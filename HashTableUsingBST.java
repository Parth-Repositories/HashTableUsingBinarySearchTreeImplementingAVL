package hashtable;
import java.lang.reflect.Array;

import binarytree.*;

public class HashTableUsingBST<T extends Comparable<T>> implements HashTableIntf<T> {
	
	BinarySearchTree<T> buckets[];

	@SuppressWarnings("unchecked")
	public HashTableUsingBST(int size) {
		buckets = (BinarySearchTree[]) Array.newInstance(BinarySearchTree.class, size);
		for(int i=0;i<size;i++) {
			buckets[i]=new BinarySearchTree<>();
		}
	}

	private int modNHashFunction(int key) {
		return key % buckets.length;
	}

	private int hashFunction(int key) {
		return (modNHashFunction(key));
	}

	@Override
	public void add(T element) {
		int bucketId = hashFunction(Math.abs(element.toString().hashCode()));
		buckets[bucketId].insert(element);
		System.out.println("Element " + element + " stored in bucketId " + bucketId);
	}

	@Override
	public boolean search(T element) {
		//using inbuilt toString's hashcode generating algorithm (s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1])
		//don't confuse that we are using hashcode method directly for Node itself.
		//Node's toString is also overridden.
		int bucketId = hashFunction(Math.abs(element.toString().hashCode()));
		if (buckets[bucketId].search(element))
			return true;
		return false;
	}
	
	@Override
	public T delete(T element) {
		//using inbuilt toString's hashcode generating algorithm (s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1])
		//don't confuse that we are using hashcode method directly for Node itself.
		//Node's toString is also overridden.
		int bucketId = hashFunction(Math.abs(element.toString().hashCode()));
		return buckets[bucketId].delete(element);
	}
}
