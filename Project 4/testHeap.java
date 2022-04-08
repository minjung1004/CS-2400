package prac;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class testHeap {
	public static void main(String[] args) {
		Integer[] list = new Integer[100];

		try {
            int i=0;
            File data = new File("data.txt");
            Scanner src = new Scanner(data);
            while (src.hasNextLine()) {
                   list[i++] =Integer.parseInt(src.nextLine());
            	
            }
            src.close();
            
		} catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
            e.printStackTrace();
		}
		
		try {
			PrintWriter f= new PrintWriter("output.txt");
			
			Heap<Integer> sequential_insertion = new Heap<Integer>(1,list);
			
			f.print("==================================================================================\n");
			f.print("Heap built using sequential insertions: ");
			f.print(sequential_insertion.getTen() + "...\n");
			f.print("Number of swaps in the heap creation: " + sequential_insertion.getSwapCount());
			f.print("\nHeap after 10 removals:" );
			for(int i=1;i<11;i++) {
				sequential_insertion.removeMax();
								
			}
			f.print(sequential_insertion.getTen() + "...\n\n") ;
			
			Heap<Integer> optimal_method = new Heap<Integer>(2,list);
			
			f.print("Heap built using optimal method: ");
			f.print(optimal_method.getTen() + "...\n");
			f.print("Number of swaps in the heap creation: " + optimal_method.getSwapCount());
			f.print("\nHeap after 10 removals:" );
			for(int i=1;i<11;i++) {
				optimal_method.removeMax();
								
			}
			f.print(optimal_method.getTen() + "...\n") ;
			f.print("==================================================================================");
			f.close();
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: File not opened to write in.");
		}
				
		
	}

}
