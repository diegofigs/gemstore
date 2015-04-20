package models;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

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
	
	private Node header;
	private int currentSize;
	
	public SortedCircularDoublyLinkedList(){
		this.header = new Node();
		this.currentSize = 0;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public boolean add(E obj) {
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		if(this.isEmpty()){
			Node newNode = new Node();
			newNode.setData(obj);
			header.setNext(newNode);
			header.setPrev(newNode);
			newNode.setNext(header);
			newNode.setPrev(header);
		}
		else{
			Node temp = null;
			for(temp = header.getNext(); temp != header; temp = temp.getNext()){
				if(temp.getData().compareTo(obj) >= 0)
					break;
			}
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

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		if(this.isEmpty())
			return false;
		Node temp = null;
		for(temp = header.getNext(); temp != header; temp = temp.getNext()){
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

	@Override
	public boolean remove(int index) {
		if(index < 0 || index >= this.currentSize)
			throw new IndexOutOfBoundsException();
		int counter = 0;
		Node temp = null;
		for(temp = this.header.getNext(); counter < index; temp = temp.getNext(), ++counter);
		temp.getNext().setPrev(temp.getPrev());
		temp.getPrev().setNext(temp.getNext());
		temp.setData(null);
		temp.setNext(null);
		temp.setPrev(null);
		temp = null;
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int removed = 0;
		while(this.remove(obj))
			removed++;
		return removed;
	}

	@Override
	public E first() {
		return this.header.getNext().getData();
	}

	@Override
	public E last() {
		return this.header.getPrev().getData();
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= this.currentSize)
			throw new IndexOutOfBoundsException();
		int counter = 0;
		Node temp = null;
		for(temp = header.getNext(); counter < index; temp = temp.getNext(), ++counter);
		return temp.getData();
		
	}

	@Override
	public void clear() {
		while(!this.isEmpty())
			remove(0);
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	@Override
	public int firstIndex(E e) {
		if(e == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		int counter = 0;
		Node temp = null;
		for(temp = header.getNext(); temp != header; temp = temp.getNext(), ++counter){
			if(temp.getData().equals(e))
				return counter;
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		if(e == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		int counter = this.currentSize - 1;
		Node temp = null;
		for(temp = header.getPrev(); temp != header; temp = temp.getPrev(), --counter){
			if(temp.getData().equals(e))
				return counter;
		}
		return -1;
	}

	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}
	

}
