package kovo.kovo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Test {
	WebDriver driver;

	public static void take_screen_shot(WebDriver driver, String imageFile) {
		Path path = Paths.get("Scr_Shots", imageFile + ".PNG");
		try {
			Files.createDirectories((path.getParent()));
			FileOutputStream file_output_stream = new FileOutputStream(path.toString());
			file_output_stream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			file_output_stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//Driver_Set_Up
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//Driver_Navigation
		driver.navigate().to("https://arielkiell.wixsite.com/interview");
//Window_Maximize, With Screenshot
		driver.manage().window().maximize();
		take_screen_shot(driver, "Home");
//Shop_Button
		driver.findElement(By.xpath("//*[@id=\"comp-iy4cwgmq1label\"]")).click();
		Thread.sleep(2000);
//Shop_Section, With_Screenshot
		driver.findElement(
				By.xpath("//*[@id=\"TPASection_j4ci4xl4\"]/div/div/div/div/section/div/ul/li[4]/div/a/div[2]/h3"))
				.click();
		take_screen_shot(driver, "Shop_Section");
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
//Product_page, With Screenshot
		WebElement read = wait.until(ExpectedConditions.elementToBeClickable(By.className("_2V5SI")));
		read.click();
		take_screen_shot(driver, "Product_Page");
		Thread.sleep(2000);
//Color_Choose, With Screenshot		
		driver.findElement(By.xpath(
				"//*[@id=\"TPAMultiSection_j4ci4xqb\"]/div/div/article/div[1]/div/article/section[2]/div[7]/div[1]/div/div[1]/section/div[2]/div/div/ul/li[1]"))
				.click();
		take_screen_shot(driver, "Chosen_Color");
//Mouth_Hover_The_Up_Arrow
		Actions actions = new Actions(driver);
		WebElement count = driver.findElement(By.xpath(
				"//*[@id=\"TPAMultiSection_j4ci4xqb\"]/div/div/article/div[1]/div/article/section[2]/div[7]/div[1]/div/div[2]/div/div/div/div/div/input"));
		actions.moveToElement(count).perform();
//Double_Click_on_Up_Arrow, With_Screenshot
		driver.findElement(By.xpath(
				"//*[@id=\"TPAMultiSection_j4ci4xqb\"]/div/div/article/div[1]/div/article/section[2]/div[7]/div[1]/div/div[2]/div/div/div/div/div/div/span[1]"))
				.click();
		driver.findElement(By.xpath(
				"//*[@id=\"TPAMultiSection_j4ci4xqb\"]/div/div/article/div[1]/div/article/section[2]/div[7]/div[1]/div/div[2]/div/div/div/div/div/div/span[1]"))
				.click();
		take_screen_shot(driver, "Incremented_Quantity");
//Add_To_Cart_Button, With Screenshot		
		WebElement add_to_cart = driver.findElement(By.xpath(
				"//*[@id=\"TPAMultiSection_j4ci4xqb\"]/div/div/article/div[1]/div/article/section[2]/div[7]/div[2]/button/span"));
		add_to_cart.click();
		driver.switchTo().frame(2);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//View_Cart_Button, With_Screenshot
		WebElement view_cart = wait.until(ExpectedConditions.elementToBeClickable(By.id("widget-view-cart-button")));
		take_screen_shot(driver, "Add_Cart");
		view_cart.click();
		Thread.sleep(2000);
		take_screen_shot(driver, "Checkout_Page");
//Check_Out_Button, With_Screenshot
		driver.switchTo().frame(0);
		WebElement check_out = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div[2]/section/div[2]/div/div/button"));
		check_out.click();
		Thread.sleep(2000);
		take_screen_shot(driver, "Payment");
		driver.switchTo().parentFrame();
//Confirmation_Step, With Screenshot
		driver.switchTo().frame(4);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[3]/button")).click();
		take_screen_shot(driver, "Confirm");
		driver.switchTo().frame(0);
//Assertion_Lines
		WebElement sum = driver.findElement(By.id("total-sum"));
		String total_sum = sum.getText();
		String expected_total_sum = "C$75.00";
		Assert.assertEquals(expected_total_sum, total_sum);
		driver.close();
	}
}