package ThreadDemo;

public class Person {

	
	private String name;
	
	public void run(){
		for (int i = 0; i <20; i++) {
			System.out.println(name+i);
		}
	}

	public Person(String name) {
		this.name = name;
	}
}
