package interfaces;

import animals.Eagle;
import comporators.EagleAgeComparator;
import comporators.EagleCompareSpeed;
import comporators.EagleTopAltitudeComparator;

public interface EagleLinkedListInterface {
	public int countEaglesInList();

	public EagleTopAltitudeComparator compareAlt = new EagleTopAltitudeComparator();

	public EagleCompareSpeed compareSpd = new EagleCompareSpeed();

	public EagleAgeComparator compareAge = new EagleAgeComparator();

	public void addEagle(Eagle eagle);

	public Eagle findByIndex(int index);

	public Eagle findById(Long id);

	public void removeById(Long id);

	public void removeByIndex(int index);

	public void clear();

	public EagleLinkedListInterface copy();

	public String printEagleList();

	public int comparationTest(Eagle eagle1, Eagle eagle2);

	public Long getLeaderID();
}
