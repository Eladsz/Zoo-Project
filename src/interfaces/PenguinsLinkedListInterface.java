package interfaces;

import animals.Penguin;

public interface PenguinsLinkedListInterface {
	public int countPenguinsInList();

	public void addPenguin(Penguin penguin);

	public Penguin findByIndex(int index);

	public Penguin findById(String id);

	public void removeById(String id);

	public void removeByIndex(int index);

	public void clear();

	public PenguinsLinkedListInterface copy();

	public String printPenguinList();

	public void sortPenguinByAge(Penguin penguin);

	public void sortPenguinByHeight(Penguin penguin);

	public void sortPenguinByName(Penguin penguin);

	public int getSortFormPenguin();

	public void setSortFormPenguin(int sortFormPenguin);
	
	public Long getLeaderID();
}
