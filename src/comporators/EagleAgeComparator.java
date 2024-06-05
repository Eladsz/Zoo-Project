package comporators;

import animals.Eagle;
import java.util.Comparator;

public class EagleAgeComparator implements Comparator<Eagle> {
	@Override
	public int compare(Eagle o1, Eagle o2) {
		return Integer.compare(o1.getAge(), o2.getAge());
	}
}
