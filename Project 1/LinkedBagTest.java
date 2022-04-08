package assignment1;
//Task 5: client program for LinkedBag

public class LinkedBagTest {
	public static void main(String[] args)
	{
		//create two array bags
		LinkedBag<String> bag1 = new LinkedBag<>();
		LinkedBag<String> bag2 = new LinkedBag<>();
		
		//array items
		bag1.add("a");
		bag1.add("b");
		bag1.add("c");
		bag2.add("b");
		bag2.add("b");
		bag2.add("d");
		bag2.add("e");
		
		//display union, intersection, difference1, and difference2
		BagInterface<String> everything = bag1.union(bag2);
		System.out.println("LinkedBag: Union");
		displayBag(everything);
		
		BagInterface<String> commonItems = bag1.intersection(bag2);
		System.out.println("LinkedBag: Intersection");
		displayBag(commonItems);
		
		BagInterface<String> leftOver1 = bag1.difference(bag2);
		System.out.println("LinkedBag: Difference 1");
		displayBag(leftOver1);
		
		
		BagInterface<String> leftOver2 = bag2.difference(bag1);
		System.out.println("LinkedBag: Difference 2");
		displayBag(leftOver2);
			
	}
	//tests the method toArray while displaying the bag
	private static void displayBag(BagInterface<String> aBag)
	{
		if (aBag == null) {
            System.out.println("The bag is empty");
            return;
        }
		 Object[] bagArray = aBag.toArray();
	      for (int index = 0; index < bagArray.length; index++)
	      {
	         System.out.print(bagArray[index] + " ");
	      } // end for
	      
	      System.out.println("\n");
	}
}
