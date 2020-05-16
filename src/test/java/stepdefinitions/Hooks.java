package stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import centauro.screen.DriverFactory;
import centauro.screen.HomeScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {
	protected static AppiumDriver<MobileElement> driver;
	public static HomeScreen homeScreen;
	
	@BeforeStep
	public void beforeStep(Scenario scenario) {
		saveScreenshotPNG(scenario.getName());
	}
	
	@AfterStep
	public void afterStep(Scenario scenario) {
		saveScreenshotPNG(scenario.getName());
	}
	
	@After
	public void after() {
		DriverFactory.quitDriver();
	}

	protected void saveScreenshotPNG(String stepName){
		Date date = new Date();
		String stepNameTrimmed = stepName.replaceAll("[\\s|\\u00A0]+", "");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String timeStamp = sdf.format(date).replaceAll("[\\s|\\u00A0]+", "").replaceAll("\\:", "");
        File screenshot = ((TakesScreenshot) DriverFactory.getInstance()).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(screenshot, new File(stepNameTrimmed + "-" + timeStamp + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
