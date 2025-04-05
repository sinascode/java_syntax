package study;

// Objetc는 모든 클래스의 최고 조상
// equals : 주소값으로 비교
// toString : 오버라이딩 하지 않으면 클래스 이름과 16진수 해시코드
class TestEquals{
	int te;
	public TestEquals(int te) {
		this.te=te;
	}
	// toString overriding하여 te를 출력하게 바꿈
	public String toString() {
		return "te :"+te;
	}
}
public class Object {

	public static void main(String[] args) {
		TestEquals t1= new TestEquals(1);
		TestEquals t2= new TestEquals(2);

		
		if (t1.equals(t2))
			System.out.println("t1==t2");
		else
			System.out.println("t1!=t2");
		
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());

	}

}
