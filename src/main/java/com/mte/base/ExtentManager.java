package com.mte.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

/**
 * Created by Dell on 2017/3/4.
 */
public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true, NetworkMode.OFFLINE);

            extent
                    .addSystemInfo("Host Name", "Test")
                    .addSystemInfo("Environment", "Tester");
        }else{
            System.out.println("The extend is not null");
        }

        return extent;
    }
}
