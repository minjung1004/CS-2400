package assignment2;
//Task 6

public class ArrayStackTest {
    public static void main(String[] args) {
    	ResizeableArrayStack<Integer> testStack = new ResizeableArrayStack<>();
        String postfix = "ab*ca-/de*+";
        int result = testStack.evaluatePostFix(postfix);

        System.out.printf("The solution to \"" + postfix + "\" is: " + result);
    }
}