package binarytree;

public interface BinarySearchTreeIntf<T> {
	
		void insert(T element);
		boolean search(T element);
		T delete(T element);
		void printBST();
	
}
