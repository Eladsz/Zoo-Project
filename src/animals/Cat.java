package animals;

import abstract_classes.PredatoryAnimalsAbstract;
import zoo.ExceptionZoo;

public class Cat extends PredatoryAnimalsAbstract {
	public static double MaxWeight = 20;
	public static long counter = 0L;
	private long id;

	public Cat(String name, boolean gender, double weight, int age) throws ExceptionZoo {
		super(name, gender, weight, age);
		this.id = ++counter;
	}

	@Override
	public double feed() {
		// every cat eats 3 fish/cans/steaks a day.
		return 3;
	}

	@Override
	public String makeNoise() {
		return "Meow";
	}

	@Override
	public String toString() {
		return "Animal Type: Cat [" + super.toString() + "]";
	}

	public long getId() {
		return id;
	}

}
