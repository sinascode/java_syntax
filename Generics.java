package study;


class GenericsTest1<T>{
	T value;
	
	public GenericsTest1(T value) {
		this.value=value;
	}
	
	public T getValue() {
		return value;
	}
	
}

class GenericsTest2<T,V>{
	T value;
	V value2;
	public GenericsTest2(T value, V value2) {
		this.value=value;
		this.value2=value2;
	}
	
	public T getValue() {
		return value;
	}
	public V getValue2() {
		return value2;
	}
	
}
public class Generics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 참조 변수와 생성자 타입에 실제 타입을 지정해준다
		GenericsTest1<Integer> g1 = new GenericsTest1<>(1);
		System.out.println(g1.getValue().getClass().getName());

		GenericsTest1<String> g2 = new GenericsTest1<>("Hello");
		System.out.println(g2.getValue().getClass().getName());

	
		GenericsTest2<Double, Boolean> g3 = new GenericsTest2<>(1.0, true);
		System.out.println(g3.getValue().getClass().getName());
	}

}
