package study;

public class AbstractClassTest extends AbstractClass{
	AbstractClassTest(int x, int y) {
		super(x,y); // 조상 클래스 생성자 호출
	}
	
	// 추상클래스 abstract 무조건 자식에서 구현해야함
	@Override
	void loc() {
		System.out.println(x+","+y);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractClassTest act = new AbstractClassTest(1,3);
		act.loc();
		
	}



}
