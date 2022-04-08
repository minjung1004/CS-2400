package assignment2;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>{

	//reference the first node in the chain
	private Node topNode;
	
    public LinkedStack(){
        topNode = null;
    }//end default constructor

    
    public void push(T newEntry) {
        //new node references top node
        Node newNode = new Node(newEntry, topNode);
        //make new node the top node
        topNode = newNode;
        //topNode = new Node(newEntry, topNode);
    }

    public T pop() {
    	//might throw EmptyStackException
    	//pop out top item
        T top = peek();
        topNode = topNode.getNextNode();
        return top;
    }

    //peeks top entry ==> return top entry without removing it
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    }

    //check if stack is empty
    public boolean isEmpty() {
        return topNode == null;
    }

    //clears all entries in stack
    public void clear() {
        topNode = null;     
    }

    //create node class
    private class Node
    {
    	private T data; //entry in stack
    	private Node next; // link to next 
    	
    	private Node(T value){
    		this(value, null);
    	}

		private Node(T value, Node link) {
			data = value;
			next = link;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}
		private Node getNextNode() {
			return next;
		}
		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
    }
    
    //convert infix expression to postfix expression
	@Override
	public String convertToPostFix(String infix) {
		//operatorStack = new empty stack
		StackInterface<Character> operatorStack = new LinkedStack<Character>();

        // Initializes the postfix return string
        String postfix = ""; //postfix= new empty stack
        int length = infix.length();
        int index = 0;
        char nextCharacter;
        char topOperator;

        while(index < length){
            //checks if character is a variable (letter)
            nextCharacter = infix.charAt(index);
            boolean isLetter = Character.isLetter(nextCharacter);

            switch(nextCharacter){
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                    // check precedence order
                    // pops top entry from stack
                    while(!operatorStack.isEmpty() && precedenceCheck(nextCharacter) <= precedenceCheck(operatorStack.peek())){
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')':
                    // pops top operator from stack
                    // continue to pop out until ( is on top
                    topOperator = operatorStack.pop();
                    while(topOperator != '('){
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                default:
                    if(isLetter)
                        postfix += nextCharacter;
                    break;
            }
            index++;//increment while loop
        }

        while(!operatorStack.isEmpty()){
            topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        return postfix;
        //System.out.println(postfix.toString());//print postfix
		
	}
	private int precedenceCheck(char operator){
        int value;
        switch(operator){
        //unary operation
            case '+': case '-':
                value = 1;
                break;
        //arithmetic operation
            case '*': case '/':
                value = 2;
                break;
        //logical operation
            case '^':
                value = 3;
            default:
                value = -1;
                break;
        }
        return value;
    }


	@Override
	public int evaluatePostFix(String postfix) {
		// TODO Auto-generated method stub
		return 0;
	}
}
