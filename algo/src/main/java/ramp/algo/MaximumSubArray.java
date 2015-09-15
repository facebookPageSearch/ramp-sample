/**
 * 
 */
package ramp.algo;


/**
 * @author Rama Palaniappan
 * @since 15-Sep-2015
 */
public class MaximumSubArray {
	
	/**
	 * Find the longest subset from an int array whose sum of consecutive
	 * elements is max.
	 * 
	 * Finding contiguous subarray within a one-dimensional
	 * array of numbers (containing at least one positive number) which has the
	 * largest sum.
	 * Examples:
	 * ---------------------------------------------------
	 * S.No | Input                     | Output 
	 * ---------------------------------------------------
	 *    1 | [1,2,3,4,5,6,7]           | [1,2,3,4,5,6,7] 
	 *    2 | [-10,6,-1,1,3,0,-9,3,-6]  | [6,-1,1,3,0]
	 *  ---------------------------------------------------
	 * Reference: https://en.wikipedia.org/wiki/Maximum_subarray_problem
	 * 
	 * @param array
	 * @return
	 */
	
	public String getSubset(int[] array) {
		return bruteForceMethod(array);
	}
	
	/**
	 * Time complexity = n^2
	 * @param array
	 * @return
	 */
	public String bruteForceMethod(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int max = Integer.MIN_VALUE;
		String maxSet = null;
		for (int i=0; i<array.length; i++) {
			int sum = array[i];
			if (max <= sum) {
				max = sum;
				maxSet = toString(array, i, i);
			}
			for (int j=i+1; j<array.length; j++) {
				sum += array[j];
				if (max <= sum) {
					max = sum;
					maxSet = toString(array, i, j);
				}
			}
		}
		System.out.println("Maximum subset=" + maxSet + ", Maximum sum=" + max);
		return maxSet;
	}
	
	/**
	 * Time complexity = n
	 * @param array
	 * @return
	 */
	
	//-10,6,-1,1,3,0,-9,3,-6
	
	public String betterMethod1(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int startIndex = 0;
		int endIndex = 0;
		int maxEndingHere = array[startIndex];
		int maxSoFar = array[startIndex];
		for (int i=1; i<array.length; i++) {
			int sum = maxEndingHere + array[i];
			if (array[i] > sum) {
				startIndex = i;//if array[i] is big, then a new sequence is starting from here
				maxEndingHere = array[i];
			} else {
				maxEndingHere = sum;
			}
			if (maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
			}
			if (maxSoFar == maxEndingHere) {
				endIndex = i; // if these are equal, then the subsequence is growing
			}
		}
		return toString(array, startIndex, endIndex);
	}
		
	private String toString(int[] array, int start, int end) {
		StringBuilder sb = new StringBuilder();
		
		for (int i=start; i<=end; i++) {
			sb.append(array[i]);
			sb.append(',');
		}
		if (sb != null && sb.length() > 1) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}
}
