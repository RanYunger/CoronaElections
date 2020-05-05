package ID318783479_ID316334473;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Set<E> {
	// Constants

	// Fields
	private ArrayList<E> elements;
	private int initialCapacity;
	private int size;

	// Properties (Getters and Setters)
	public ArrayList<E> getElements() {
		return elements;
	}

	private void setCollection(ArrayList<E> collection) {
		this.elements = collection;
	}

	public int getCapacity() {
		return initialCapacity;
	}

	private void setCapacity(int capacity) {
		this.initialCapacity = capacity;
	}

	private void setSize(int size) {
		this.size = size;
	}

	// Constructors
	public Set() {
		this(1);
	}

	public Set(int initialCapcatiy) {
		setCollection(new ArrayList<E>(initialCapcatiy));
		setSize(0);
		setCapacity(initialCapcatiy);
	}

	public Set(Collection<E> collection) {
		setCollection(new ArrayList<E>(collection));
		setSize(collection.size());
		setCapacity(size);
	}

	// Methods
	public boolean add(E e) {
		if (!contains(e)) {
			elements.add(e);
			setSize(elements.size());

			return true;
		}

		return false;
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E e : c)
			add(e);

		return true;
	}

	public void clear() {
		elements.clear();
		setSize(elements.size());
	}

	public boolean contains(Object o) {
		return elements.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	public E get(int index) {
		return elements.get(index);
	}

	public int indexOf(Object o) {
		return elements.indexOf(o);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public Iterator<E> iterator() {
		return elements.iterator();
	}

	public boolean remove(Object o) {
		elements.remove(o);
		setSize(elements.size());

		return true;
	}

	public boolean removeAll(Collection<?> c) {
		elements.removeAll(c);
		setSize(elements.size());

		return true;
	}

	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	public void set(int index, E element) {
		elements.set(index, element);
	}

	public int size() {
		return elements.size();
	}

	public Object[] toArray() {
		return elements.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		result = prime * result + initialCapacity;
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Set<E> other = (Set<E>) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (initialCapacity != other.initialCapacity)
			return false;
		if (size != other.size)
			return false;
		return true;
	}
}
