package hashtable;

public class HashTableTester {

	public static void main(String[] args) {
		System.out.println("Using Double ");
		System.out.println("BST implemented Hash table with bucket size = 10");
		HashTableIntf<Double> ht = new HashTableUsingBST<>(10);
		ht.add(53.53);
		ht.add(35.35);
		ht.add(99.23);
		ht.add(55.25);

		if (ht.search(19.08)) {
			System.out.println("19.08 is present in hash table.");
		} else {
			System.out.println("19.08 is NOT present in hash table.");
		}
		if (ht.search(55.25)) {
			System.out.println("55.25 is present in hash table.");
		} else {
			System.out.println("55.25 is NOT present in hash table.");
		}
		
		System.out.println("\nAt this line removing 55.25, if present");
		System.out.println(ht.delete(55.25));
		
		if (ht.search(55.25)) {
			System.out.println("55.25 is present in hash table.");
		} else {
			System.out.println("55.25 is NOT present in hash table.");
		}
		if (ht.search(99.23)) {
			System.out.println("99.23 is present in hash table.");
		} else {
			System.out.println("99.23 is NOT present in hash table.");
		}
		
		System.out.println("\n\n\n\nUsing String ");
		System.out.println("BST implemented Hash table with bucket size = 10");
		HashTableIntf<String> htString = new HashTableUsingBST<>(10);
		htString.add("lion");
		htString.add("duck");
		htString.add("elepant");
		htString.add("frog");

		if (htString.search("tiger")) {
			System.out.println("tiger is present in hash table.");
		} else {
			System.out.println("tiger is NOT present in hash table.");
		}
		if (htString.search("lion")) {
			System.out.println("lion is present in hash table.");
		} else {
			System.out.println("lion is NOT present in hash table.");
		}
		
		System.out.println("\nAt this line removing frog, if present");
		System.out.println(htString.delete("frog"));
		
		if (htString.search("frog")) {
			System.out.println("frog is present in hash table.");
		} else {
			System.out.println("frog is NOT present in hash table.");
		}
		if (htString.search("lion")) {
			System.out.println("lion is present in hash table.");
		} else {
			System.out.println("lion is NOT present in hash table.");
		}
	}
}
