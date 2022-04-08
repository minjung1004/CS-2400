package prac;

import java.util.Arrays;

public final class Heap<T extends Comparable<? super T>> implements HeapInterface<T> {
	private T[] heap;
	private int lastIndex;
    private boolean initialized =false;
	private static final int DEFAULT_CAPACITY= 100;
	private static final int MAX_CAPACITY = 1000;
	private static int swapCount;
	
	public Heap()
	{
		this(DEFAULT_CAPACITY);
	}
	public Heap(int initialCapacity) {
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized =true;
		swapCount = 0 ;
	}
	

	//for optimal_method
	public Heap(int method, T[] entries) {
		this(entries.length);
		assert initialized = true;
		if(method == 1) {
			for(int index=0;index<entries.length;index++) {
				add(entries[index]);
			}
		}else if(method == 2) {
			for(int index = 0; index <entries.length; index++) {
				heap[index +1] =entries[index];
				lastIndex++;
			}
				
			for(int rootIndex= lastIndex/2; rootIndex > 0; rootIndex--) {
				reheap(rootIndex);
			}
		}
	}

	
	//sequential_insertion
	@Override
	public void add(T newEntry) {
		checkInitialization();
		int newIndex = lastIndex +1;
		int parentIndex = newIndex/2;
		while ((parentIndex > 0)&& newEntry.compareTo(heap[parentIndex])>0) {
			heap[newIndex]=heap[parentIndex];
			newIndex=parentIndex;
			parentIndex=newIndex/2;
			swapCount++;
		}
		heap[newIndex]=newEntry;
		lastIndex++;
		
		ensureCapacity();
	}
	

	@Override
	public T removeMax() {
		checkInitialization();
		T root = null;
		if(!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		return root;
	}

	private void reheap(int rootIndex) {
		boolean done = false;
		
		T orphan = heap[rootIndex];
		int leftChild = 2 * rootIndex;
		while(!done && (leftChild <= lastIndex)) {
			int largerChild = leftChild;
			int rightChild = leftChild +1;
			if((rightChild <= lastIndex) && heap[rightChild].compareTo(heap[largerChild])>0) {
				largerChild=rightChild;
			}
			if(orphan.compareTo(heap[largerChild])<0) {
				heap[rootIndex] = heap[largerChild];
				rootIndex = largerChild;
				leftChild = 2 * rootIndex;
				swapCount++;
			}
			else
				done = true;
		}
		
		heap[rootIndex] = orphan;
	}
	
	@Override
	public T getMax() {
		checkInitialization();
		T root = null;
		if(!isEmpty())
			root = heap[1];
		return root;
	}

	@Override
	public boolean isEmpty() {
		return lastIndex < 1;
	}

	@Override
	public int getSize() {
		return lastIndex;
	}

	@Override
	public void clear() {
		lastIndex =0;
		swapCount = 0;
	}
	 public int getSwapCount() {
		 return swapCount;
	 }
	

	//get 10 insertions
	public String getTen() {
		String ten ="";
		for(int i=1;i<11;i++) {
			ten += heap[i] + ",";
			
		}
		return ten;
	}
	
	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("Heap is not initialized.");
		}
		
	}
	
	private void checkCapacity(int initialCapacity) {
		if(initialCapacity > MAX_CAPACITY)
			throw new IllegalStateException("Exceeds mac heap of "+ MAX_CAPACITY);
			
		
	}
	
	private void ensureCapacity() {
		if(lastIndex >= heap.length) {
			heap =Arrays.copyOf(heap, heap.length*2);
		}
		
	}
	

}
