package assignment2;

import java.util.Arrays;
import java.util.EmptyStackException;

//a class of stacks whose entries are stored in an array
public class ResizeableArrayStack<T> implements StackInterface<T>{
	 private T[] stack; ///array of stack entries
	 private int topIndex; //index of top entry
	 private static final int DEFAULT_CAPACITY = 50;
	    
	 public ResizeableArrayStack(){
	    this(DEFAULT_CAPACITY);
	 }//end default constructor
	    
	 public ResizeableArrayStack(int intialCapacity){     
		 @SuppressWarnings("unchecked")
	    T[] tempStack = (T[])new Object[intialCapacity];
	    stack = tempStack;
	    topIndex = -1;
	 }//end of constructor
	    

	 //pushes entry to top of stack
	 public void push(T newEntry) {
	     ensureCapacity();
	     stack[topIndex + 1] = newEntry;
	     topIndex++;
	 }//end push


	 //if stack full, ,increase the stack capacity by double
	 private void ensureCapacity(){
	     if(topIndex >= stack.length - 1){ //if array is full, double its size
	         int newLength = 2 * stack.length;
	         stack = Arrays.copyOf(stack, newLength);
	     }//end if
	 }//end ensureCapacity

	 //pops out top entry from stack
	 public T pop() {
	     T top = peek();
	     stack[topIndex] = null;
	     topIndex--;
	     return top;
	 }

	 //retrieves the top entry
	 public T peek() {
	     if(isEmpty())
	         throw new EmptyStackException();
	     else
	         return stack[topIndex];
	 }

	 //checks if stack is empty
	 public boolean isEmpty() {
	     return topIndex < 0;
	 }

	 public void clear() {
	     for(;topIndex > -1;topIndex--)
	        stack[topIndex] = null;
	 }
	 //evaluate the postfix expression string
	    public int evaluatePostFix(String postfix){
	    	//valueStack = a new empty stack
	        StackInterface<Integer> valueStack = new ResizeableArrayStack<Integer>(10);
	        
	        int length = postfix.length();
	        int index = 0;
	        int solution = 0;
	        char nextCharacter;
	        int val;

	        while(index < length){
	        	//checks every char in String == a variable
	            nextCharacter = postfix.charAt(index);
	            boolean isLetter = Character.isLetter(nextCharacter);
	            
	            switch(nextCharacter){
	                case '^': case '+': case '-': case '*': case '/':
	                    int operandOne = valueStack.pop();
	                    int operandTwo = valueStack.pop();
	                    solution = result(operandOne, operandTwo, nextCharacter);
	                    valueStack.push(solution);
	                    break;
	                default:
	                    if(isLetter){
	                        val = valueOfCharacter(nextCharacter);
	                        valueStack.push(val);
	                    }   
	                    break;
	            }
	            index++;//increment while loop
	        }
	        // returns value of stack
	        return valueStack.peek();
	    }

	    //do math operations
	    private static int result(int op1, int op2, char operation){
	        int result;

	        switch(operation){
	            case '+':
	                result = op2 + op1;
	                break;
	            case '-':
	                result = op2 - op1;
	                break;
	            case '*':
	                result = op2 * op1;
	                break;
	            case '/':
	                result = op2 / op1;
	                break;
	            case '^':
	                for(int i = 0; i < op1; i++)
	                    op2 += op2;
	                result = op2;
	                break;
	            default:
	                result = 0;
	        }
	        return result;
	    }
	    
	    //give value to character
	    private static int valueOfCharacter(char check){
	        int result;

	        // Returns value based on char
	        switch(check){
	            case 'a':
	                result = 2;
	                break;
	            case 'b':
	                result = 3;
	                break;
	            case 'c':
	                result = 4;
	                break;
	            case 'd':
	                result = 5;
	                break;
	            case 'e':
	                result = 6;
	                break;
	            default:
	                result = 0;
	                break;
	        }
	        return result;
	    }

		@Override
		public String convertToPostFix(String infix) {
			// TODO Auto-generated method stub
			return null;
		}

 }

