package winspect.com;

import java.awt.Robot;
import java.time.Duration;
import java.util.List;

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

		WebElement orderCard = driver.findElement(By.xpath("//div[text()='2268']"));

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
		temperature.sendKeys("-29");

		Thread.sleep(5000);

//		WebElement save = driver.findElement(By.xpath("//button[text()='Save']"));
//		save.click();

		Thread.sleep(8000);

	}

	@Test(priority = 8)
	private void continueInspection() throws InterruptedException {
		Thread.sleep(5000);
		WebElement continueBtn = driver.findElement(By.xpath("(//button[text()='Continue Inspection'])[1]"));
		continueBtn.click();
		Thread.sleep(2000);
	}

	@Test(priority = 10)
	private void searchRemarks() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		ac = new Actions(driver);
		js = (JavascriptExecutor) driver;

		try {
			WebElement dropdownValue = driver
					.findElement(By.xpath("//div[@data-value='4564f350-ace3-490b-b6c3-12cdd3b97f04']"));
			dropdownValue.click();

			WebElement clickOutsidePopup = driver
					.findElement(By.xpath("(//div[contains(text(),'Full Home Inspection')])[1]"));
			clickOutsidePopup.click();
		} catch (Exception e) {
			System.out.println("No About values found");
		}

		List<WebElement> subCategories = driver
				.findElements(By.xpath("//div[@class='flex items-center gap-1 cursor-pointer']"));
		int totalSubCategories = subCategories.size();
		System.out.println("Total Number of subCategories :" + totalSubCategories);

		for (int subIndex = 1; subIndex <= totalSubCategories; subIndex++) {
			WebElement subCategory = driver.findElement(
					By.xpath("(//div[@class='flex items-center gap-1 cursor-pointer'])[" + subIndex + "]"));
			subCategory.click();
			System.out.println("Selected subcategory: " + subIndex);
			Thread.sleep(3000);

			for (int remarkIndex = 1; remarkIndex <= 8; remarkIndex++) {
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

				WebElement replaceRemark = driver
						.findElement(By.xpath("(//div[text()='Replace'])[" + remarkIndex + "]"));
				replaceRemark.click();
				Thread.sleep(3000);

				System.out.println("Added remark " + remarkIndex + " for subcategory " + subIndex);

				if (subIndex == 1 && remarkIndex == 1) {
					try {
						WebElement uploadImage = driver.findElement(
								By.xpath("(//div[@id='upload-remark-image']/child::input[@type='file'])[1]"));
						js.executeScript("arguments[0].style.display='block';", uploadImage);
						Thread.sleep(2000);

						String imgUrl = "/Users/balajikathirwal/Downloads/NEW.jpg";
						uploadImage.sendKeys(imgUrl);
						Thread.sleep(30000);

						WebElement saveBtnInTheEditor = driver.findElement(By.xpath("//div[text()='Save']"));
						js.executeScript("arguments[0].click();", saveBtnInTheEditor);
						System.out.println("Image uploaded successfully for subcategory 1, remark 1!");
					} catch (Exception e) {
						System.out.println("Image not uploaded for subcategory 1, remark 1");
						System.out.println("This is the issue ====================: " + e);
					}
				}

				try {
					WebElement AddtoSummary = driver
							.findElement(By.xpath("(//button[@id='add-to-summary'])[" + remarkIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView(true);", AddtoSummary);
					Thread.sleep(5000);
					js.executeScript("arguments[0].click();", AddtoSummary);
				} catch (Exception e) {
					System.out.println("Unable to select the AddToSummary for remark: " + remarkIndex);
				}

				Thread.sleep(5000);

				try {
					WebElement statusBox = driver.findElement(
							By.xpath("(//label[text()='Status*']/following-sibling::button[@role='combobox'])["
									+ remarkIndex + "]"));
					js.executeScript("arguments[0].scrollIntoView(true);", statusBox);
					Thread.sleep(5000);
					js.executeScript("arguments[0].click();", statusBox);
					Thread.sleep(5000);

					WebElement functionalStatus = driver.findElement(By.xpath("//span[text()='Not Inspected']"));
					js.executeScript("arguments[0].click();", functionalStatus);
				} catch (Exception e) {
					System.out.println("Unable to select the status for remark: " + remarkIndex);
				}
			}

			try {
				WebElement completeBtn = driver.findElement(By.xpath("//button[text()='Complete']"));
				if (completeBtn.isEnabled()) {
					completeBtn.click();
				} else if (completeBtn.isDisplayed()) {
					WebElement clickToIncomplete = driver
							.findElement(By.xpath("//button[normalize-space()='Completed (Click to Incomplete)']"));
					clickToIncomplete.click();
				}
				System.out.println("Subcategory " + subIndex + " completed.");
			} catch (Exception e) {
				System.out.println("Unable to click Complete button for subCategory: " + subIndex);
			}

			Thread.sleep(9000);
		}

		WebElement checkTheStatusOfSubCategory = driver.findElement(By.xpath("//img[@alt='Completed']"));
		String attribute = checkTheStatusOfSubCategory.getAttribute("alt");
		System.out.println("Main Category Status: " + attribute);

		if (attribute.equalsIgnoreCase("Completed")) {
			WebElement finishAndSubmit = driver.findElement(By.xpath("//button[text()='Finish and Submit']"));
			finishAndSubmit.click();
			System.out.println("Main category completed. Clicked Finish and Submit.");
		} else {
			System.out.println("Main category still in progress.");
		}

		Thread.sleep(8000);

		WebElement yesClientreport = driver.findElement(By.xpath("//button[text()='Yes']"));
		js.executeScript("arguments[0].click();", yesClientreport);

		Thread.sleep(8000);

		WebElement submitToWinConnect = driver.findElement(By.xpath("//button[text()='Submit to WINconnect']"));
		js.executeScript("arguments[0].click();", submitToWinConnect);

		System.out.println("Completed a report with Happy Flow");

	}

}
