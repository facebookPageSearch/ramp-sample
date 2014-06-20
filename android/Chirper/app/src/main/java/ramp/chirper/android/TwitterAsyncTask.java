package ramp.chirper.android;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.Twitter;

/**
 * Created by rpalaniappan on 18/06/14.
 */
public class TwitterAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {
    Twitter twitter = null;
    Activity activity = null;

    public TwitterAsyncTask(Twitter twitter, Activity activity) {
        this.twitter = twitter;
        this.activity = activity;
    }

    @Override
    protected ArrayList<String> doInBackground(String... objects) {
        ArrayList<String> statusTexts = new ArrayList<String>();
        List<twitter4j.Status> statuses = null;
        try {
            String query = null;
            if (objects != null && objects.length > 0) {
                query = objects[0];
            } else {
                query = "Intuit";
            }
            statuses = twitter.search(new Query(query)).getTweets();

            for (twitter4j.Status s : statuses) {
                statusTexts.add(s.getText());
            }
        } catch (Exception e) {
            statusTexts.add("Twitter query failed: " + e.toString());
        }
        return statusTexts;
    }

    @Override
    protected void onPostExecute(ArrayList<String> o) {
        super.onPostExecute(o);
        ((TweetListActivity)activity).setListViewAdapter(o);
    }
}
