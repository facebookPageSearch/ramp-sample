package ramp.chirper.android;

/**
 * Created by rpalaniappan on 18/07/14.
 */
public class NullChecker {

    public void verify(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Null or empty search query");
        }
    }
}
