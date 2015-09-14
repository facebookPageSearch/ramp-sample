/**
 * 
 */
package ramp.algo;

import java.util.LinkedList;

/**
 * @author Rama Palaniappan
 * @since 14-Sep-2015
 */
public class BTInorderTraversal {
	public String traverse(Node root) {
		String result = null;
		
		result = recursiveInOrderWrapper(root);
//		result = iterativeInOrder(root);
		
		return result;
	}
	
	public String recursiveInOrderWrapper(Node node) {
		StringBuilder sb = new StringBuilder();
		recursiveInOrder(node, sb);
		if (sb != null && sb.length() > 1) {
			sb.setLength(sb.length() - 1);// remove extra comma
		}
		return sb.toString();
	}
	
	private void recursiveInOrder(Node node, StringBuilder sb) {
		if (node == null || sb == null) {
			return;
		}
		recursiveInOrder(node.left, sb);
		sb.append(node.data).append(',');
		recursiveInOrder(node.right, sb);
	}
	
	public String recursiveInOrderWrapper2(Node node) {
		StringBuilder sb = recursiveInOrder(node);
		if (sb != null && sb.length() > 1) {
			sb.setLength(sb.length() - 1);// remove extra comma
		}
		return sb.toString();
	}
	
	private StringBuilder recursiveInOrder(Node node) {
		StringBuilder sb = null;
		if (node == null) {
			return sb;
		}
//		if (node != null && node.left == null) {
//			sb = new StringBuilder(); //bottom left leaf node
//		}
		if (node.left != null) {
			sb = recursiveInOrder(node.left);
		}
		if (sb == null) {
			sb = new StringBuilder(); //bottom left leaf node
		}
		sb.append(node.data).append(',');
		if (node.right != null) {
			sb = recursiveInOrder(node.right);
		}
		return sb;
	}
	
	public String iterativeInOrder(Node node) {
		if (node == null) return null;
		StringBuilder sb = new StringBuilder();
		LinkedList<Node> stack = new LinkedList<Node>();
		while (true) {
			if (node != null) {
				stack.push(node);
				node=node.left;
			} else {
				if (stack.size() > 0) {
					node = stack.pop();
					sb.append(node.data).append(',');
					node = node.right;
				} else {
					break;
				}
			}
		}
		if (sb != null && sb.length() > 1) {
			sb.setLength(sb.length() - 1);// remove extra comma
		}
		return sb.toString();
	}
	
}

class Node {
	protected Node left;
	protected Node right;
	protected int data;
	
	public Node(int data) {
		this(data, null, null);
	}
	
	public Node(int data, Node left, Node right) {
		setData(data);
		setLeft(left);
		setRight(right);
	}
	
	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public Node getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(Node left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public Node getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	
	public String toString() {
		return Integer.toString(data); 
	}
	
	public static Node generateBT() {
/*
 					a(6)
				   /   \
				 /       \
				b(3)	   c(9)
			  /   \        /  \
			d(2)   e(5)   f(7)	g(10)
		  /        /       \      \
		h(1)	  i(4)      j(8)   k(11)
*/		
		Node k = new Node(11);
		Node j = new Node(8);
		Node i = new Node(4);
		Node h = new Node(1);
		Node g = new Node(10, null, k);
		Node f = new Node(7, null, j);
		Node e = new Node(5, i, null);
		Node d = new Node(2, h, null);
		Node c = new Node(9, f, g);
		Node b = new Node(3, d, e);
		Node a = new Node(6, b, c);
		return a;
	}
}
