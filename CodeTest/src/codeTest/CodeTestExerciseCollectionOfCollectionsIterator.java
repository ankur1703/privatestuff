package codeTest;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implement an iterator that iterates all the elements of a collection of collections
 */
public class CodeTestExerciseCollectionOfCollectionsIterator implements Iterator<Object> {

	private Collection<Collection<Object>> _collOfColl = null;
	private Object currentReturn = null;
	private Object next;
	private Iterator<Collection<Object>> outerIterator;
	private Iterator<Object> innerIterator;
	private Collection<Object> nextCollection;
	private boolean hasNext;
	
	/**
	 * Constructor takes in the collection of collections that should be iterated
	 * @param collofColl collection of collections
	 */
	public CodeTestExerciseCollectionOfCollectionsIterator(Collection<Collection<Object>> collofColl) {
		_collOfColl = collofColl;
		
		outerIterator = _collOfColl.iterator(); // get iterator for the outer collection
		if(outerIterator.hasNext()){
			nextCollection = (Collection<Object>)(outerIterator.next()); // get the first collection from the outer iterator
			innerIterator = nextCollection.iterator(); // get iterator from the collection
			prepareNext(); // initial preparation for the first element
		}
	}
	/*
	 * Utility method to prepare next element
	 */
	private void prepareNext(){	
		// checking if the current collection has anymmore elements
		if(innerIterator.hasNext()){
			next = innerIterator.next(); // get the next element
			
			// condition to ignore empty strings
			if(next.toString().equals(""))
				prepareNext();
			
			hasNext = true; 
		}
		else{
			// collection didnt have anymore elements
			// now checking if outerIteration has anymore collections
			if(outerIterator.hasNext()){
				// iterator has more collection, getting the innerIterator for the collection and preparing next
				nextCollection = (Collection<Object>)(outerIterator.next());
				innerIterator = nextCollection.iterator(); 
				prepareNext();
			}
			else{
				// No more elements available
				hasNext = false;
			}
		}
	}
	
	/**
	 * Returns true if the iteration has more elements
	 */
	public boolean hasNext() {		
		return hasNext;
	}

	/**
	 * Returns the next element in the iteration
	 */
	public Object next() {
		// if next is called with no more elements NoSuchElementException is thrown
		if (!hasNext()){
			throw new NoSuchElementException();
		}
		
		// returning next element and preparing for next one.
		currentReturn = next;
		prepareNext();
		return currentReturn;
	}

	/**
	 * Removes from the underlying collection the last element returned by the iterator
	 * For the code test, this does not need to be implemented. 
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
