package comporators;

import java.util.Comparator;
import animals.Penguin;

public class PenguinNameComparator implements Comparator<Penguin> {

	@Override
	public int compare(Penguin o1, Penguin o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
