package study;


class  Phone{
	int version;
	
	void versionUp() {++version;}
	void versionUDown() {--version;}
}

class IPhone extends Phone{
	String text;
	void caption() {};
}

class Galaxy extends Phone{
	String str;
	void caption() {};
}


// 다형성: 조상클래스 타입의 참조변수로 자손 클래스의 인스턴스를 참조 가능
public class Polymorphism {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IPhone i = new IPhone();
		Phone p = new IPhone();	// 조상 타입의 참조변수로 자손 인스턴스를 참조
		// IPhone ip = new Phone(); // 자식타입으로 조상타입  인스턴스 참조 불가능		
		
		i.caption();
		// p.caption(); // 조상 타입의 참조변수로 참조 참조하면 Phone클래스의 멤버들만 사용 가능
		
		
		
		// 참조변수 instanceof 타입: 
		
		Galaxy g= new Galaxy();
		System.out.println(g instanceof Phone);
		//상속 관계가 아닌 클래스간 형변환 불가
		
		Phone pg= new Galaxy();
		Phone pi= new IPhone();
		
		System.out.println(pg instanceof IPhone);
		System.out.println(pi instanceof Galaxy);

		//공통 조상도 아닐경우 에러
		//System.out.println(g instanceof IPhone);
		
		
	}

}
