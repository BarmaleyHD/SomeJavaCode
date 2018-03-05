package tables;

public abstract class Person {
	
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Person(String name){
		this.name = name;
	}
	
	public abstract void display();

}
