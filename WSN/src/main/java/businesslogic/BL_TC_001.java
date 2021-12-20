package businesslogic;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobject.PO_TC_001;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BL_TC_001 extends PO_TC_001 {
    WebDriver driver = null;

    public void InitialSetup() throws Throwable {
        try {
            System.out.println("Launching Edge browser..");
            //set path of msedgedriver.exe
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +  "\\src\\main\\resources\\Driver\\"+"msedgedriver.exe");
            //instance of EdgeDriver
             driver = new EdgeDriver();
            //URL launch
            driver.navigate().to("https://www.moneycorp.com/en-gb/");
            driver.manage().window().maximize();
            waitForPageToLoad();
            String ExpectedTitle = "International Payments & Currency Exchange | moneycorp";
            Assert.assertEquals(driver.getTitle(), ExpectedTitle);
        } catch (Exception e) {

            e.printStackTrace();
            Teardown();
            Assert.fail("Test case fail due to exception");

        }
        }


    public void ChangeTheRegion() throws Throwable {
        try {
            System.out.println("Change the language and region from the top right corner to USA (English).");
            // Change the language and region from the top right corner to USA (English).
            waitForElementVisible(Region_language_Dropdown,30);
            driver.findElement(By.xpath(Region_language_Dropdown)).click();
            waitForElementVisible(Region_language,30);

            String searchText = "USA";

            List<WebElement> options = driver.findElements(By.xpath(Region_language));
            for (WebElement option : options)
            {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", option);
                Thread.sleep(500);
                if (option.getText().contains(searchText))
                {
                    option.click(); // click the desired option
                    break;
                }
            }

            Thread.sleep(5000);
            waitForElementVisible(Region_language_Dropdown_Logo,30);
            WebElement img = driver.findElement(By.xpath(Region_language_Dropdown_Logo));
            String Actual_Logo_src = img.getAttribute("src");

            String Expected_Logo_src = "https://www.moneycorp.com/globalassets/images/icons/flags/united-states-of-america.svg";
            Assert.assertEquals(Actual_Logo_src, Expected_Logo_src,"Region and language is not selected as USA (English).");



        } catch (Exception e) {

            e.printStackTrace();
            Teardown();
            Assert.fail("Test case fail due to exception");
        }
    }

    public void FindOutButtonCheck() throws Throwable {
        try {
            System.out.println("Click Find out more for “Foreign exchange solutions” Validate if you have arrived on the page");
            // Click Find out more for “Foreign exchange solutions” Validate if you have arrived on the page

            List<WebElement> options = driver.findElements(By.xpath(FindoutHeading));
            int i = options.size();
            for (int j=1; i<= i; j++)
            {
                WebElement linkbutton = driver.findElement(By.xpath("(//div[@class='copy']/a)["+j+"]"));
                WebElement Heading = driver.findElement(By.xpath("(//div[@class='copy']/h3)["+j+"]"));
                if (Heading.getText().contains("Foreign exchange solutions"))
                {
                    //Click on Fint Out More Link
                    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", linkbutton);
                    linkbutton.click();
                    break;
                }
            }

            Thread.sleep(5000);
            waitForElementVisible("//h1[contains(text(),'Foreign exchange solutions for your business')]",30);
            String ExpectedTitle = "Foreign Exchange Solutions | moneycorp USA";
            String Actual_Title = driver.getTitle();
            Assert.assertEquals(Actual_Title, ExpectedTitle,"Foreign Exchange Solutions page is not displayed");



        } catch (Exception e) {

            e.printStackTrace();
            Teardown();
            Assert.fail("Test case fail due to exception");
        }
    }


    public void SearchWord(String SearchValue) throws Throwable {
        try {
            System.out.println("Search for the word “international payments” using the search box");
            // Search for the word “international payments” using the search box

            waitForElementVisible(Searchicon,30);
            driver.findElement(By.xpath(Searchicon)).click();
            waitForElementVisible(SearchinputText,30);
            driver.findElement(By.xpath(SearchinputText)).sendKeys(SearchValue + "\n");
            Thread.sleep(2000);
            String ExpectedTitle = "Search";
            String Actual_Title = driver.getTitle();
            Assert.assertEquals(Actual_Title, ExpectedTitle,"Search page is not displayed");

        } catch (Exception e) {

            e.printStackTrace();
            Teardown();
            Assert.fail("Test case fail due to exception");
        }
    }
    public void ValidateLink() throws Throwable {
        try {
            System.out.println("Validate that each article in the list displays a link that starts with https://www.moneycorp.com/en-us/ ");
            //Validate that each article in the list displays a link that starts with https://www.moneycorp.com/en-us/
            waitForElementVisible(Link,30);



            List<WebElement> options = driver.findElements(By.xpath(Link));
            for (WebElement option : options)
            {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", option);
                Thread.sleep(500);
                String href = option.getAttribute("href");

                if (href.startsWith("https://www.moneycorp.com/en-us/"))
                {
                    System.out.println("Link Start with  https://www.moneycorp.com/en-us/");
                }
                else
                {
                    Assert.fail("link does not contain https://www.moneycorp.com/en-us/ ");
                }
            }





        } catch (Exception e) {

            e.printStackTrace();
            Teardown();
            Assert.fail("Test case fail due to exception");
        }
    }







    public void waitForElementVisible(String element, Integer Time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        } catch (TimeoutException e) {
            e.printStackTrace();

        }

    }
    public void waitForPageToLoad() {
        System.out.println("Implicit wait Time to load the Page " + 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void Teardown() throws Throwable {
        try {
            System.out.println("Teardown Edge browser..");

            driver.quit();


        } catch (Exception e) {

            e.printStackTrace();

        }
    }





}



