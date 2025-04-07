package study;

//아래 기능들은 jar파일이 갖고있음
//module-info삭제하면 빨간줄 사라짐

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import std.mysql.cj.jdbc.exceptions.PacketTooBigException;

import std.dto.Std;

public class StdDAO {
	// 모든 학생 출력
	public static ArrayList<Std> getAllstds() throws SQLException, ClassNotFoundException {
	
		//con을 finally에서도 사용해야하므로 try밖에 선언
		Connection con = null;
		Statement stmt=null;
		
		//결과를 받아옴 
		ResultSet rset=null;
		
		ArrayList<Std> stdList=null;
		
			try {
				// step01 : JDBC 드라이버 로드. 외부 자료파일 넣고 실제로 불러오게 처리
				Class.forName("com.mysql.cj.jdbc.Driver");//mysql드라이버를 내부로 가지고옴
				// step02 : DB 연결(db, id, pw)
				// 어떤 db사용할지
				String url = "jdbc:mysql://localhost:3306/uni?serverTimezone=UTC";
				con = DriverManager.getConnection(url, "id", "pwd");
				// step03 : SQL 객체 생성 (sql실행객체)
				stmt = con.createStatement();
				
				// step04 : select* from std; 쿼리실행
				// 쿼리 결과 값을 받아옴
				String sql ="SELECT * FROM student";
				rset=stmt.executeQuery(sql);
				
				// step05
				stdList=new ArrayList<>();
				// 객체를 하나씩 갖고올 때 이터레이터 사용
				// 반복시 항상 갖고올 데이터 있는지 물어봐야함 -> rset.next()
				while(rset.next()) {
					
					stdList.add(new Std(//stdno 컬럼의 내용을 가져옴
											rset.getInt("stdno"),
											// 문자열 데이터 값을 가져옴
											rset.getString("sname"),
											rset.getString("major")));
									
					
				}
			}
			finally {
				// step06 :사용한 자원 반환. 사용하든 안하든 무조건 수행해야하므로 finally
				if(rset!=null) {
					rset.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(con!=null) {
					con.close();
				}
			}
		return stdList;
	}
	
	// 학생 출력
	public static std getstdBystdno(int stdno) throws ClassNotFoundException, SQLException {
		
				Connection con = null;
				Statement stmt=null;
				ResultSet rset=null;
				std std=null;
				
					try {
						// step01 : JDBC 드라이버 로드. 외부 자료파일 넣고 실제로 불러오게 처리
						Class.forName("com.mysql.cj.jdbc.Driver");//mysql드라이버를 내부로 가지고옴
						// step02 : DB 연결(db, id, pw)
						// 어떤 db사용할지
						String url = "jdbc:mysql://localhost:3306/uni?serverTimezone=UTC";
						con = DriverManager.getConnection(url, "uni", "pwd");
						// step03 : SQL 객체 생성 (sql실행객체)
						stmt = con.createStatement();
						
						// step04 : select* from std; 쿼리실행
						// 쿼리 결과 값을 받아옴
						String sql = "SELECT * FROM std WHERE stdno="+stdno;
						rset=stmt.executeQuery(sql);
						
						// step05
						std=new std();
						// 객체를 하나씩 갖고올 때 이터레이터 사용
						// 반복시 항상 갖고올 데이터 있는지 물어봐야함 -> rset.next()
						// 하나의 객체일때도 객체를 가지고 오는 순서 보장을 위해 while
						while(rset.next()) {
							std=new Std(//stdno 컬럼의 내용을 가져옴
													rset.getInt("stdno"),
													// 문자열 데이터 값을 가져옴
													rset.getString("sname"),
													rset.getString("loc"));
						}
							
						
					}
					finally {
						// step06 :사용한 자원 반환. 사용하든 안하든 무조건 수행해야하므로 finally
						if(rset!=null) {
							rset.close();
						}
						if(stmt!=null) {
							stmt.close();
						}
						if(con!=null) {
							con.close();
						}
					}
		
		return std;
	}
	

	// 학생 생성 : INSERT INTO std VALUES (stdno, sname, loc);
	public static boolean insertstd(std newstd) throws ClassNotFoundException, SQLException {
		
		Connection con = null;
//		Statement stmt=null;	
		
		//이미 객체를 만들때부터 어떤 쿼리 쓸지 준비됨
		PreparedStatement pstmt= null; // 이미 쿼리가 준비된 상태에서 객체가 만들어짐
		
		
		//몇개 수행됐는지 숫자만 받아오면 되니 resultset필요없음.
		
		Std std=null;
		
			try {
				// step01 : JDBC 드라이버 로드. 외부 자료파일 넣고 실제로 불러오게 처리
				Class.forName("com.mysql.cj.jdbc.Driver");
				// step02 : DB 연결(db, id, pw)
				// 어떤 db사용할지
				String url = "jdbc:mysql://localhost:3306/uni?serverTimezone=UTC";
				con = DriverManager.getConnection(url, "uni", "pwd");
				
				
				// step03 : SQL 객체 생성 (sql실행객체)
				String sql = "INSERT INTO std VALUES (?,?,?)";
				pstmt=con.prepareStatement(sql);	// 얘는 스트링에 자동으로 ''붙여줌
				pstmt.setInt(1, newstd.getStdno());
				pstmt.setString(2, newstd.getSname());
				pstmt.setString(3, newstd.getMajor());
				// step04 
				// 문자열에는 단일 따옴표 ''로 감싸야함
//				String sql = "INSERT INTO std VALUES ("+newstd.getStdno()+",'"+newstd.getSname()+"','"+newstd.getLoc()+"')";
				int result =pstmt.executeUpdate();
				
				// step05
				// 객체를 하나씩 갖고올 때 이터레이터 사용
				// 반복시 항상 갖고올 데이터 있는지 물어봐야함 -> rset.next()
				// 하나의 객체일때도 객체를 가지고 오는 순서 보장을 위해 while
				if(result !=0) {
					return true;
				}
					
				
			}
			finally {
//				// step06 :사용한 자원 반환. 사용하든 안하든 무조건 수행해야하므로 finally
				if(pstmt!=null) {
					pstmt.close();
				}if(con!=null) {
					con.close();
				}
			}

		
		return false;
	}
	
	// 학생 전공 수정 : UPDATE std SET major = ? WHERE stdno = ?;
	public static boolean updateLocBystdno(int stdno, String newstdMajor) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/uni?serverTimezone=UTC";
			con = DriverManager.getConnection(url, "uni", "pwd");
			
			String sql = "UPDATE std SET major = ? WHERE stdno = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, newstdMajor);
			pstmt.setInt(2, stdno);
			
			int result = pstmt.executeUpdate();
			
			if(result != 0) {
				return true;
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return false;
	}
	
	
	
	// 학생 삭제 : DELETE FROM std WHERE stdno = ?
	public static boolean deletestdBystdno(int stdno) throws SQLException, ClassNotFoundException {

		Connection con = null;
		
		PreparedStatement pstmt= null; // 이미 쿼리가 준비된 상태에서 객체가 만들어짐
		
		
			try {
				// step01 : JDBC 드라이버 로드. 외부 자료파일 넣고 실제로 불러오게 처리
				Class.forName("com.mysql.cj.jdbc.Driver");
				// step02 : DB 연결(db, id, pw)
				String url = "jdbc:mysql://localhost:3306/uni?serverTimezone=UTC";
				con = DriverManager.getConnection(url, "uni", "pwd");
				
				
				// step03 : SQL 객체 생성 
				String sql = "DELETE FROM std WHERE stdno = ?";
				pstmt=con.prepareStatement(sql);	// 얘는 스트링에 자동으로 ''붙여줌
				pstmt.setInt(1, stdno);
				// step04 
				int result =pstmt.executeUpdate();
				
				// step05
				if(result !=0) {
					return true;
				}
					
				
			}
			finally {
//				// step06 :사용한 자원 반환. 사용하든 안하든 무조건 수행해야하므로 finally
				if(pstmt!=null) {
					pstmt.close();
				}if(con!=null) {
					con.close();
				}
			}

		
		return false;
	}
}