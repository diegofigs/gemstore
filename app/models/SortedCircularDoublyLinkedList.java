package models;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The SCDLL is a container class that acts a DoublyLinkedList,
 * but it's elements are sorted and there is only one null node.
 * @author diegofigs
 *
 * @param <E> type argument of the SCDLL, which
 * must implement the Comparable<E> interface
 */
public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {
	/**
	 * The Node class takes care of representing each
	 * object contained in the SCDLL.
	 * @author Manuel
	 *
	 */
	private class Node{
		private Node next, prev;
		private E data;
		
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
	}
	/**
	 * The ListIterator takes care of inspecting
	 * all the elements of the SCDLL from the
	 * first one to the last one.
	 * @author diegofigs
	 *
	 */
	private class ListIterator implements Iterator<E>{
		
		private Node nextNode;
		
		public ListIterator(){
			nextNode = header.getNext();
		}
		
		public ListIterator(int index){
			if(index < 0 || index > currentSize)
				throw new IndexOutOfBoundsException();
			int counter = 0;
			for(nextNode = header.getNext(); counter < index; nextNode = nextNode.getNext(), counter++);
		}

		@Override
		public boolean hasNext() {
			return nextNode != header;
		}

		@Override
		public E next() {
			if(this.hasNext()){
				E result = this.nextNode.getData();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else
				throw new NoSuchElementException();
		}
	}
	/**
	 * The ReverseListIterator takes care of
	 * inspecting all the elements of the SCDLL
	 * from the last one to the first one.
	 * @author diegofigs
	 *
	 */
	public class ReverseListIterator implements ReverseIterator<E>{

		private Node prevNode;
		
		public ReverseListIterator(){
			prevNode = header.getPrev();
		}
		
		public ReverseListIterator(int index){
			if(index < 0 || index > currentSize)
				throw new IndexOutOfBoundsException();
			int counter = 0;
			for(prevNode = header.getNext(); counter < index; prevNode = prevNode.getNext(), counter++);
		}
		
		@Override
		public boolean hasPrevious() {
			return prevNode != header;
		}

		@Override
		public E previous() {
			if(this.hasPrevious()){
				E result = prevNode.getData();
				prevNode = prevNode.getPrev();
				return result;
			}
			else
				throw new NoSuchElementException();
		}
		
	}
	/**
	 * The null header of the SCDLL.
	 */
	private Node header;
	/**
	 * The size of the SCDLL.
	 */
	private int currentSize;
	/**
	 * The main constructor, which initializes
	 * the header node and sets the size to 0.
	 */
	public SortedCircularDoublyLinkedList(){
		this.header = new Node();
		this.currentSize = 0;
	}
	/**
	 * Method that returns a ListIterator
	 * from the first position.
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}
	/**
	 * Add method that takes parameter and adds
	 * it to the SCDLL in the correct slot,
	 * maintaining the list sorted.
	 * @param obj object to be added
	 */
	@Override
	public boolean add(E obj) {
		// Check for null parameter
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		// For first element added only
		if(this.isEmpty()){
			Node newNode = new Node();
			newNode.setData(obj);
			header.setNext(newNode);
			header.setPrev(newNode);
			newNode.setNext(header);
			newNode.setPrev(header);
		}
		else{
			// Finding the correct slot to insert element
			Node temp = null;
			for(temp = header.getNext(); temp != header; temp = temp.getNext()){
				if(temp.getData().compareTo(obj) >= 0)
					break;
			}
			// Insertion
			Node newNode = new Node();
			newNode.setData(obj);
			temp.getPrev().setNext(newNode);
			newNode.setPrev(temp.getPrev());
			temp.setPrev(newNode);
			newNode.setNext(temp);
		}
		this.currentSize++;
		return true;
	}
	/**
	 * Method that returns the size of the SCDLL.
	 */
	@Override
	public int size() {
		return this.currentSize;
	}
	/**
	 * Method that makes an in place removal
	 * without making memory leaks.
	 * @param obj object to be removed
	 */
	@Override
	public boolean remove(E obj) {
		// Check for null parameter
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		// Check for empty list
		if(this.isEmpty())
			return false;
		// Find specified element
		Node temp = null;
		for(temp = header.getNext(); temp != header; temp = temp.getNext()){
			// if found, remove without making memory leaks
			if(temp.getData().equals(obj)){
				temp.getNext().setPrev(temp.getPrev());
				temp.getPrev().setNext(temp.getNext());
				temp.setData(null);
				temp.setNext(null);
				temp.setPrev(null);
				temp = null;
				this.currentSize--;
				return true;
			}
		}
		return false;
	}
	/**
	 * Method that removes the object in the
	 * index slot that is specified by the parameter.
	 * @param index specified index slot to be removed
	 */
	@Override
	public boolean remove(int index) {
		// Check for out of bounds index
		if(index < 0 || index >= this.currentSize)
			throw new IndexOutOfBoundsException();
		// Finding element from index parameter
		int counter = 0;
		Node temp = null;
		for(temp = this.header.getNext(); counter < index; temp = temp.getNext(), ++counter);
		// Removal without making memory leaks
		temp.getNext().setPrev(temp.getPrev());
		temp.getPrev().setNext(temp.getNext());
		temp.setData(null);
		temp.setNext(null);
		temp.setPrev(null);
		temp = null;
		this.currentSize--;
		return true;
	}
	/**
	 * Method that removes all elements
	 * specified by the parameter.
	 * @param obj objects to be removed
	 */
	@Override
	public int removeAll(E obj) {
		int removed = 0;
		while(this.remove(obj))
			removed++;
		return removed;
	}
	/**
	 * Method that returns the first element of the SCDLL.
	 */
	@Override
	public E first() {
		return this.header.getNext().getData();
	}
	/**
	 * Method that returns the last element of the SCDLL.
	 */
	@Override
	public E last() {
		return this.header.getPrev().getData();
	}
	/**
	 * Method that gets the element from
	 * the specified index slot.
	 * @param index index used to return the element
	 */
	@Override
	public E get(int index) {
		// Check for out of bounds index
		if(index < 0 || index >= this.currentSize)
			throw new IndexOutOfBoundsException();
		// Finding element from index parameter
		int counter = 0;
		Node temp = null;
		for(temp = header.getNext(); counter < index; temp = temp.getNext(), ++counter);
		// Return element
		return temp.getData();
		
	}
	/**
	 * Method that empties the whole SCDLL.
	 */
	@Override
	public void clear() {
		while(!this.isEmpty())
			remove(0);
	}
	/**
	 * Method that checks the SCDLL
	 * for any instance of a specific element.
	 * @param e element to be looked for
	 */
	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}
	/**
	 * Method that returns true if the SCDLL
	 * is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}
	/**
	 * Constructor that return a ListIterator
	 * from a specified starting position.
	 * @param index starting position
	 */
	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}
	/**
	 * Method that returns the index of the first
	 * instance encountered of the specified element.
	 * @param e element to be searched for
	 */
	@Override
	public int firstIndex(E e) {
		// Check for null parameter
		if(e == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		// Finding element
		int counter = 0;
		Node temp = null;
		for(temp = header.getNext(); temp != header; temp = temp.getNext(), ++counter){
			if(temp.getData().equals(e))
				// Return position
				return counter;
		}
		return -1;
	}
	/**
	 * Method that returns the index of the last
	 * instance encountered of the specified element.
	 * @param e element to be searched for
	 */
	@Override
	public int lastIndex(E e) {
		// Check for null parameter
		if(e == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		// Finding element
		int counter = this.currentSize - 1;
		Node temp = null;
		for(temp = header.getPrev(); temp != header; temp = temp.getPrev(), --counter){
			if(temp.getData().equals(e))
				// Return position
				return counter;
		}
		return -1;
	}
	/**
	 * Method that returns a ReverseListIterator
	 * from the last position.
	 */
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}
	/**
	 * Method that returns a ReverseListIterator
	 * from the specified position.
	 * @param index specified index position
	 */
	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}
	

}
