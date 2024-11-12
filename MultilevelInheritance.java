class Animal {
	public void walk() {
		System.out.println("Animals walks");
	}
}

class Dog extends Animal {
	public void bark() {
		System.out.println("Dog Barks");
	}	
}

class Puppy extends Dog {
	public void sleep() {
		System.out.println("Puppy Sleeps");
	}	
}

public class MultilevelInheritance {
	public static void main(String[] args) {
		Puppy myPuppy = new Puppy();
		
		myPuppy.walk();
		myPuppy.bark();
		myPuppy.sleep();
	}
}
