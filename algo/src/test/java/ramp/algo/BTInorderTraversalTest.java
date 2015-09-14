/**
 * 
 */
package ramp.algo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Rama Palaniappan
 * @since 14-Sep-2015
 */
public class BTInorderTraversalTest {
	BTInorderTraversal inorderTraversal = new BTInorderTraversal();
	
	@DataProvider
	public Object[][] dptraverse() {
		Node a = Node.generateBT();
		String inOrderA = "1,2,3,4,5,6,7,8,9,10,11";
		Node b = a.left;
		String inOrderB = "1,2,3,4,5";
		Node c = a.right;
		String inOrderC = "7,8,9,10,11";
		return new Object[][] {
				{a, inOrderA},
				{b, inOrderB},
				{c, inOrderC},
		};
	}
	
	@Test(dataProvider="dptraverse")
	public void recursiveInOrderWrapper(Node root, String expected) {
		String actual = inorderTraversal.recursiveInOrderWrapper(root);
		Assert.assertEquals(actual, expected);
	}

	@Test(dataProvider="dptraverse")
	public void recursiveInOrderWrapper2(Node root, String expected) {
		String actual = inorderTraversal.recursiveInOrderWrapper2(root);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider="dptraverse")
	public void iterativeInOrder(Node root, String expected) {
		String actual = inorderTraversal.iterativeInOrder(root);
		Assert.assertEquals(actual, expected);
	}

}