package abstract_classes;

import java.text.DecimalFormat;
import enums.BodySignatureEnum;
import enums.FishColorEnum;

public abstract class FishAbstract extends AnimalAbstract {
	protected double length;
	protected FishColorEnum[] color;
	protected BodySignatureEnum signature;
	protected double howMuchTheFishEat;
	public DecimalFormat formatter = new DecimalFormat("#0.00");
	protected int happiness;

	public FishAbstract(int age, double length) {
		super(age);
		this.length = length;
		this.happiness = 100;
	}

	@Override
	public void ageOneYear() {
		this.age++;
	}

	@Override
	public double feed() {
		return 0;
	}

	@Override
	public String makeNoise() {
		return "blob";
	}

	@Override
	public String toString() {
		return ", age = " + age + ", length = " + formatter.format(length) + ", happiness level= " + happiness;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getLength() {
		return length;
	}

	public FishColorEnum[] getColor() {
		return color;
	}

	public BodySignatureEnum getSignature() {
		return signature;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

}
