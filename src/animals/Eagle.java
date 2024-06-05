package animals;

import java.util.Random;

import abstract_classes.PredatoryBird;
import enums.EagleTypesEnum;
import interfaces.TrioMethodInterface_ageOneYear_makeNoise_feed;
import zoo.ExceptionZoo;

public class Eagle extends PredatoryBird implements TrioMethodInterface_ageOneYear_makeNoise_feed {
	public static Random random = new Random();
	public static final int LifeSpan = 30; // years
	public static final int MaxTopSpeed = 360; // KMPH
	public static final int MaxTopAltitude = 3048; // meters
	public static final double MaxEagleWeight = 10; // KiloGrams
	private EagleTypesEnum eagleType;
	private Long EagleID;
	private static long idEnum = 0L;

	public Eagle(String name, boolean gender, double weight, int age, int speed, int topFlyAltitude,
			EagleTypesEnum eagleType) throws ExceptionZoo {
		super(name, gender, weight, age, speed, topFlyAltitude);
		this.eagleType = eagleType;
		if (weight > MaxEagleWeight || weight <= 0 || age < 0 || age > LifeSpan || speed <= 0 || speed > MaxTopSpeed
				|| topFlyAltitude <= 0 || topFlyAltitude > MaxTopAltitude) {
			throw new ExceptionZoo("Not Valid Parameters!", "One Or More From The Parameters Are Not Acceptable");
		}
		this.EagleID = ++idEnum;
	}

	@Override
	public String makeNoise() {
		return "SCREEEE!";
	}

	@Override
	public double feed() {
		return weight * 0.28;
	}

	@Override
	public String toString() {
		return "Animal type: " + eagleType + " Eagle [" + super.toString() + ", EagleID = " + EagleID + "]";
	}

	public EagleTypesEnum getEagleType() {
		return eagleType;
	}

	public Long getEagleID() {
		return EagleID;
	}

}
