package assignment1;
//Task 3:Implementation of LinkedBag

public class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; //reference to first Node
	/**1.keeps track of number of bag items, currently in the array*/
	private int numberOfEntries;
	
	public LinkedBag()
	{
		firstNode = null;
		numberOfEntries = 0;
	}
	
	
	private class Node
	{
		private T data; //entry in bag
		private Node next;//link to next node
		
		/**Constructors*/
		private Node(T dataPortion)
		{
			this(dataPortion, null);
		}
		private Node(T dataPortions, Node nextNode)
		{
			data = dataPortions;
			next = nextNode;
		}
		/**Accessor and mutator methods*/
		private T getData()
		{
			return data;
		}
		private void setData(T newData)
		{
			data = newData;
		}
		private Node getNextNode()
		{
			return next;
		}
		/*private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}*/
	}

	//gets the number of entries currently in the bag
	public int getCurrentSize() {
		return numberOfEntries;
	}

	//checks whether this bag is empty
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**adds a new entry to this bag*/
	public boolean add(T newEntry) {
		//add to beginning of chain
		Node newNode = new Node(newEntry);
		//Make new node reference rest of chain
		//firstNode is null if chain is empty
		newNode.next = firstNode;
	
		//new node is at beginning of chain
		firstNode = newNode;
		numberOfEntries++;
		
		return true;
	}

	//removes one unspecified entry from this bag, if possible
	public T remove() {
		T result = null;
		if(firstNode != null)
		{
			result = firstNode.getData();
			//remove the first node from chain
			firstNode = firstNode.getNextNode();
			numberOfEntries++;
		}
		return result;
	}

	/**removing a given entry from a bag
	 * 1.search for the given entry in a bag
	 * 2.remove the given entry*/
	
	//locates a given entry within this bag
	//return a reference to the node containing the entry, if located, or null otherwise
	private Node getReferenceTo(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while(!found && (currentNode !=null))
		{
			if(anEntry.equals(currentNode.getData()))
			{
				found =true;
			}
			else
			{
				currentNode = currentNode.getNextNode();
			}
		}
		return currentNode;
	}
	
	/**removes one occurence of a given entry from this bag*/
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		
		if(nodeN != null)
		{
			//replace located entry with entry in first node
			nodeN.setData(firstNode.getData());
			//remove first node
			firstNode = firstNode.getNextNode();
			
			numberOfEntries--;
			result = true;
		}
		
		return result;
	}

	//removes all entries from this bag
	public void clear() {
		while (!isEmpty())
		{
			remove();
		}
		
	}

	/**counts the number of times a given entry appears in this bag*/
	public int getFrequencyOf(T anEntry) {
		int frequency =0;
		
		int counter =0;
		Node currentNode = firstNode;
		
		while((counter < numberOfEntries) && (currentNode != null))
		{
			if(anEntry.equals(currentNode.getData()))
			{
				frequency++;
			}
			
			counter++;
			currentNode = currentNode.getNextNode();
		}
		return frequency;
	}

	//checks whether this bag contains a given entry
	public boolean contains(T anEntry) {
		boolean found =false;
		Node currentNode = firstNode;
		
		while(!found && (currentNode != null))
		{
			if(anEntry.equals(currentNode.getData()))
			{
				found = true;
			}
			else
			{
				currentNode = currentNode.getNextNode();
			}
		}
		
		return found;
	}

	//retrieves all entries that are in this bag
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		
		int index = 0;
		Node currentNode = firstNode;
		while((index < numberOfEntries) && (currentNode != null))
		{
			result[index] =currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	//the new bag with the union of bag1 and bag2
	public BagInterface<T> union(BagInterface<T> otherBag) {
		//create the union bag
		BagInterface<T> unionBag = new LinkedBag<>();
		
		//declare bag1 and bag2 that will union
		int index;
		T[] bag = this.toArray();
		T[] bag2 =otherBag.toArray();
		
		//union of bag1, add entries from bag1 to unionBag
		for(index = 0 ;index< numberOfEntries; index++)
		{
			unionBag.add(bag[index]);
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
		//the count of an item in the intersection is the smaller of the count in each bag
		BagInterface<T> intersectionBag = new LinkedBag<>();
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
		BagInterface<T> differenceBag = new LinkedBag<>();
		
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
