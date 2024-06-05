package comporators;

import java.util.Comparator;
import animals.Eagle;

public class EagleCompareSpeed implements Comparator<Eagle> {

	@Override
	public int compare(Eagle o1, Eagle o2) {
		return Integer.compare(o1.getSpeed(), o2.getSpeed());
	}

}
