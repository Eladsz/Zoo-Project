package comporators;

import java.util.Comparator;
import animals.Cat;

public class CatAgeComperator implements Comparator<Cat> {

	@Override
	public int compare(Cat o1, Cat o2) {
		return Integer.compare(o1.getAge(), o2.getAge());
	}

}
