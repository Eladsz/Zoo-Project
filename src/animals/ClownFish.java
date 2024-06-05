package animals;

import java.util.Arrays;

import abstract_classes.FishAbstract;
import enums.BodySignatureEnum;
import enums.FishColorEnum;
import interfaces.TrioMethodInterface_ageOneYear_makeNoise_feed;
import zoo.ExceptionZoo;

public class ClownFish extends FishAbstract implements TrioMethodInterface_ageOneYear_makeNoise_feed {
	private FishColorEnum[] color = { FishColorEnum.BLACK, FishColorEnum.WHITE, FishColorEnum.ORANGE };
	private BodySignatureEnum signature = BodySignatureEnum.STRIPES;
	private double howMuchTheFishEat = 2;
	public static final int LifeSpan = 8;
	public static final int RANGE_LENGTH_RANDOM_CLOWN_FISH = 10; // CENTEMETER

	public ClownFish(int age, double length) throws ExceptionZoo {
		super(age, length);
		if ((length > RANGE_LENGTH_RANDOM_CLOWN_FISH || length <= 0) && (age < 0 || age > LifeSpan)) {
			throw new ExceptionZoo("Not Valid Length And Age!",
					"Length must be: 0 < [Length] <= 200!\nAge Must Be Between 0 to " + LifeSpan + "!");
		} else if (length > RANGE_LENGTH_RANDOM_CLOWN_FISH || length <= 0) {
			throw new ExceptionZoo("Not Valid Length",
					"Length must be: 0 < [Length] <= " + RANGE_LENGTH_RANDOM_CLOWN_FISH + "!");
		} else if (age < 0 || age > LifeSpan) {
			throw new ExceptionZoo("Not Valid Age", "Age Must Be Between 0 to " + LifeSpan + "!");
		}
	}

	@Override
	public String toString() {

		return "[ClownFish " + ", color = " + Arrays.toString(color) + ", signature = " + signature + super.toString()
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
