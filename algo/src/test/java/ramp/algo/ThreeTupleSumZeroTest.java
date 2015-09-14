/**
 * 
 */
package ramp.algo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Rama Palaniappan
 * @since 13-Sep-2015
 */
public class ThreeTupleSumZeroTest {

	private ThreeTupleSumZero threeTupleSumZero = new ThreeTupleSumZero();

	@DataProvider
	public Object[][] dpthreeTuplesSumsToZero() {
		int[] a = new int[] { -1, -2, -3, 0, 1, 2, 3 };
		List<ThreeTuple> expectedA = new ArrayList<ThreeTuple>();
		expectedA.add(new ThreeTuple(-3, 0, 3));
		expectedA.add(new ThreeTuple(-3, 1, 2));
		expectedA.add(new ThreeTuple(-2, 0, 2));
		expectedA.add(new ThreeTuple(-1, 0, 1));
		expectedA.add(new ThreeTuple(-2, -1, 3));

		int[] b = new int[] { 0, 0, 0 };
		List<ThreeTuple> expectedB = new ArrayList<ThreeTuple>();
		expectedB.add(new ThreeTuple(0, 0, 0));

		int[] c = new int[] { 1, 2, 3, 4 };
		List<ThreeTuple> expectedC = null;

		int[] d = new int[] { -1, -2, -3, -3, -4, 0, 1, 2, 3, 4, 4 };
		//Sorted array -4 -3 -3 -2 -1 0 1 2 3 4 4
		//-4 0 4
		//-4 0 4
		//-4 1 3
		//-3 -1 4
		//-3 -1 4
		//-3 0 3
		//-3 1 2
		//-3 -1 4
		//-3 -1 4
		//-3 0 3
		//-3 1 2
		//-2 -1 3
		//-2 0 2
		//-1 0 1
		List<ThreeTuple> expectedD =  new ArrayList<ThreeTuple>();
		expectedD.add(new ThreeTuple(-4,0,4));
		expectedD.add(new ThreeTuple(-4,0,4));
		expectedD.add(new ThreeTuple(-4,1,3));
		expectedD.add(new ThreeTuple(-3,-1,4));
		expectedD.add(new ThreeTuple(-3,-1,4));
		expectedD.add(new ThreeTuple(-3,0,3));
		expectedD.add(new ThreeTuple(-3,1,2));
		expectedD.add(new ThreeTuple(-3,-1,4));
		expectedD.add(new ThreeTuple(-3,-1,4));
		expectedD.add(new ThreeTuple(-3,0,3));
		expectedD.add(new ThreeTuple(-3,1,2));
		expectedD.add(new ThreeTuple(-2,-1,3));
		expectedD.add(new ThreeTuple(-2,0,2));
		expectedD.add(new ThreeTuple(-1,0,1));
		return new Object[][] { 
				{ a, expectedA }, 
				{ b, expectedB },
				{ c, expectedC },
				{ d, expectedD }, };

	}

	@Test(dataProvider = "dpthreeTuplesSumsToZero")
	public void threeTuplesSumsToZero(int[] intArray, List<ThreeTuple> expected) {
		List<ThreeTuple> actual = threeTupleSumZero
				.threeTuplesSumsToZero(intArray);
		Assert.assertEquals(actual, expected);
	}
}
