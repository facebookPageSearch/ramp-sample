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
	 * This impl is with two queues, current and next.
	 * @return
	 */
	public String printTreeLevelOrder(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("Null input node");
		}
		// Add a root node to a queue: current
		Queue<Node> current = new LinkedList<Node>();
		current.add(node);
		StringBuilder sb = new StringBuilder();

		// Create a new queue for next level
		Queue<Node> next = new LinkedList<Node>();

		// Iterate till queue is empty
		while (!current.isEmpty()) {
			Node n = current.remove();
			if (n != null) {
				sb.append(n.data).append(',');
				next.add(n.left);
				next.add(n.right);
			}
			if (current.isEmpty() && !next.isEmpty()) {
				// Swap current and next
				Queue<Node> tmp = current;
				current = next;
				next = tmp;

				// Remove extra comma
				if (sb != null && sb.length() > 0) {
					sb.setLength(sb.length() - 1);
				}
				// Add a new line to String Buffer
				sb.append(NEW_LINE);
			}
		}
		return sb.toString();
	}
	
	/**
	 * This is with only a single queue
	 * @param node
	 * @return
	 */
	public String printTreeLevelOrderBetter(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("Null input node");
		}
		// Add a root node to a queue
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		StringBuilder sb = new StringBuilder();

		int numNodesInCurrent = 1;
		int numNodesInNext = 0;
		
		// Iterate till queue is empty
		while (!queue.isEmpty()) {
			Node n = queue.remove();
			numNodesInCurrent--;
			if (n != null) {
				sb.append(n.data).append(',');
				queue.add(n.left);
				queue.add(n.right);
				numNodesInNext += 2;
			}
			if (numNodesInCurrent == 0) {
				//Reset counters
				numNodesInCurrent = numNodesInNext;
				numNodesInNext = 0;
				
				// Remove extra comma
				if (sb != null && sb.length() > 0) {
					sb.setLength(sb.length() - 1);
				}
				// Add a new line to String Buffer
				sb.append(NEW_LINE);
			}
		}
		return sb.toString();
	}

}