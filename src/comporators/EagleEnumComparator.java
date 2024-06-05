package comporators;

import java.util.Comparator;
import animals.Eagle;

public class EagleEnumComparator implements Comparator<Eagle> {

	@Override
	public int compare(Eagle o1, Eagle o2) {
		return o1.getEagleType().compareTo(o2.getEagleType());
	}

}
