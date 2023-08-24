package telran.interviews;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Stack;
//Requirement: all methods must have complexity O[1]

public class MyStack<T extends Comparable<T>> {
	//TODO insert the required fields
    private final Stack<T> mainStack = new Stack<>();
    private final Stack<T> maxStack = new Stack<>();
    private final Comparator<T> comparator;
	public MyStack(Comparator<T> comp) {
		//TODO comparator for comparing two objects of a class T
	    this.comparator = comp;
	}
	public MyStack() {
		//TODO for comparing in the natural order (Comparable)
	    this.comparator = Comparator.naturalOrder();
	}
	
  public void push(T element) {
	  //TODO adds element to the stack's top (last element of the stack)
      mainStack.push(element);
      if (maxStack.isEmpty() || comparator.compare(element, maxStack.peek()) > 0) {
          maxStack.push(element);
      } else {
          maxStack.push(maxStack.peek());
      }
  }
  public T pop() {
	  //TODO removes the stack's top element and returns it out
	  //In the case no elements exist in the stack the method throws exception NoSuchElementException
      if (isEmpty()) {
          throw new NoSuchElementException("The stack is empty.");
      }
      maxStack.pop();
      return mainStack.pop();
  }
  public boolean isEmpty() {
	  //TODO returns true if the stack is empty otherwise false
	  return mainStack.isEmpty();
  }
  public T getMax() {
	  //TODO returns maximal element from the stack
	  //In the case no elements exist in the stack the method throws exception NoSuchElementException
      if (isEmpty()) {
          throw new NoSuchElementException("The stack is empty.");
      }
      return maxStack.peek();
  }
}
