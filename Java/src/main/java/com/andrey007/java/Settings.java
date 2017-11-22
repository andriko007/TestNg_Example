package com.andrey007.java;

import org.junit.After;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Settings {

    protected static WebDriver driver;
    public static  String baseURL = "https://www.wufoo.com/";

    private static String CHROME_DRIVER = "/chrome_driver/mac/chromedriver";
    private static String FIREFOX_DRIVER = "/firefox_driver/mac/geckodriver";

    /**
     * browser_id = 1 : Run test suit in Chrome
     * browser_id = 2 : Run test suit in Firefox
     */
    private static int browser_id = 1;

    @BeforeClass
    //@Parameters("browser")
    public static void setUp()//String browser)
    {
        /*
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", Settings.class.getResource(FIREFOX_DRIVER).getFile());
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver", Settings.class.getResource(CHROME_DRIVER).getFile());
            //create chrome instance
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(baseURL);

       */

        if(browser_id == 1) {

            System.setProperty("webdriver.chrome.driver", Settings.class.getResource(CHROME_DRIVER).getFile());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.get(baseURL);

        }else if(browser_id == 2){

            System.setProperty("webdriver.gecko.driver", Settings.class.getResource(FIREFOX_DRIVER).getFile());
            driver = new FirefoxDriver();
            driver.manage().window();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.get(baseURL);
        }
    }

    @After
    public void cleanUp()
    {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown()
    {
        driver.close();
    }


    /****************************************************** HELPER FUNCTIONS ******************************************************/

    public WebElement waitForElement(final WebElement element, int timeOut){
        new WebDriverWait( driver, timeOut ).until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply( WebDriver webDriver )
            {
                try
                {
                    element.isDisplayed();
                }
                catch( ElementNotVisibleException e )
                {
                    Log.info( "Select failed! Try again..." );
                    return false;
                }
                Log.info( element.getText().trim() + " found!" );
                return true;
            }
        } );
        return element;
    }

}