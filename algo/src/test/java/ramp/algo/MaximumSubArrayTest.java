/**
 * 
 */
package ramp.algo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Rama Palaniappan
 * @since 15-Sep-2015
 */
public class MaximumSubArrayTest {
	private MaximumSubArray longestSubsetWithMaxSum = new MaximumSubArray();
	
	@DataProvider
	public Object[][] dataProvider() {
		int [] a = {1,2,3,4,5,6,7};
		String expectedA = "1,2,3,4,5,6,7";
		int [] b = {-10,6,-1,1,3,0,-9,3,-6};
		String expectedB = "6,-1,1,3,0";
		int [] c = {-10,2,3,-6,2,3,6,-10,1};
		String expectedC = "2,3,6";
		return new Object[][] {
				{a, expectedA},
				{b, expectedB},
				{c, expectedC},
		};
	}
	
	@Test(dataProvider="dataProvider")
	public void subsetBruteForceMethod(int[] array, String expected) {
		String actual = longestSubsetWithMaxSum.bruteForceMethod(array);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider="dataProvider")
	public void subsetbetterMethod1(int[] array, String expected) {
		String actual = longestSubsetWithMaxSum.betterMethod1(array);
		Assert.assertEquals(actual, expected);
	}
	
}
