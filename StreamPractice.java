package study;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;


//Comparable을 구현한 클래스
class Person implements Comparable<Person> {
	String name;
	int age;
	
	Person(String name, int age){
		this.name=name;
		this.age=age;
	}
	
	// 나이 오름차순기준 정렬이 기본
    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.age, person.age);
    }
}

public class StreamPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Person> personStream=new ArrayList<Person>();
		
		Person a= new Person("aaa",21);
		Person b= new Person("abb",22);
		Person c= new Person("acc",20);
		Person d= new Person("ddd",24);
		Person e= new Person("eee",25);
		
		personStream.add(a);
		personStream.add(b);
		personStream.add(c);
		personStream.add(d);
		personStream.add(e);
		
		
		//  sorted() 전에 .map() 하면 String이 되므로 나이 정렬 못함
		// .sorted는 객체일때 하고 그 뒤에 string해야함
		personStream.stream()
				.filter(p -> p.name.contains("a"))
				.sorted(Comparator.reverseOrder()) // age 내림차순 (compareTo 기준 뒤집기)
	    		.map(p -> p.name)
	    		.forEach(str->System.out.println(str));

	}

}
