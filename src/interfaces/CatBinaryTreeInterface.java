package interfaces;

import animals.Cat;

public interface CatBinaryTreeInterface {
	void add(Cat cat);

	String printCatOrdered();

	String ageCatOneYear();

	int countCats();

	double feedCats();

	String makeSound();

	String printTree();

	void clear();
}
