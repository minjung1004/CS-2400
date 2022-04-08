package assignment1;

public class Node {
	
	/**
	 * ResizeableArrayBag
	 * 	union
	 * 		Best:O(1) --adds bag2 as the last bag items
	 * 				  --Bag is not full
	 * 				  --assuming that bag2 is empty
	 * 		Worst:O(n) --Bag is full --> resizing is needed
	 * 	intersection
	 * 		Best:O(1)--Bag is not full 
	 * 				 --assuming that there is no intersection
	 * 				
	 * 		Worst:O(n) --Bag is full --> resizing is needed
	 *  difference
	 *  	Best:O(1)--bag is not full
	 *  			--assuming that all items occur again in bag2, thus no difference
	 *  			
	 *  	Worst:O(n)---Bag is full--> resizing is needed
	 *  
	 *  
	 *  LinkedBag
	 *  	union
	 * 		Best:O(1) --add as new first node
	 * 				  --Bag is not full
	 * 				  --assuming that bag2 is empty
	 * 		Worst:O(1) --add as new first node
	 * 	intersection
	 * 		Best:O(1)--adding as new first node
	 * 		Worst:O(n) --searching is needed
	 *  difference
	 *  	Best:O(1)--bag is not full
	 *  			--assuming that all items occur again in bag2, thus no difference
	 *  			
	 *  	Worst:O(n)--searching is needed
	 *  
	 *  
	 *  */

}

