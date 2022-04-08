package assignment2;

public interface StackInterface<T> {
	/**
	 * Adds a new entry to the top of this stack*/
	public void push(T newEntry);
	
	/**
	 * Removes and returns this stack's top entry*/
	public T pop();
	
	/**
	 * Retrieves this stack's top entry without removing it*/
	public T peek();
	
	/**
	 * Detects whether this stack is empty*/
	public boolean isEmpty();
	
	/**
	 * Removes all entries from this stack*/
	public void clear();
	
	public String convertToPostFix(String infix);
	public int evaluatePostFix(String postfix);
}
