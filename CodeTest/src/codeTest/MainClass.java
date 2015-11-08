package codeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainClass {

	public static void main(String[] args) {
//		CodeTestExerciseCollectionOfCollectionsIteratorTest test = new CodeTestExerciseCollectionOfCollectionsIteratorTest();
//		test.testEmptyIteratorReturnedIfCollectionOfCollectionsIsEmpty();
		
		String[][] contents = {{"a", "b", "c"}, {"d"}, {"e", "f"}}; // {"a", "b", "c"}, {"d"}, {"e", "f"}
		Collection<Collection<Object>> collection = createCollections(contents);
		CodeTestExerciseCollectionOfCollectionsIterator itr = new CodeTestExerciseCollectionOfCollectionsIterator(collection);
		while (itr.hasNext())
			System.out.print(itr.next().toString() + " ");
	}
	
	private static Collection<Collection<Object>> createCollections(Object[][] contents) {
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
