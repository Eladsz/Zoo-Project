package animals;

import java.util.Arrays;

import abstract_classes.FishAbstract;
import enums.BodySignatureEnum;
import enums.FishColorEnum;
import interfaces.TrioMethodInterface_ageOneYear_makeNoise_feed;
import zoo.ExceptionZoo;

public class GoldFish extends FishAbstract implements TrioMethodInterface_ageOneYear_makeNoise_feed {
	private FishColorEnum[] color;
	private BodySignatureEnum signature = BodySignatureEnum.SMOOTH;
	private double howMuchTheFishEat = 1;
	public static final int LifeSpan = 12;
	public static final int RANGE_LENGTH_RANDOM_GOLD_FISH = 50; // CENTEMETER

	public GoldFish(int age, double length, FishColorEnum[] color) throws ExceptionZoo {
		super(age, length);
		this.color = color;
		if ((length > RANGE_LENGTH_RANDOM_GOLD_FISH || length <= 0) && (age < 0 || age > LifeSpan)) {
			throw new ExceptionZoo("Not Valid Length And Age!",
					"Length must be: 0 < [Length] <= 200!\nAge Must Be Between 0 to " + LifeSpan + "!");
		} else if (length > RANGE_LENGTH_RANDOM_GOLD_FISH || length <= 0) {
			throw new ExceptionZoo("Not Valid Length",
					"Length must be: 0 < [Length] <= " + RANGE_LENGTH_RANDOM_GOLD_FISH + "!");
		} else if (age < 0 || age > LifeSpan) {
			throw new ExceptionZoo("Not Valid Age", "Age Must Be Between 0 to " + LifeSpan + "!");
		}
	}

	@Override
	public String toString() {

		return "[GoldFish " + ", color = " + Arrays.toString(color) + ", signature = " + signature + super.toString()
				+ "]";
	}

	@Override
	public double feed() {
		return howMuchTheFishEat;
	}

	public FishColorEnum[] getColor() {
		return color;
	}

	public BodySignatureEnum getSignature() {
		return signature;
	}

}
