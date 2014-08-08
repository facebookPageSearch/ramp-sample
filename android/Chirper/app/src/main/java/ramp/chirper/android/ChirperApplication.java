package ramp.chirper.android;

import android.app.Application;

import com.intuit.acra.TrafficType;
import com.intuit.acra.sender.CrashReporterSender;
import com.intuit.mobile.alm.Alm;
import com.intuit.mobile.alm.AlmNotification;
import com.intuit.mobile.alm.annotation.AlmInitializationInfo;
import com.intuit.mobile.alm.dto.AlmCustomInitInfo;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by rpalaniappan on 17/07/14.
 */
@AlmInitializationInfo(
        url="https://applifecycle-e2e.platform.intuit.com/v1/notifications",
        //this app's credential may not pushed to e2e
        apiKey="preprdakyresm7eilK4nmZLE6xy88HlfMlwFLMTk", //get api key from service portal
//        apiKey = "preprdakyresY2eGEQql7y915PvHlNIx17ybRdfy",
//        offeringId="7855421242231653664", // get offering id for your app from service portal
        offeringId = "Intuit.platform.crashreporter.chirperandroid")
@ReportsCrashes(
        formUri = "https://crashlog.platform.intuit.com/api/v1/crashes",
//        formKey = "prdakyres1JF2Oeu7islVqM8zn7fpDBmZkgkytX0",//This app's prod key may not be pushed to prod
        formKey = "akyresYQJQLLRgBIoL119ucHzOfpY89OKNWXGDaR",
        maxNumberOfRequestRetries = 10,
        sendReportsInDevMode = true
)
public class ChirperApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        ACRA.getErrorReporter().setReportSender(
                new CrashReporterSender(TrafficType.LIVE));
        Alm.init(this);
    }
}
