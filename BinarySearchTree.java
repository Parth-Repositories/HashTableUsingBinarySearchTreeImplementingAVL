package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeIntf<T> {
	
	BTNode<T> root;
	
//	int leftHeight=0;
//	int rightHeight=0;
	
	public BinarySearchTree() {
		this.root=null;
	}
	

	@Override
	public boolean search(T element) {
		//initialise current as root
		BTNode<T> current = root;
		
		//divide and conquer 
		while(current!=null)	
			//chk less than center node, if true move current to left child
			if(element.compareTo(current.data)<0)
				current=current.left;
			//chk greater than center node, if true move current to right child
			else if(element.compareTo(current.data)>0) 
				current=current.right;
			else 
				//got match condition
				return true;
		
		//return false if match not found
		return false;
	}

	@Override
	public void printBST() {
		if(root==null)
			System.out.println("Empty Tree!!!");
				
		inOrder(root);
	}
	
	public void inOrder(BTNode<T> node) {
		if(node==null)
			return;
		if(node.left!=null)
			inOrder(node.left);
		
		System.out.println(node.data);
		
		if(node.right!=null)
			inOrder(node.right);
	}


								//deleting node from tree
	@Override
	public T delete(T element) 
	{
								//return if tree is empty
		if(root==null)
			return null;
		
								//creating temporary BTBTNode<T> for using compareTo method while traversing
		BTNode<T> tempBTNode = new BTNode<>(element);
		
								//initialize to traverse for finding match
		BTNode<T> current = root;
		BTNode<T> previous = null;
		
		while(current!=null) 
		{
								//if match found break loop and continue with current node as the matched one.
			if(tempBTNode.data.equals(current.data))
				break;
			
								//if not matched increment move the current to left or right child
								//move current to left if smaller search element
								//move current to right if greater search element
			previous=current;
			if(tempBTNode.data.compareTo(current.data)<0)
				current=current.left;
			else
				current=current.right;
		}	
		
		//if match not found, return 
		if(current==null)
			return null;
		
							//if the match found is a leaf node (w/o child) 
							//match (current needs to be deleted) taking care of succeeding links.
		else if(current.right==null && current.left==null) 
		{
								//check if root is the only matched leaf (root as leaf situation)
								//then set root to null 
			if(previous==null)
                root=null;
								//if current(a matched leaf node here) is previous node's right child
								//for deleting current, set previous node's right child as empty.
			else if(current==previous.right)
				previous.right=null;
								//else current(a matched leaf node here) is previous node's left child
								//for deleting current, set previous node's left child as empty.
			else
				previous.left=null;
		}
							//if current(/matched node) has "only" --> right child
		else if(current.right!=null && current.left==null) {
								//if root is matched, 
									//set root as root's right child.
			if(previous==null)
				root=root.right;
								//if matched node is not root
									//if previous node's right is equal to current.
									//set previous node's right as current's right(setting current as free)
			else if(current==previous.right)
				previous.right=current.right;
									//if previous node's left is equal to current.
									//set previous node's left as current's right(setting current as free)
			else
				previous.left=current.right;
		}
		
							//if current(/matched node) has "only" --> left child
		else if(current.right==null && current.left!=null) {
								//if root is matched, 
									//set root as root's left child.
			if(previous==null)
				root=root.left;
								//if matched node is not root
									//if previous node's right is equal to current.
									//set previous node's right as current's left(setting current as free
			else if(current==previous.right)
				previous.right=current.left;
									//if previous node's left is equal to current.
									//set previous node's left as current's left(setting current as free)
			else
				previous.left=current.left;
		}
							//if both child are present with matched node
		else {
			
			//initialize successor nodes for reaching the node having no left child
			BTNode<T> preSuccessor = null;
			BTNode<T> successor = current.right;
            
			//traverse and increment till reaching the node which does not have left child
            while(successor.left!=null){
                preSuccessor = successor;
			    successor = successor.left;
            }
            
            //swapping data of matched node and found successor node
            T temp = successor.data;
            successor.data = current.data;
            current.data = temp;
            
            //if successor is the right child of current(/matched node)
            //then it wont traverse any further so the right child of current is the successor itself
            if(preSuccessor==null){
                current.right=successor.right;
            }
            
            //we already know successor doesn't have left child
            //if it doesn't have right child also, then we set is free.
            else if(successor.right==null) {
                preSuccessor.left=null;
            }
            //else we attach successor's right to ---> successors's previous node's left 
            else
                preSuccessor.left=successor.right;
			
		}	
		return element;
	}
	
	public int height(BTNode<T> node) {
		if(node==null)
			return -1;
		//returns 0 for only root
		//returns -1 for empty tree
		int leftHeight = height(node.left);
	
		int rightHeight = height(node.right);
		
		if(leftHeight>rightHeight)
			return leftHeight+1;
		else
			return rightHeight+1;
		
	}
	
	public void insert(T data) 
    {
        Stack<BTNode<T>> stack = new Stack<>();
        
        //creating new node in heap
        BTNode<T> newBTNode = new BTNode<>(data);
        
        //if tree is empty, then store node as root and stop.
        if(root==null){
            root=newBTNode;
            return;
        }
        
        //if non-empty tree, find the place to store new node in a binary tree algorithm
        //also store each node passed though traversal till storing new node inside a stack
        BTNode<T> previous = null;
        BTNode<T> current = root;
        
        while(current!=null){
            stack.push(current);
            previous=current;
            if(newBTNode.data.compareTo(current.data)<0)
                current=current.left;
            else
                current=current.right;
        }
        if(newBTNode.data.compareTo(previous.data)<0)
            previous.left=newBTNode;
        else
            previous.right=newBTNode;
        stack.push(newBTNode);
        
        //non pop each node from stack
        //and check height difference of left and right subtree of the last popped node.
        current = stack.pop();
        while(Math.abs(height(current.left)-height(current.right))<2){
            
            //if nowhere the height difference is more than 1, then we conclude that the tree is 
            //already balanced and stop.
            if(stack.empty())
                return;
            
            current=stack.pop();
        }
        
        //if loop didn't broke and returned, we can conclude current has height diff more than 1
        //then current should be marked as g
        //next to g in direction towards new node the node must be marked as p
        //similarly x must be marked towards new BTNode<Integer> as next child to p
        StringBuilder rotation = new StringBuilder("");
        BTNode<T> g = current;
        BTNode<T> p;
        if(newBTNode.data.compareTo(current.data)<0){
            rotation.append("L");
            p=g.left;
        }
        else {
            rotation.append("R");
            p=g.right;
        }
        BTNode<T> x;
        if(newBTNode.data.compareTo(p.data)<0){
            rotation.append("L");
            x=p.left;
        }
        else {
            rotation.append("R");
            x=p.right;
        }
        String str = rotation.toString();    
        if(str.equals("LL"))
            rightRotation(g,p);
        else if(str.equals("RR"))
            leftRotation(g,p);
        else if(str.equals("LR")){
            leftRotation(p,x);
            rightRotation(g,p);
        }
        else if(str.equals("RL")){
            rightRotation(p,x);
            leftRotation(g,p);
        }
        
        return;
    }

    public void leftRotation(BTNode<T> parent, BTNode<T> child){
    	T temp=parent.data;
        parent.data=child.data;
        child.data=temp;
        parent.right=child.right;
        child.right=child.left;
        child.left=parent.left;
        parent.left=child;
    }

    public void rightRotation(BTNode<T> parent, BTNode<T> child){
    	T temp=parent.data;
        parent.data=child.data;
        child.data=temp;
        parent.left=child.left;
        child.left=child.right;
        child.right=parent.right;
        parent.right=child;
    }
    
    public void PrintUsingIterativeInorderTraversal(BTNode<T> root) {
    	Stack<BTNode<T>> stack = new Stack<>();
    	BTNode<T> current = root;
    	stack.push(current);
    	do {
    		while(current.left!=null) {
        		stack.push(current);
        		current=current.left;
        	}
    		
    		System.out.println(current.data);
    		
    		while((!stack.empty()) && current.right==null) {
    			current = stack.pop();
    			System.out.println(current.data);
            }
    		
    		current=current.right;
 	
    	} while(!stack.empty());
    	
    }

}

