package ramp.chirper.android;

import android.app.Application;

import com.intuit.acra.TrafficType;
import com.intuit.acra.sender.CrashReporterSender;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by rpalaniappan on 17/07/14.
 */
@ReportsCrashes(

        //URL to post crashes
        formUri = "https://crashlog.platform.intuit.com/api/v1/crashes",
//Intuit provided API Key, this is a test api key
//Get API Key for your project from Intuit Services Portal
        formKey = "akyresYQJQLLRgBIoL119ucHzOfpY89OKNWXGDaR",
        //Max number of retries
        maxNumberOfRequestRetries = 10,
        //send crashes even for app that is not signed
        sendReportsInDevMode = true
)
public class ChirperApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        ACRA.getErrorReporter().setReportSender(
                new CrashReporterSender(TrafficType.SANDBOX));    }

}
