package assignment2;
//Task 3

public class LinkedStackTest {

    public static void main(String[] args) {
    	StackInterface<String> testStack = new LinkedStack<>();
        String infix = "a*b/(c-a)+d*e";
        String postfix = testStack.convertToPostFix(infix);
        System.out.println("Infix: " + infix);
        System.out.print("Postfix: " + postfix);
    }
}
