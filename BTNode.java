package binarytree;


public class BTNode<T> {
	public T data;
	public BTNode<T> left;
	public BTNode<T> right;
	
	public BTNode(T data) {
		this.data=data;
		this.left=null;
		this.right=null;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
