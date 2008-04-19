package p79068.datastruct;

import java.util.Iterator;


public class ListView<E> implements List<E> {
	
	private List<E> list;
	
	
	
	public ListView(List<E> list) {
		this.list = list;
	}
	
	
	
	public int length() {
		return list.length();
	}
	
	
	public E getAt(int index) {
		return list.getAt(index);
	}
	
	
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public void append(Object obj) {
		throw new UnsupportedOperationException();
	}
	
	
	public void appendList(List<? extends E> list) {
		throw new UnsupportedOperationException();
	}
	
	
	public Collection<E> asCollection() {
		return new ListCollectionAdapter<E>(this);
	}
	
	
	public void clear() {
		throw new UnsupportedOperationException();
	}
	
	
	public void insert(int index, Object obj) {
		throw new UnsupportedOperationException();
	}
	
	
	public void insertList(int index, List<? extends E> list) {
		throw new UnsupportedOperationException();
	}
	
	
	public E removeAt(int index) {
		throw new UnsupportedOperationException();
	}
	
	
	public void removeRange(int offset, int length) {
		throw new UnsupportedOperationException();
	}
	
	
	public void setAt(int index, Object obj) {
		throw new UnsupportedOperationException();
	}
	
}