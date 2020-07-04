package stepsdefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import centauro.suport.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {

	BaseClassStep test = new BaseClassStep();
	private static String currentStep="";
	private static String imgFolder="evidencias";

	@AfterStep
	public void afterStep(Scenario scenario) throws Exception {
		saveScreenshotPNG(scenario);
	}
	
	@After
	public void after() {
		DriverFactory.quitDriver();
	}

	public void saveScreenshotPNG(Scenario scenario) {
		String scenarioName =  removeSpecialChars(scenario.getName());
		String status = String.valueOf(scenario.getStatus());
		String nameImg = (status + "_" + "CenarioName=" + scenarioName + "_StepName=" +
				removeSpecialChars(test.getStepText()) + removeSpecialChars(currentStep) + "_" + dateNowImg() );
		createScreenshot(nameImg, scenarioName);
	}

	public void createScreenshot(String nameImg, String scenarioName){
		String path = String.valueOf(Paths.get(System.getProperty("user.dir"), imgFolder, scenarioName, dateNowFolder()));
		fileWithDirectoryAssurance(path);
		File screenshot = ((TakesScreenshot) DriverFactory.getInstance()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(String.valueOf(Paths.get(path, nameImg + ".png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String removeSpecialChars(String text){
		return text.replaceAll("[^a-zA-Z0-9]", "_");
	}

	public String dateNowImg() {
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return removeSpecialChars(formata.format(new Date()));
	}

	public String dateNowFolder() {
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		return removeSpecialChars(formata.format(new Date()));
	}

	public static File fileWithDirectoryAssurance(String directory) {
		File dir = new File(String.valueOf(directory));
		if (!dir.exists()) dir.mkdirs();
		return dir;
	}
}
