package zoo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import UI.Logger.Logger;
import UI.Menus.MenuFactory;
import UI.Menus.MenuFactory.MenuType;
import Users.Worker.WorkersAuthenticationSystem;
import abstract_classes.*;
import animals.*;
import enums.*;
import interfaces.*;

public class ZooAplication {
	public static Scanner scan = new Scanner(System.in);
	public static DecimalFormat format = new DecimalFormat("#0.00");
//	public static Zoo zoo = new Zoo(" Afeka Zoo ", " Mifza Kadesh 38 ");
//	public static Zoo zoo = Zoo.getZooInstance(" Afeka Zoo ", " Mifza Kadesh 38 ");
	public static ZooInterface zoo = Zoo.getZooInstance(" Afeka Zoo ", " Mifza Kadesh 38 ");

	public static void main(String[] args) throws ExceptionZoo, InterruptedException, Exception {
		zoo.zooContant();
		Logger.initLogger();
		int choice = 0;
		Logger.log();
		do {
			programStartAndOpening();
			choice = checkInputStringToInt();
			switch (choice) {
			case 1:
				case1_ShowZooStats();
				Logger.log();
				break;
			case 2:
				case2_ShowAllPeguinsInTheZoo();
				Logger.log();
				break;
			case 3:
				case3_AddNewPeguinToTheZoo();
				Logger.log();
				break;
			case 4:
				case4_ShowAllPredetoryAnimalsInTheZoo();
				Logger.log();
				break;
			case 5:
				case5_AddNewPredetoryAnimalToTheZoo();
				Logger.log();
				break;
			case 6:
				case6_ShowAllFishInTheZoo();
				Logger.log();
				break;
			case 7:
				case7_AddNewFishToTheZoo_RandomOrOneByOne();
				Logger.log();
				break;
			case 8:
				case8_FeedAllAnimalInTheZoo();
				Logger.log();
				break;
			case 9:
				case9_ListenToAllAnimalsInTheZoo();
				Logger.log();
				break;
			case 10:
				case10_ageOneYear();
				Logger.log();
				break;
			case 11:
				case11_ResortThePenguins();
				Logger.log();
				break;
			case 12:
				case12_NewAnimals();
				Logger.log();
				break;
			case 13:
				workerAuthentication(WorkersAuthenticationSystem.getInstance());
				Logger.log();
				break;
			default:
				if (choice == 0) {
					Logger.log("Good Bye!!");
				} else {
					Logger.log("Enter a Valid Option Number.");
					Logger.log();
				}
				break;
			}

		} while (choice != 0);

	}

	public static void workerAuthentication(WorkersAuthenticationSystem workerAuthentication) {
					MenuFactory.getMenu(MenuType.LOGIN).mainMenu();
	}

	public static void mainManeuPrint() {
		Logger.log("\t1) Zoo Info.");
		Logger.log("\t2) See All Penguins.");
		Logger.log("\t3) Add A New Penguin.");
		Logger.log("\t4) See All Predetory Animals.");
		Logger.log("\t5) Add A New Predetory Animals.");
		Logger.log("\t6) See All The Fish In The Aquarium.");
		Logger.log("\t7) Add Fish To The Aquarium. ");
		Logger.log("\t8) Feed The Animals.");
		Logger.log("\t9) Listen To The Animals Voices.");
		Logger.log("\t10) Move Forward A Year (Next Year).");
		Logger.log("\t11) Change Penguin Sorting Strategy.");
		Logger.log("\t12) The New Animals Sectione.");
		Logger.log("\t13) Visitor management system.");
		Logger.log("\tENTER 0 TO EXIT.");
	}

	public static void programStartAndOpening() {
		Logger.log("Welcome To Our Zoo!");
		Logger.log("Please Enter The Number Of Your Choice:");
		mainManeuPrint();
	}

	public static void case1_ShowZooStats() {
		Logger.log("\tZoo Name: " + zoo.getZooName());
		Logger.log("\tZoo Adress: " + zoo.getZooAddress() + "\n");
		Logger.log("\tLions Amount In The Zoo: " + zoo.getLionsArr().size());
		Logger.log("\tTigers Amount In The Zoo: " + zoo.getTigersArr().size());
		Logger.log("\tPenguins Amount In The Zoo: " + zoo.getPenguinHord().countPenguinsInList());
		Logger.log("\n\tTotal Fish Amount In The Zoo: " + zoo.getFishArr().size());
		Logger.log("\t\tAquarium Fish Amount: " + zoo.theNumberOfAquariumFish());
		Logger.log("\t\tGold Fish Amount: " + zoo.theNumberOfGoldFish());
		Logger.log("\t\tClown Fish Amount: " + zoo.theNumberOfClownFish());
		Logger.log("\t\tEagles Amount: " + zoo.howMuchEaglesInTheZoo());
		Logger.log("\t\tCats Amount: " + zoo.howMuchCatsInTheZoo());
		Logger.log("\t\tMegaladons Amount: " + zoo.getMegaladon().size());
		Logger.log();
	}

	public static void case2_ShowAllPeguinsInTheZoo() throws InterruptedException {
		SpecialPrints.PhotoPrintingPenguin();
		Logger.log(zoo.getPenguinHord().printPenguinList());
	}

	public static void case3_AddNewPeguinToTheZoo() {
		int age = 0;
		double height = 0;
		String name;
		do {
			Logger.log("Please Enter The Penguins Name (a string containing only alphabets): ");
			name = scan.nextLine();
		} while (!isValidAlphabetString(name));
		Logger.log("Please Enter The Penguins Age(In Integers): ");
		age = checkInputStringToInt();
		Logger.log("Please Enter The Penguins Height(0 < height <= 200)[In CM]: ");
		height = checkInputStringToDouble();
		try {
			zoo.addNewPeguin(new Penguin(name, age, height));
			Logger.log("Congrats we have a new penguin!!");
		} catch (ExceptionZoo e) {
			Logger.log(e.getTheIssueTitle());
			Logger.log(e.getTheIssueDescription());
			Logger.log("\nThe penguin with the name < " + name + " > is not validated.");
			Logger.log("Please try again!");
		}

	}

	public static void case4_ShowAllPredetoryAnimalsInTheZoo() throws InterruptedException {
		SpecialPrints.PhotoPrintingLion();
		Logger.log();
		Logger.log(zoo.returnStringOfAllPredetoryAnimals());
	}

	public static int checkInputStringToInt() {
		boolean success = false;
		int num = 0;
		while (!success) {
			try {
				String choiceText = scan.nextLine();
				num = Integer.parseInt(choiceText);
				success = true;
			} catch (Exception inputTextException) {
				Logger.log("You should type a number!");
			}
		}
		return num;
	}

	public static double checkInputStringToDouble() {
		boolean success = false;
		double num = 0;
		while (!success) {
			try {
				String choiceText = scan.nextLine();
				num = Double.parseDouble(choiceText);
				success = true;
			} catch (Exception inputTextException) {
				Logger.log("You should type a number!");
			}
		}
		return num;
	}

	public static void case5_AddNewPredetoryAnimalToTheZoo() throws ExceptionZoo {
		int whatAnimalToAddChoice = 0;
		Logger.log("Do you want to add a Tiger or a Lion [Choose 1 or 2]?");
		Logger.log("\t1) Lion ");
		Logger.log("\t2) Tiger ");
		whatAnimalToAddChoice = checkInputStringToInt();
		while (whatAnimalToAddChoice != 1 && whatAnimalToAddChoice != 2) {
			Logger.log("Enter a valid number!");
			whatAnimalToAddChoice = checkInputStringToInt();
		}
		if (whatAnimalToAddChoice == 1) {
			AddNewLionToTheZoo();
		} else {
			AddNewTigerToTheZoo();
		}

	}

	public static void AddNewLionToTheZoo() throws ExceptionZoo {
		int age = getNewPredatorAge();
		String name = getNewPredatorName();
		Boolean gender = getNewPredatorGender();
		double weight = getNewPredatorWeight(Lion.MaxWeight);
		Lion newlion = new Lion(name, gender, weight, age);
		zoo.addNewLion(newlion);
		Logger.log("The Lion Entered Succesfully! ");
	}

	public static void AddNewEgleToTheZoo() throws ExceptionZoo {
		int age = getNewEagleAge();
		String name = getNewPredatorName();
		Boolean gender = getNewPredatorGender();
		double weight = getNewEagleWeight();
		EagleTypesEnum eagleType = getNewEagleEnum();
		int speed = zoo.randomEagleParameters(Eagle.MaxTopSpeed);
		int altitude = zoo.randomEagleParameters(Eagle.MaxTopAltitude);
		zoo.addEagle(new Eagle(name, gender, weight, age, speed, altitude, eagleType));
		Logger.log("The Eagle Entered Succesfully! ");
	}

	public static EagleTypesEnum getNewEagleEnum() throws ExceptionZoo {
		EagleTypesEnum eagleType;
		int enumChoice = 0;
		do {
			Logger.log("Choose 1 Of The Next Eagle Types: ");
			Logger.log("\t1)" + EagleTypesEnum.values()[0]);
			Logger.log("\t2)" + EagleTypesEnum.values()[1]);
			Logger.log("\t3)" + EagleTypesEnum.values()[2]);
			enumChoice = checkInputStringToInt();
			if (enumChoice < 0 || enumChoice > 3) {
				Logger.log("You cannot Enter this number please try again.");
			}
		} while (enumChoice < 0 || enumChoice > 3);
		if (enumChoice == 1) {
			eagleType = EagleTypesEnum.values()[0];
			return eagleType;
		} else if (enumChoice == 2) {
			eagleType = EagleTypesEnum.values()[1];
			return eagleType;
		} else {
			eagleType = EagleTypesEnum.values()[2];
			return eagleType;
		}
	}

	public static double getNewEagleWeight() throws ExceptionZoo {
		Logger.log("Enter Eagle Weight in kg(0 < weight <= " + Eagle.MaxEagleWeight + "):");
		double weight = checkInputStringToDouble();
		while (weight <= 0 || weight > Eagle.MaxEagleWeight) {
			Logger.log("Incorrect Weight Please Enter Again.");
			weight = checkInputStringToDouble();
		}
		return weight;
	}

	public static int getNewEagleAge() throws ExceptionZoo {
		double getAge;
		int age;
		Logger.log("Enter Eagle Age[In Integers 0 - " + Eagle.LifeSpan + "]:");
		do {
			getAge = checkInputStringToDouble();
			age = (int) getAge;
			if (age < 0 || age > Eagle.LifeSpan) {
				Logger.log("You cannot Enter this age please try again.");
			}
		} while (age < 0 || age > Eagle.LifeSpan);
		return age;

	}

	public static String getNewPredatorName() throws ExceptionZoo {
		String name;
		do {
			Logger.log("Please Enter The Predator Name (a string containing only alphabets): ");
			name = scan.nextLine();
		} while (!isValidAlphabetString(name));
		return name;

	}

	public static int getNewPredatorAge() throws ExceptionZoo {
		double getAge;
		int age;
		Logger.log("Enter Predator Age[In Integers 0 - " + PredatoryAnimalsAbstract.LifeSpan + "]:");
		do {
			getAge = checkInputStringToDouble();
			age = (int) getAge;
			if (age < 0 || age > PredatoryAnimalsAbstract.LifeSpan) {
				Logger.log("You cannot Enter this age please try again.");
			}
		} while (age < 0 || age > PredatoryAnimalsAbstract.LifeSpan);
		return age;

	}

	public static Boolean getNewPredatorGender() throws ExceptionZoo {
		Boolean gender;
		Logger.log("Choose The Number Of Your Predator Gender: ");
		Logger.log("\t1) MALE");
		Logger.log("\t2) FEMALE");
		int numOfGender = checkInputStringToInt();
		while (numOfGender != 1 && numOfGender != 2) {
			Logger.log("Please Enter 1 or 2!");
			Logger.log("\t1) MALE");
			Logger.log("\t2) FEMALE");
			numOfGender = checkInputStringToInt();
		}
		if (numOfGender == 1) {
			gender = true;
		} else {
			gender = false;
		}
		return gender;

	}

	public static double getNewPredatorWeight(double maxWeight) throws ExceptionZoo {
		Logger.log("Enter Predator Weight in kg(0 < weight <= " + maxWeight + "):");
		double weight = checkInputStringToDouble();
		while (weight <= 0 || weight > maxWeight) {
			Logger.log("Incorrect Weight Please Enter Again.");
			weight = checkInputStringToDouble();
		}
		return weight;
	}

	public static void AddNewTigerToTheZoo() throws ExceptionZoo {
		int age = getNewPredatorAge();
		String name = getNewPredatorName();
		Boolean gender = getNewPredatorGender();
		double weight = getNewPredatorWeight(Tiger.MaxWeight);
		Tiger newTiger = new Tiger(name, gender, weight, age);
		zoo.addNewTiger(newTiger);
		Logger.log("The Tiger Entered Succesfully! ");
	}

	public static void AddNewCatToTheZoo() throws ExceptionZoo {
		int age = getNewPredatorAge();
		String name = getNewPredatorName();
		Boolean gender = getNewPredatorGender();
		double weight = getNewPredatorWeight(Cat.MaxWeight);
		Cat newCat = new Cat(name, gender, weight, age);
		zoo.addNewCat(newCat);
		Logger.log("The Cat Entered Succesfully! ");
	}

	public static void case6_ShowAllFishInTheZoo() throws InterruptedException {
		SpecialPrints.PhotoPrintingFish();
		Logger.log();
		Logger.log(zoo.returnStringOfArrFish());
		Logger.log(zoo.returnStringOfAllColorFish());
		Logger.log();
	}

	public static void case7_AddNewFishToTheZoo_RandomOrOneByOne() throws ExceptionZoo {
		int choiceHowToAddAFish;
		addFishManue();
		choiceHowToAddAFish = checkInputStringToInt();
		while (choiceHowToAddAFish != 1 && choiceHowToAddAFish != 2) {
			Logger.log("Enter a Valid Option Number.");
			Logger.log();
			addFishManue();
			choiceHowToAddAFish = checkInputStringToInt();
		}
		switch (choiceHowToAddAFish) {
		case 1:
			manualyAddFishManue();
			int fishType;
			fishType = checkInputStringToInt();
			while (fishType != 1 && fishType != 2 && fishType != 3) {
				Logger.log("Enter a Valid Option Number.");
				Logger.log();
				manualyAddFishManue();
				fishType = checkInputStringToInt();
			}
			switch (fishType) {
			case 1:
				enterManualyAquariumFish();
				break;
			case 2:
				enterManualyGoldFish();
				break;
			case 3:
				enterManualyClownFish();
				break;
			default:
				break;
			}
			break;
		case 2:
			randomlyEnteredFishDatta();
			break;
		default:
			break;
		}

	}

	public static void addFishManue() {
		Logger.log("Choose The Number Of How To Add A Fish:");
		Logger.log("\t1) Manualy Enter The Data.");
		Logger.log("\t2) Randomly Enter The Data By Entering The number Of Fish You Like To Add.");
	}

	public static void manualyAddFishManue() {
		Logger.log("What fish type do you want to add[Choose 1, 2 or 3]:");
		Logger.log("\t1)Aquarium Fish");
		Logger.log("\t2)Gold Fish");
		Logger.log("\t3)Clown Fish");

	}

	public static void enterManualyGoldFish() throws ExceptionZoo {
		int age;
		Logger.log("Enter fish length in CM [number between: 0.00 < length <= 50.00]:");
		double lengthfish = checkInputStringToDouble();
		while (lengthfish <= 0 || lengthfish > 50) {
			Logger.log("Invalid Number Please Enter Again.");
			lengthfish = checkInputStringToDouble();
		}
		Logger.log("Enter fish Age[In Integers 0 - " + GoldFish.LifeSpan + "]:");
		age = checkInputStringToInt();
		while (age < 0 || age > GoldFish.LifeSpan) {
			Logger.log("Invalid Number Please Enter Again.");
			age = checkInputStringToInt();
		}
		Logger.log("Enter wich color the fish is[Choose one from the below numbers]:");
		Logger.log("\t1) " + FishColorEnum.values()[0]);
		Logger.log("\t2) " + FishColorEnum.values()[3]);
		Logger.log("\t3) " + FishColorEnum.values()[5]);
		Logger.log("\t4) " + FishColorEnum.values()[7]);
		int colorChoice;
		FishColorEnum[] coloerArr = new FishColorEnum[1];
		colorChoice = checkInputStringToInt();
		while (colorChoice != 1 && colorChoice != 2 && colorChoice != 3 && colorChoice != 4) {
			Logger.log("Enter a Valid Option Number.");
			Logger.log();
			Logger.log("Enter wich color the fish is[Choose one from the below numbers]:");
			Logger.log("\t1) " + FishColorEnum.values()[0]);
			Logger.log("\t2) " + FishColorEnum.values()[3]);
			Logger.log("\t3) " + FishColorEnum.values()[5]);
			Logger.log("\t4) " + FishColorEnum.values()[7]);
			colorChoice = checkInputStringToInt();
		}
		switch (colorChoice) {
		case 1:
			coloerArr[0] = FishColorEnum.values()[0];
			break;
		case 2:
			coloerArr[0] = FishColorEnum.values()[3];
			break;
		case 3:
			coloerArr[0] = FishColorEnum.values()[5];
			break;
		case 4:
			coloerArr[0] = FishColorEnum.values()[7];
			break;
		default:
			break;
		}
		zoo.AddOneNewFishToArr(new GoldFish(age, lengthfish, coloerArr));
		Logger.log("The Fish Entered Succesfully! ");
	}

	public static void enterManualyClownFish() throws ExceptionZoo {
		int age;
		Logger.log("Enter fish length in CM [number between: 0.00 < length <= 10.00]:");
		double lengthfish = checkInputStringToDouble();
		while (lengthfish <= 0 || lengthfish > 10) {
			Logger.log("Invalid Number Please Enter Again.");
			lengthfish = checkInputStringToDouble();
		}
		Logger.log("Enter fish Age[In Integers 0 - " + ClownFish.LifeSpan + "]:");
		age = checkInputStringToInt();
		while (age < 0 || age > ClownFish.LifeSpan) {
			Logger.log("Invalid Number Please Enter Again.");
			age = checkInputStringToInt();
		}
		zoo.AddOneNewFishToArr(new ClownFish(age, lengthfish));
		Logger.log("The Fish Entered Succesfully! ");

	}

	public static void enterManualyAquariumFish() throws ExceptionZoo {
		int age;
		Logger.log("Enter fish length in CM [number between: 0.00 < length <= 500.00]:");
		double lengthfish = checkInputStringToDouble();
		while (lengthfish <= 0 || lengthfish > 500) {
			Logger.log("Invalid Number Please Enter Again.");
			lengthfish = checkInputStringToDouble();
		}
		Logger.log("Enter fish Age[In Integers 0 - " + AquariumFish.LifeSpan + "]:");
		age = checkInputStringToInt();
		while (age < 0 || age > AquariumFish.LifeSpan) {
			Logger.log("Invalid Number Please Enter Again.");
			age = checkInputStringToInt();
		}
		Logger.log("Enter How Much Colors Will The Fish Have[Max Colors == 10]: ");
		int numberOfColorsFishWillHave = checkInputStringToInt();
		while (numberOfColorsFishWillHave <= 0 || numberOfColorsFishWillHave > 10) {
			Logger.log("Enter a Number Between 1 - 10 !!");
			numberOfColorsFishWillHave = checkInputStringToInt();
		}
		FishColorEnum[] coloerArr = new FishColorEnum[numberOfColorsFishWillHave];
		ArrayList<FishColorEnum> availibleColoers = new ArrayList<FishColorEnum>();
		Logger.log("Enter The Number Of The Colors You Want The Fish To Have[only 1 time from every color]:");
		for (int i = 0; i < FishColorEnum.values().length; i++) {
			availibleColoers.add(i, FishColorEnum.values()[i]);
		}
		for (int k = 0; k < numberOfColorsFishWillHave; k++) {
			for (int i = 0; i < availibleColoers.size(); i++) {
				Logger.log((i + 1) + ") " + availibleColoers.get(i));
			}
			Logger.log("Please enter color number:");
			int currentColorNumer = checkInputStringToInt();

			while (currentColorNumer <= 0 || currentColorNumer > availibleColoers.size()) {
				Logger.log("Enter a valid number!!");
				for (int j = 0; j < FishColorEnum.values().length; j++) {
					Logger.log((j + 1) + ") " + FishColorEnum.values()[j]);
				}
				currentColorNumer = checkInputStringToInt();
			}
			coloerArr[k] = availibleColoers.get(currentColorNumer - 1);
			availibleColoers.remove(currentColorNumer - 1);
		}
		Logger.log("Enter Which Body Signature The Fish Hava");
		for (int i = 0; i < BodySignatureEnum.values().length; i++) {
			Logger.log(i + 1 + ") " + BodySignatureEnum.values()[i]);
		}
		int BodySignatureEnumNumber = checkInputStringToInt();
		while (BodySignatureEnumNumber < 1 || BodySignatureEnumNumber > 4) {
			Logger.log("Enter a Valid Number!!");
			for (int i = 0; i < BodySignatureEnum.values().length; i++) {
				Logger.log(i + 1 + ") " + BodySignatureEnum.values()[i]);
			}
			BodySignatureEnumNumber = checkInputStringToInt();
		}
		BodySignatureEnum BodySignature = BodySignatureEnum.values()[BodySignatureEnumNumber - 1];
		zoo.AddOneNewFishToArr(new AquariumFish(age, lengthfish, coloerArr, BodySignature));
		Logger.log("The Fish Entered Succesfully! ");
	}

	public static void randomlyEnteredFishDatta() throws ExceptionZoo {

		Logger.log("Enter The Number Of Fish You Want To Enter: ");
		int numberOfFishToAddRandomly = checkInputStringToInt();
		while (numberOfFishToAddRandomly < 1 || numberOfFishToAddRandomly > 300) {
			Logger.log("Enter Number Between 1 - 300 ");
			numberOfFishToAddRandomly = checkInputStringToInt();
		}
		zoo.addNumbrOfFish(numberOfFishToAddRandomly);
		Logger.log("The Fish Entered Succesfully! ");
	}

	public static void case8_FeedAllAnimalInTheZoo() {
		Logger.log("The Total Amount of Fish That The Penguins Ate is: " + zoo.totalPenguinsFood());
		Logger.log(
				"The Total Amount of Meat In Kilo Grams That The Lions Ate is: " + zoo.howMuchAllTheLionsEat());
		Logger.log(
				"The Total Amount of Meat In Kilo Grams That The Tigers Ate is: " + zoo.howMuchAllTheTigersEat());
		Logger.log(
				"The Total Amount of Food Packeges That The Fish Ate is: " + format.format(zoo.howMuchAllTheFishEat()));
		Logger.log("The Total Amount of Meat In Kilo Grams That The Tigers Ate is: "
				+ format.format(zoo.totalEagleFood()));
		Logger.log("All The Cats Together Ate: " + zoo.totalCatsFood() + " Cans/Fish/Steak ");
		Logger.log("All The Megaladons Together Ate: " + zoo.howMuchTheMegaladonEats()
				+ "Kg of Meat Of Fish/Giant Animal/Sharcks And More. ");

	}

	public static void case9_ListenToAllAnimalsInTheZoo() {
		Logger.log(zoo.makeNoise());

	}

	private static boolean isValidAlphabetString(String input) {
		// Check if the input contains only alphabets
		return input.matches("^[a-zA-Z]+$");
	}

	public static void case10_ageOneYear() {
		Logger.log(zoo.ageOneYear());
	}

	public static void case11_ResortThePenguins() {
		int choicetest;
		int currentStrategy = zoo.getPenguinHord().getSortFormPenguin();
		Logger.log("The Current Way Of Sorting Is: " + (currentStrategy == 1 ? "Sort By Name [A - Z , a - z]"
				: currentStrategy == 2 ? "Sort By Height [the leader(the tallest) first]"
						: "Sort By Age [youngest first]"));
		Logger.log("Choose One Of The Next Options:");
		Logger.log("\t1) Sort By Name [A - Z , a - z]");
		Logger.log("\t2) Sort By Height [the leader(the tallest) first]");
		Logger.log("\t3) Sort By Age [youngest first]");
		choicetest = checkInputStringToInt();
		zoo.penguinSortWay(choicetest);
	}

	public static int getNewMegaladonAge() throws ExceptionZoo {
//		double getAge;
		int age;
		Logger.log("Enter Megaladon Age[In Integers 0 - " + Megaladon.LifeSpan + "]:");
		do {
			age = checkInputStringToInt();
//			age = (int) getAge;
			if (age < 0 || age > Megaladon.LifeSpan) {
				Logger.log("You cannot Enter this age please try again.");
			}
		} while (age < 0 || age > Megaladon.LifeSpan);
		return age;

	}

	public static double getNewMegaladonWeight() throws ExceptionZoo {
		Logger.log(
				"Enter Megaladon Weight in Tons(" + Megaladon.MinWeight + " < weight <= " + Megaladon.MaxWeight + "):");
		double weight = checkInputStringToDouble();
		while (weight <= Megaladon.MinWeight || weight > Megaladon.MaxWeight) {
			Logger.log("Incorrect Weight Please Enter Again.");
			weight = checkInputStringToDouble();
		}
		return weight;
	}

	public static int getNewMegaladonSpeed() throws ExceptionZoo {
		Logger.log(
				"Enter Megaladon Speed in KMPH(" + Megaladon.MinSpeed + " < Speed <= " + Megaladon.MaxSpeed + "):");
		int speed = checkInputStringToInt();
		while (speed <= Megaladon.MinSpeed || speed > Megaladon.MaxSpeed) {
			Logger.log("Incorrect Speed Please Enter Again.");
			speed = checkInputStringToInt();
		}
		return speed;
	}

	public static int getNewMegaladonLength() throws ExceptionZoo {
		Logger.log(
				"Enter Megaladon Length in kg(" + Megaladon.MinLength + " < Length <= " + Megaladon.MaxLength + "):");
		int length = checkInputStringToInt();
		while (length <= Megaladon.MinLength || length > Megaladon.MaxLength) {
			Logger.log("Incorrect Length Please Enter Again.");
			length = checkInputStringToInt();
		}
		return length;
	}

	public static void AddNewMegaladonToTheZoo() throws ExceptionZoo {
		int age = getNewMegaladonAge();
		int length = getNewMegaladonLength();
		int speed = getNewMegaladonSpeed();
		String name = getNewPredatorName();
		double weight = getNewMegaladonWeight();
		Megaladon Megaladon = new Megaladon(age, length, name, weight, speed);
		zoo.addMegaladon(Megaladon);
		Logger.log("The Megaladon Entered Succesfully! ");
	}

	public static void case12_NewAnimals() throws ExceptionZoo, InterruptedException {
		int newAnimalChoice = -1;
		do {
			Logger.log();
			Logger.log("Welcome To The New Animal Section :)\nChoose One From The Options");
			Logger.log("\t1) See All Eagles");
			Logger.log("\t2) Add An Eagle");
			Logger.log("\t3) See All Cats");
			Logger.log("\t4) Add A Cat");
			Logger.log("\t5) See All Megaladon");
			Logger.log("\t6) Add An Megaladon");
			Logger.log("Enter 0 to get back to the main maneu.");

			newAnimalChoice = checkInputStringToInt();
			switch (newAnimalChoice) {
			case 1:
				SpecialPrints.PhotoPrintingEagle();
				Logger.log();
				Logger.log(zoo.StringOfAllEaglesInTheZoo());
				Logger.log();
				break;
			case 2:
				AddNewEgleToTheZoo();
				Logger.log();
				break;
			case 3:
				SpecialPrints.PhotoPrintingCat();
				Logger.log();
				Logger.log(zoo.returnStringOfAllTheCats());
				Logger.log();
				break;
			case 4:
				AddNewCatToTheZoo();
				Logger.log();
				break;
			case 5:
				SpecialPrints.PhotoPrintingMegaladon();
				Logger.log();
				Logger.log(zoo.stringOfMegaladon());
				Logger.log();
				break;
			case 6:
				AddNewMegaladonToTheZoo();
				Logger.log();
				break;
			default:
				if (newAnimalChoice == 0) {
					Logger.log("MAIN MANEU");
				} else {
					Logger.log("Enter a Valid Option Number.");
					Logger.log();
				}
				break;
			}

		} while (newAnimalChoice != 0);

	}

}
