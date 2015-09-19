/**
 * 
 */
package ramp.algo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Rama Palaniappan
 * @since 19-Sep-2015
 */
public class BinaryTreeLevelOrder {
	
	private final String NEW_LINE = System.getProperty("line.separator");

	/**
	 * Print the tree with each level in a new line; left most node in the tree should be first in the line and right most the last
	 * Sample Input:
	 * 
 					a(6)
				   /   \
				 /       \
				b(3)	   c(9)
			  /   \        /  \
			d(2)   e(5)   f(7)	g(10)
		  /        /       \      \
		h(1)	  i(4)      j(8)   k(11)
	 *
	 * Sample Output:
	 * 6
	 * 3, 9
	 * 2, 5, 7, 10
	 * 1, 4, 8, 11
	 * @return
	 */
	public String printTreeLevelOrder(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("Null input node");
		}
		//Add a root node to a queue
		Queue<Node> current = new LinkedList<Node>();
		current.add(node);
		StringBuilder sb = new StringBuilder();
		
		//Iterate till queue is empty
		while (!current.isEmpty()) {
			//Create a new queue for next level
			Queue<Node> next = new LinkedList<Node>();
			//Populate the 'next' queue and append String buffer
			while (!current.isEmpty()) {
				Node n = current.remove();
				sb.append(n).append(',');
				if (n != null && n.left != null) {
					next.add(n.left);
				}
				if (n != null && n.right != null) {
					next.add(n.right);
				}
			}
			//Remove extra comma
			if (sb != null  && sb.length() > 0) {
				sb.setLength(sb.length()-1);
			}
			//Assign the next queue as current and add a new line to String buffer
			sb.append(NEW_LINE);
			current = next;
		}
		return sb.toString();
	}
}