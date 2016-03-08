/**
 * 
 */
package ramp.sample.worksheetgen.io;

/**
 * @author Rama Palaniappan
 * @since 15-Dec-2015
 */
public class Random {
	public static void main(String[] args) {
		Random random = new Random();
		int runningCount = 0;
		int successCount = 0;
		int failureCount = 0;
		for (int i = 0; i < 100; i++) {
			String status = random.getStatus();
			System.out.println(status);
			
			//Summary Counter
			if (status.equals(RUNNING)) {
				runningCount++;
			} else if (status.equals(FAILED)) {
				failureCount++;
			} else if (status.equals(SUCCESS)) {
				successCount++;
			}
		}
		System.err.println("Summary (out of 100 hits)");
		System.err.println("Running = " + runningCount);
		System.err.println("Success = " + successCount);
		System.err.println("Failed = " + failureCount);
	}

	private enum STATUS {
		RUNNING, SUCCESS, FAILUIRE
	};
	private static final String RUNNING = "Job Running";
	private static final String SUCCESS = "Job Success";
	private static final String FAILED = "Job Failed";

	private String getStatus() {
		STATUS status = getRandom();
		String str = null;
		switch (status) {
		case RUNNING:
			str = RUNNING;
			break;
		case SUCCESS:
			str = SUCCESS;
			break;
		default:
			str = FAILED;
			break;
		}
		return str;
	}

	private STATUS getRandom() {
		int runningProbability = 3; // probability 1/2
		int successProbability = 2;// probability 1/3
		int failureProbability = 1; // prbability 1/6
		int totalPossibilities = 6;

		long rand = System.nanoTime() % totalPossibilities;
		if (rand < runningProbability) {
			return STATUS.RUNNING;
		} else if (rand < runningProbability + successProbability) {
			return STATUS.SUCCESS;
		} else {
			return STATUS.FAILUIRE;
		}
	}
}
