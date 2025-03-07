package winspect.com;

import java.awt.Robot;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.baseclass.BaseClass;

public class Winspect_Desktop extends BaseClass {

	Actions ac;
	Robot robot;
	JavascriptExecutor js;

	public static WebDriver driver;

	@BeforeSuite
	private void Test_A() {

		driver = getDriver("chrome");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getUrl("https://desktop-preprod.winspectdev.com/login");

	}

	@Test(priority = 1)
	private void company_Key() {
		WebElement cK1 = driver.findElement(By.xpath("//input[@name='companyKey1']"));
		cK1.sendKeys("9");
		WebElement cK2 = driver.findElement(By.xpath("//input[@name='companyKey2']"));
		cK2.sendKeys("9");

		WebElement cK3 = driver.findElement(By.xpath("//input[@name='companyKey3']"));
		cK3.sendKeys("9");

	}

	@Test(priority = 2)
	private void userName() {
		WebElement userName = driver.findElement(By.xpath("//input[@placeholder='Enter your username']"));
		userName.sendKeys("sempally");

	}

	@Test(priority = 3)
	private void password() {
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
		password.sendKeys("Win@1234");

	}

	@Test(priority = 4)
	private void signIn() {
		WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign In']"));
		signIn.click();

	}

	@Test(priority = 5)
	private void pastOrder() {
		WebElement past = driver.findElement(By.xpath("//div[text()='Past']"));
		past.click();

	}

	@Test(priority = 6)
	private void orderCard() {

		// WebElement Searchbar = driver.findElement(By.className("py-2 px-10
		// text-[14px] font-normal leading-[24px] w-[318px] text-primary"));
		// WebElement searchBar =
		// driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]"));
		// searchBar.sendKeys("2181");

		WebElement orderCard = driver.findElement(By.xpath("//div[text()='2255']"));

		orderCard.click();

	}
	
	@Test(priority = 7)

	private void agreementPopup() throws InterruptedException {

		Thread.sleep(5000);

		try {
			WebElement close = driver.findElement(By.xpath("(//button[text()='Close'])[1]"));
			close.click();
		} catch (Exception e) {
			System.out.println("Agreement Pop-up not present");
		}
	}

	@Test(priority = 7)
	private void orderDetailsPage() throws InterruptedException {

		WebElement beds = driver.findElement(By.xpath("//input[@name='beds']"));
		beds.sendKeys("5");

		WebElement floors = driver.findElement(By.xpath("//input[@name='floors']"));
		floors.sendKeys("15");

		WebElement baths = driver.findElement(By.xpath("//input[@name='baths']"));
		baths.sendKeys("04");

		WebElement yearBuilt = driver.findElement(By.xpath("//input[@name='yearBuilt']"));
		yearBuilt.sendKeys("2002");

		WebElement sqFeet = driver.findElement(By.xpath("//input[@name='squareFeet']"));
		sqFeet.sendKeys("200");

		WebElement temperature = driver.findElement(By.xpath("//input[@name='temperature']"));
		temperature.sendKeys("-27");
		

		Thread.sleep(5000);

		WebElement save = driver.findElement(By.xpath("//button[text()='Save']"));
		save.click();

		Thread.sleep(5000);

	}

	

	@Test(priority = 8)
	private void continueInspection() throws InterruptedException {
		Thread.sleep(5000);
		WebElement continueBtn = driver.findElement(By.xpath("(//button[text()='Continue Inspection'])[1]"));
		continueBtn.click();
		Thread.sleep(2000);
	}

	@Test(priority = 9)
	private void searchRemarks() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		ac = new Actions(driver);
		js = (JavascriptExecutor) driver;

		Thread.sleep(3000);

		try {

			WebElement dropdownvalue = driver
					.findElement(By.xpath("//div[@data-value='4564f350-ace3-490b-b6c3-12cdd3b97f04']"));
			dropdownvalue.click();

			WebElement clickOutsidePopup = driver
					.findElement(By.xpath("(//div[contains(text(),'Full Home Inspection')])[1]"));
			clickOutsidePopup.click();
		} catch (Exception e) {
			System.out.println("No About values");
		}

		for (int i = 1; i <= 8; i++) {

			WebElement subCategory = driver
					.findElement(By.xpath("(//div[@class='flex flex-col gap-[2px]']/child::div)[" + i + "]"));
			js.executeScript("arguments[0].click();", subCategory);

			WebElement addNewRemark = driver.findElement(By.xpath("//div[text()='Add New Remark']"));
			ac.moveToElement(addNewRemark).perform();
			Thread.sleep(3000);

			WebElement addACustomRemark = driver.findElement(By.xpath("//button[text()='Add a custom remark']"));
			js.executeScript("arguments[0].click();", addACustomRemark);
			Thread.sleep(3000);

			WebElement remarkSearchBtn = driver
					.findElement(By.xpath("//h3[text()='Remark Title']/following-sibling::button"));
			remarkSearchBtn.click();
			Thread.sleep(3000);

			WebElement replaceRemark = driver.findElement(By.xpath("(//div[text()='Replace'])[" + i + "]"));
			replaceRemark.click();
			Thread.sleep(3000);

			System.out.println("Added the Remark for  : " + i);

			try {

				WebElement AddtoSummary = driver.findElement(By.xpath("(//button[@id='add-to-summary'])[" + i + "]"));
				js.executeScript("arguments[0].scrollIntoView(true);", AddtoSummary);
				Thread.sleep(5000);

				js.executeScript("arguments[0].click();", AddtoSummary);

			} catch (Exception e) {
				System.out.println("Unable to select the AddToSummary : " + i);

			}

			Thread.sleep(5000);

			try {
				WebElement statusBox = driver.findElement(
						By.xpath("(//label[text()='Status*']/following-sibling::button[@role='combobox'])[" + i + "]"));
				js.executeScript("arguments[0].scrollIntoView(true);", statusBox);
				Thread.sleep(5000);

				js.executeScript("arguments[0].click();", statusBox);
				Thread.sleep(5000);
				WebElement functionalStatus = driver.findElement(By.xpath("//span[text()='Not Inspected']"));
				js.executeScript("arguments[0].click();", functionalStatus);

			} catch (Exception e) {
				System.out.println("Unable to select the statuses : " + i);

			}

			// Action Required,Informational,Limitation,Not Inspected,Preventive
			// Measure,Attention,Repairs Recommended
			// It should be dynamic
		}

		WebElement completebtn = driver.findElement(By.xpath("(//button[normalize-space()='Complete'])[1]"));

		if (completebtn.isEnabled()) {
			completebtn.click();
		} else if (completebtn.isDisplayed()) {
			WebElement clickToIncomplete = driver
					.findElement(By.xpath("(//button[normalize-space()='Completed (Click to Incomplete)'])[1]"));
			clickToIncomplete.click();
		}

		System.out.println("Remarks added nd clicked ");

	}

	@Test(priority = 10)
	private void searchRemarks1() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		ac = new Actions(driver);
		js = (JavascriptExecutor) driver;

		try {

			WebElement dropdownvalue = driver
					.findElement(By.xpath("//div[@data-value='4564f350-ace3-490b-b6c3-12cdd3b97f04']"));
			dropdownvalue.click();

			WebElement clickOutsidePopup = driver
					.findElement(By.xpath("(//div[contains(text(),'Full Home Inspection')])[1]"));
			clickOutsidePopup.click();
		} catch (Exception e) {
			System.out.println("No About values");
		}

		for (int i = 1; i <= 8; i++) {

			WebElement subCategory = driver
					.findElement(By.xpath("(//div[@class='flex items-center gap-1 cursor-pointer'])[" + i + "]"));
			subCategory.click();
			Thread.sleep(3000);

			WebElement addNewRemark = driver.findElement(By.xpath("//div[text()='Add New Remark']"));
			ac.moveToElement(addNewRemark).perform();
			Thread.sleep(3000);

			WebElement addACustomRemark = driver.findElement(By.xpath("//button[text()='Add a custom remark']"));
			js.executeScript("arguments[0].click();", addACustomRemark);
			Thread.sleep(3000);

			WebElement remarkSearchBtn = driver
					.findElement(By.xpath("//h3[text()='Remark Title']/following-sibling::button"));
			remarkSearchBtn.click();
			Thread.sleep(3000);

			WebElement replaceRemark = driver.findElement(By.xpath("(//div[text()='Replace'])[" + i + "]"));
			replaceRemark.click();
			Thread.sleep(3000);

			try {

				WebElement AddtoSummary = driver.findElement(By.xpath("(//button[@id='add-to-summary'])[" + i + "]"));
				js.executeScript("arguments[0].scrollIntoView(true);", AddtoSummary);
				Thread.sleep(5000);

				js.executeScript("arguments[0].click();", AddtoSummary);

			} catch (Exception e) {
				System.out.println("Unable to select the AddToSummary : " + i);

			}

			Thread.sleep(5000);

			try {
				WebElement statusBox = driver.findElement(
						By.xpath("(//label[text()='Status*']/following-sibling::button[@role='combobox'])[" + i + "]"));
				js.executeScript("arguments[0].scrollIntoView(true);", statusBox);
				Thread.sleep(5000);

				js.executeScript("arguments[0].click();", statusBox);
				Thread.sleep(5000);
				WebElement functionalStatus = driver.findElement(By.xpath("//span[text()='Not Inspected']"));
				js.executeScript("arguments[0].click();", functionalStatus);

			} catch (Exception e) {
				System.out.println("Unable to select the statuses : " + i);

			}

			// Action Required,Informational,Limitation,Not Inspected,Preventive
			// Measure,Attention,Repairs Recommended
			// It should be dynamic
		}

		WebElement completebtn = driver.findElement(By.xpath("(//button[normalize-space()='Complete'])[1]"));

		if (completebtn.isEnabled()) {
			completebtn.click();
		} else if (completebtn.isDisplayed()) {
			WebElement clickToIncomplete = driver
					.findElement(By.xpath("(//button[normalize-space()='Completed (Click to Incomplete)'])[1]"));
			clickToIncomplete.click();
		}

		System.out.println("Remarks added nd clicked ");

	}
}
