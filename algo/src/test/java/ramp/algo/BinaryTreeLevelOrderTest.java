/**
 * 
 */
package ramp.algo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Rama Palaniappan
 * @since 19-Sep-2015
 */
public class BinaryTreeLevelOrderTest {

	private BinaryTreeLevelOrder binaryTreeLevelOrder = new BinaryTreeLevelOrder();
	private final String NEW_LINE = System.getProperty("line.separator");
	
	@DataProvider
	public Object[][] dataProvider() {
		Node a = Node.generateBT();
		String expectedA = new StringBuilder()
			.append("6").append(NEW_LINE)
			.append("3,9").append(NEW_LINE)
			.append("2,5,7,10").append(NEW_LINE)
			.append("1,4,8,11").append(NEW_LINE).toString();
		Node b = a.left;
		String expectedB = new StringBuilder()
		.append("3").append(NEW_LINE)
		.append("2,5").append(NEW_LINE)
		.append("1,4").append(NEW_LINE).toString(); 
		Node c = a.right;
		String expectedC = new StringBuilder()
		.append("9").append(NEW_LINE)
		.append("7,10").append(NEW_LINE)
		.append("8,11").append(NEW_LINE).toString();
		return new Object[][] {
			{a, expectedA},
			{b, expectedB},
			{c, expectedC},
		};
	}
	
	@Test(dataProvider="dataProvider")
	public void printTreeLevelOrder(Node node, String expected) {
		String actual = binaryTreeLevelOrder.printTreeLevelOrder(node);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider="dataProvider")
	public void printTreeLevelOrderBetter(Node node, String expected) {
		String actual = binaryTreeLevelOrder.printTreeLevelOrderBetter(node);
		Assert.assertEquals(actual, expected);
	}
}
