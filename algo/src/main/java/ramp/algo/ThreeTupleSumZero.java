/**
 * 
 */
package ramp.algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rama Palaniappan
 * @since 13-Sep-2015
 */
public class ThreeTupleSumZero {

	public List<ThreeTuple> threeTuplesSumsToZero(int[] intArray) {
		if (intArray.length < 3) {
			return null;
		} else if (intArray.length == 3) {
			ThreeTuple threeTuple = new ThreeTuple(intArray[0], intArray[1],
					intArray[2]);
			if (threeTuple.sum() == 0) {
				List<ThreeTuple> list = new LinkedList<ThreeTuple>();
				list.add(threeTuple);
				return list;
			} else {
				return null;
			}
		}
		List<ThreeTuple> result = null;
		result = bruteForceMethod(intArray);
		System.out.println(result);
		return result;
	};

	public List<ThreeTuple> bruteForceMethod(int[] intArray) {
		Arrays.sort(intArray);
		List<ThreeTuple> threeTuples = new LinkedList<ThreeTuple>();
		for (int i = 0; i < intArray.length - 2; i++) {
			for (int j = i + 1; j < intArray.length - 1; j++) {
				for (int k = j + 1; k < intArray.length; k++) {
					if (intArray[i] + intArray[j] + intArray[k] == 0) {
						threeTuples.add(new ThreeTuple(intArray[i],
								intArray[j], intArray[k]));
					}
				}
			}
		}
		if (threeTuples.size() == 0) {
			return null;
		}
		return threeTuples;
	}
	
	public List<ThreeTuple> betterMethod1(int[] intArray) {
		Arrays.sort(intArray);
		List<ThreeTuple> threeTuples = new LinkedList<ThreeTuple>();
		for (int i=0; i<intArray.length; i++) {
			int j=i+1;
			int k=intArray.length-1;
			while (j<k) {
				int sum = intArray[i]+ intArray[j] + intArray[k];
				if (sum == 0) {
					ThreeTuple tt = new ThreeTuple(intArray[i],
							intArray[j], intArray[k]);
					threeTuples.add(tt);
					while (intArray[j] == intArray[++j] && j<k) {
						threeTuples.add(tt);
					}
					while (intArray[k] == intArray[--k] && j<k) {
						threeTuples.add(tt);
					}
				} else if (sum > 0) {
					//sum > 0 -> max is less, so move j forward
					while (intArray[j] == intArray[j+1] && j<k) { //Take care of identical numbers
						j++;
					}
				} else {
					//sum < 0 -> max is more, so move k backward
					while (intArray[k] == intArray[k-1] && j<k) { //Take care of identical numbers
						k--;
					}
				}
			}
		}
		if (threeTuples.size() == 0) {
			return null;
		}
		return threeTuples;
	}
}

class ThreeTuple {
	int v1 = 0;
	int v2 = 0;
	int v3 = 0;

	public ThreeTuple(int v1, int v2, int v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public int sum() {
		return v1 + v2 + v3;
	}

	public boolean equals(Object o) {
		if (!(o instanceof ThreeTuple)) {
			return false;
		}
		ThreeTuple toCompare = (ThreeTuple) o;
		return this.sum() == toCompare.sum();
	}

	public String toString() {
		return "(" + v1 + "," + v2 + "," + v3 + ")\n";
	}
}
