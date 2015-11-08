package codeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

/**
 * Implement a set of tests to test your implementation of the collection of collections iterator
 */
public class CodeTestExerciseCollectionOfCollectionsIteratorTest extends TestCase {

	/**
	 * Sample test - test that an empty iterator is returned if the collection of collections 
	 * has no contents
	 */
	public void testEmptyIteratorReturnedIfCollectionOfCollectionsIsEmpty() {
		String[][] contents = {}; // {"a", "b", "c"}, {"d"}, {"e", "f"}
		Collection<Collection<Object>> collection = createCollections(contents);
		assertTrue(!new CodeTestExerciseCollectionOfCollectionsIterator(collection).hasNext());
	}
	
	/**
	 * Test - to check empty collections handled correctly
	 */
	public void testNoNullReturnedIfCollectionOfCollectionsHasNullCollection() {
		String[][] contents = {{"a", "b", "c"}, {}, {"e", "f"}}; 
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		while (iterator.hasNext()){
			assertTrue(!iterator.next().equals(null));
		}		
	}
	/**
	 * Test - to check empty string are neglected
	 */
	public void testNoEmptyStringReturnedIfCollectionOfCollectionsHasEmptyString() {
		String[][] contents = {{"a", "b", ""}, {"d"}, {"e", "f"}};
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		while (iterator.hasNext()){
			assertTrue(!iterator.next().toString().equals(""));
		}	
	}
	
	/**
	 * Test - to check first element is returned correctly
	 */
	public void testFirstElementReturnedFromCollectionOfCollections() {
		String[][] contents = {{"a", "b", "c"}, {"d"}, {"e", "f"}};
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		assertTrue(iterator.next().toString().equals("a"));
			
	}
	/**
	 * Test - to check last element is returned correctly
	 */
	public void testLastElementReturnedFromCollectionOfCollections() {
		String[][] contents = {{"a", "b", "c"}, {"d"}, {"e", "f"}};
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		String lastElement = "";
		while (iterator.hasNext()){
			lastElement = iterator.next().toString();
		}
		assertTrue(lastElement.equals("f"));
	}
	/**
	 * Test - to check special character is returned correctly
	 */
	public void testElementReturnedHasSpecialCharecterFromCollectionOfCollections() {
		String[][] contents = {{"%", "b", "c"}, {"d"}, {"e", "f"}};
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		assertTrue(iterator.next().toString().equals("%"));
			
	}
	/**
	 * Test - to check all elements are returning correctly
	 */
	public void testEveryElementReturnedFromCollectionOfCollections() {
		String[][] contents = {{"a", "b", "c"}, {"d"}, {"e", "f"}};
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator iterator = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		String element = "";
		for (int i = 0; i < 3; i++)
			for (String stringElement: contents[i]){
				element = iterator.next().toString();
				assertTrue(element.equals(stringElement));
			}
	}

	/**
	 * Utility method that takes a 2-dimensional Object array and returns it as a collection
	 * of collections. 
	 * For example, if contents = {{"a", "b"}, {"c"}}, 
	 * then the returned result would be a Collection containing two Collections:
	 * index 0: Collection containing "a", "b"
	 * index 1: Collection containing "c"
	 * 
	 * @param contents 2-dimensional Object array
	 * @return collection of collections containing the contents of the array
	 */
	private Collection<Collection<Object>> createCollections(Object[][] contents) {
		Collection<Collection<Object>> result = new ArrayList<Collection<Object>>(contents.length);
		for (int i = 0; i < contents.length; i++) {
			if (contents[i] == null) {
				result.add(null);
			} else {
				result.add(Arrays.asList(contents[i]));
			}
		}
		return result;
	}

}
