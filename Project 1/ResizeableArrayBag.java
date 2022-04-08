package assignment1;
//Task 2:Implementation of ArrayBag

import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T> {
	/**Private Data Fields*/
	
	//bag points to the array
	private T[] bag;
	//sets the default array size to 30
	private static final int DEFAULT_CAPACITY =30;
	//sets the max capacity size
	private static final int  MAX_CAPACITY = 100000;
	/**1.keeps track of number of bag items, currently in the array*/
	private int numberOfEntries;
	
	/**creates an empty bag whose initial capacity is 30
	 * this is the default constructor
	 * DEFAULT_CAPACITY calls the second constructor capacity*/
	public ResizeableArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	/**creates an empty bag having a given initial capacity*/
	public ResizeableArrayBag(int capacity) {
		//memory allocation
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[capacity];
		//data field initialization
		bag = tempBag;
		numberOfEntries = 0; 
	}

	/**gets the current number of entries in the bag*/
	public int getCurrentSize() {
		return numberOfEntries;
	}

	/**checks whether the bag is empty*/
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	//Adds a new entry to this bag
	public boolean add(T newEntry) {
		/**if the bag is full, we cannot add anything to it --> return false
		 * else, add newEntry after the last entry in the array bag*/
		boolean result = true;
		if(isFull())
		{
			doubleCapacity();
		}
		else
		{
			bag[numberOfEntries] = newEntry;
			numberOfEntries++;
		}
		return result;
	}
	
	//doubles the six of  array bag
	private void doubleCapacity() 
	{
		int newLength = 2* bag.length;
		checkCapacity(newLength);
		bag = Arrays.copyOf(bag,  newLength);
	}
	//throws an exception if the client requests a capacity that is too large
	private void checkCapacity(int capacity) {
		if(capacity >  MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose" + 
									"capacity exceeds allowed" +
									"maximum of" +  MAX_CAPACITY);
		
	}

	/**check whether the bag is full return true if the bag is full*/
	public boolean isFull()
	{
		return numberOfEntries == bag.length;
	}
	
	/**retrieves the entries that are in the bag
	 * returns them to the client within a newly allocated array*/
	public T[] toArray() {
		//creates a new array of size numberOfEntries
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		//copies the array
		for (int index = 0; index < numberOfEntries; index++)
		{
			result[index] = bag[index];
		}
		//returns the newly formed array
		return result;
	}

	//removes all entries form this bag
	public boolean remove(T anEntry) {
		/**1. searching for the entry*/
		int index = getIndexOf(anEntry);
		/** 2. removes one occurrence of the entry from the bag*/
		T result = removeEntry(index);
		return anEntry.equals(result);
	}
	
	private int getIndexOf(T anEntry) {
		/**locates the given entry within the array bag
		 * returns the index of the entry, if located or -1
		 * */
		int where = -1;
		boolean found = false;
		int index =0;
		
		while(!found && (index < numberOfEntries))
		{
			if(anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			}
			index++;
		}
		/**if where > -1, anEntry is in the array bag, and it equals bag[where]
		 * else anEntry is not the array*/
		return where;
	}
	
	private T removeEntry(int givenIndex) {
		/**removes and return the entry at a given index within the array bag
		 * if no such entry exist, return null
		 * 0 <= givenIndex < numberOfEntries*/
		T result = null;
		
		if(!isEmpty() && (givenIndex >= 0))
		{
			//find entry to remove
			result = bag[givenIndex];
			//copy array: givenIndex.replace entry with last entry
			bag[givenIndex] = bag[numberOfEntries -1];
			//remove last entry
			bag[numberOfEntries -1] = null;
			numberOfEntries--;
		}
		return result;
	}
	
	
	
	
	/**removes unspecified entry= remove the last entry*/
	public T remove() {
		T result = removeEntry(numberOfEntries -1);
		return result;
	}
	
	/**removes all entries from the bag*/
	public void clear() {
		
		while(!isEmpty())
		{
			remove();
		}
	}
	
	public int getFrequencyOf(T anEntry)
	{
		/**counts the number of times a given entry appears in this bag*/
		int counter = 0;
		
		for(int index = 0; index < numberOfEntries; index++)
		{
			if(anEntry.equals(bag[index]))
			{
				counter++;
			}
		}
		return counter;
	}


	public boolean contains(T anEntry) {
		/**checks whether the bag contains a given entry*/
		return getIndexOf(anEntry) >= 0;
	}

	
	// new bag with the union of bag1 and bag2
	public BagInterface<T> union(BagInterface<T> otherBag) {
		//create the union bag
		BagInterface<T> unionBag = new ResizeableArrayBag<T>();
		
		//declare bag1 and bag2 that will union
		int index;
		T[] bag1 = this.toArray();
		T[] bag2 = otherBag.toArray();
				
		//union of bag1, add entries from bag1 to unionBag
		for(index = 0 ;index< numberOfEntries; index++)
		{
			unionBag.add(bag1[index]);
		}
		//union of bag2, add entries from bag2 to unionBag	
		for(index = 0; index< otherBag.getCurrentSize(); index++)
		{
			unionBag.add(bag2[index]);
		}
		return unionBag;
	}
	
	//create a new bag (intersectionBag) that contains the intersection of bag1 and bag2
	public BagInterface<T> intersection(BagInterface<T> otherBag) {
		//create the intersection bag
		BagInterface<T> intersectionBag = new ResizeableArrayBag<T>();
		
		//declare the bag1 and bag2
		int index;
		int j_index;
		T[] bag1 = this.toArray();
		T[] bag2 = otherBag.toArray();
		//copy of other bag
        T copyOfOtherBag;
    
        //copies the array of otherBag to bag1
        for(index = 0; index < bag1.length; index++) {
        	copyOfOtherBag = bag1[index];
        	
            for (j_index = 0; j_index < bag2.length; j_index++) {
            	//compares the copy of other bag to bag2
                if (copyOfOtherBag == bag2[j_index]) 
                {
                	//add to intersectionBag each item in this bag that matches an item in another bag
                    intersectionBag.add(copyOfOtherBag);
                    bag2[j_index] = null;
                    break;
                }
            }
        }
        
        return intersectionBag;
		
	}


	//creates a differenceBag that contains the difference between bag1 and bag2
	public BagInterface<T> difference(BagInterface<T> otherBag) {
		//creates the differenceBag
		BagInterface<T> differenceBag = new ResizeableArrayBag<T>();
			
		//declares bag1 and bag2
		int index;
		T[] bag1 = this.toArray();
		T[] bag2 = otherBag.toArray();
		//difference of bag1, add entries from bag1 to differenceBag
		for(index = 0 ;index< numberOfEntries; index++)
		{
			differenceBag.add(bag1[index]);
		}
			
		//removes the items that also occur in bag2     
	    for(index=0 ; index< bag2.length; index++)
	    {
			if(differenceBag.contains(bag2[index]))
			{
				differenceBag.remove(bag2[index]);
			}
		}
		  
		return differenceBag;
			
		}
		
	}
