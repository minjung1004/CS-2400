package assignment1;
//Task 1: Bag interface
public interface BagInterface<T>{
	/** gets the current number of entries in this bag*/
	public int getCurrentSize();
	
	/** checks whether this bag is empty*/
	public boolean isEmpty();
	
	/**adds a new entry to the bag*/
	public boolean add(T newEntry);
	
	/**removes one unspecified entry from this bag, if possible*/
	public T remove();
	
	/**removes one occurrence of a given entry from this bag, if possible*/
	public boolean remove(T anEntry);
	
	/**removes all entries from this bag*/
	public void clear();
	
	/**counts the number of times a given entry appears in this bag*/
	public int getFrequencyOf(T anEntry);
	
	/**checks whether this bag contains a given entry*/
	public boolean contains(T anEntry);
	
	/**retrieves all entries that are in this bag*/
	public T[] toArray();


	/**creates a new bag that combines the contents of this bag and a 
	 * second given bag without affecting the original two bags
	 * bag1 or bag2 = unionBag*/
	public BagInterface<T> union(BagInterface<T> otherBag);
	
	/**creates a new bag that contains those objects that occur in both this bag and 
	 * a second bag given bag without affecting the original
	 * bag1 and bag2 = intersectionBag*/
	public BagInterface<T> intersection(BagInterface<T> otherBag);
	
	/**creates a new bag of objects that would be left in this bag after removing those
	 * that also occur in the second given bag
	 * bag1 - bag2 = differenceBag*/
	public BagInterface<T> difference(BagInterface<T> otherBag);
}
//add three methods: union, intersection, and difference
//ADT: a finite collection of objects in no particular order
//	 :can contain duplicate items
//for Bags not set

//UML
/*
*      <<interface>>
*        Bag
* -------------------------
* 
* --------------------------
* +getCurrentSize():integer
* +isEmpty(): boolean
* +add(newEntry: T): boolean
* +remove(): T
* +remove(anEntry: T): boolean
* +clear() :void
* +getFrequencyOf(anENtry: T) integer
* +contains(anEntry: T) :boolean
* +toArray(): T[]
* +union(bag1: BagInterface<T>): BagInterface<T>
* +intersection(bag1: BagInterface<T>): BagInterface<T>
* +difference(bag1: BagInterface<T>): BagInterface<T>
* 
* 
*/
