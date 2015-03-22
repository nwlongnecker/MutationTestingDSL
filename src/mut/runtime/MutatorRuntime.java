package mut.runtime;

import java.util.Collection;

public class MutatorRuntime {

	public static void printList(String title, Collection<String> items) {
		System.out.print(title);
		boolean first = true;
		for(String item : items) {
			if (first) {
				first = false;
			} else {
				System.out.print(", ");
			}
			System.out.print(item);
		}
		System.out.println();
	}
}
