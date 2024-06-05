package abstract_classes;

import interfaces.TrioMethodInterface_ageOneYear_makeNoise_feed;
import zoo.ExceptionZoo;

public abstract class PredatoryBird extends PredatoryAnimalsAbstract
		implements TrioMethodInterface_ageOneYear_makeNoise_feed {
	protected int speed;
	protected int topFlyAltitude;

	public PredatoryBird(String name, boolean gender, double weight, int age, int speed, int topFlyAltitude)
			throws ExceptionZoo {
		super(name, gender, weight, age);
		this.speed = speed;
		this.topFlyAltitude = topFlyAltitude;
	}

	@Override
	public double feed() {
		return 0;
	}

	@Override
	public String makeNoise() {
		return null;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTopFlyAltitude() {
		return topFlyAltitude;
	}

	@Override
	public String toString() {
		return "speed = " + speed + ", topFlyAltitude = " + topFlyAltitude + ", " + super.toString();
	}

}
