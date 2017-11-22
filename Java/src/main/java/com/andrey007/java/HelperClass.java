package com.andrey007.java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by andrey on 11/22/17.
 */

public class HelperClass extends PageObject {

    @FindBy(xpath = "//a[@id='ctaHomeHero2b']")
    private WebElement btnSignUpPro;

    @FindBy(xpath = "//a[@id='ctaHomeHero1']")
    private WebElement btnSignUpFree;


    @FindBy(xpath = "//input[@id='email']")
    private WebElement fieldEmail;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//input[@id='wufooName']")
    private WebElement fieldUserName;

    @FindBy(xpath = "//input[@id='saveForm']")
    private WebElement btnSignUp;

    @FindBy(xpath = "//a[contains(text(),'Sign Up!')])[3]")
    private WebElement btnAAA;

    @FindBy(xpath = "//a[@id='notRightNow']")
    private WebElement btnNotRightNow;

    @FindBy(xpath = "//a[contains(text(),'Create form')]")
    private WebElement btnCreateForm;


    @FindBy(xpath = "//a[@id='sl']")
    private WebElement btnSingleLineText;

    @FindBy(xpath = "//a[@id='nu']")
    private WebElement btnNumber;

    @FindBy(xpath = "//a[@id='ml']")
    private WebElement btnParagraphText;

    @FindBy(xpath = "//a[@id='cb']")
    private WebElement btnCheckBoxes;

    @FindBy(xpath = "//a[@id='mc']")
    private WebElement btnMultipleChoice;

    @FindBy(xpath = "//label[@id='title1']")
    private WebElement fieldSingleLineText;

    @FindBy(xpath = "//label[@id='title2']")
    private WebElement fieldNumbers;

    @FindBy(xpath = "//label[@id='title3']")
    private WebElement fieldParagraphText;

    @FindBy(xpath = "//label[@id='title4']")
    private WebElement fieldCheckBoxes;

    @FindBy(xpath = "//label[@id='title104']")
    private WebElement fieldMultipleChoice;

    @FindBy(xpath = "//a[@id='saveForm']")
    private WebElement btnSaveForm;

    @FindBy(xpath = "//div[@id='lbContent']/ol/li/a")
    private WebElement dialogShare;

    @FindBy(xpath = "//input[@id='permanentLink']")
    private WebElement linkShared;

    @FindBy(xpath = "//input[@id='Field1']")
    private WebElement fieldFirst;

    @FindBy(xpath = "//input[@id='Field2']")
    private WebElement fieldSecond;

    @FindBy(xpath = "//textarea[@id='Field3']")
    private WebElement fieldThird;


    @FindBy(xpath = "//*[text()[contains(.,'Great! Thanks for filling out my form!')]]")
    private WebElement txtGreat;

    @FindBy(xpath = "//*[text()[contains(.,'Account')]]")
    private WebElement txtAccount;




    Settings set = new Settings();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    private Random rnd = new Random();

    private String sharedLink;
    public static  String baseURL = "https://www.wufoo.com/";

    public HelperClass(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {

        try{
            return this.btnSignUpFree.isDisplayed();

        }catch (NoSuchElementException ex) {
            driver.navigate().refresh();
            return this.btnSignUpFree.isDisplayed();
        }
    }

    protected void signUp(String email, String password, String userName){

        set.waitForElement(btnSignUpFree, 30);
        Log.info("Button Sign Up Free exists");

        this.btnSignUpPro.click();
        assertTrue(driver.findElement(By.cssSelector("#plan-18 > div.plans__details > div.btn-row.plans__btn-row > a.btn.plans__btn-cta")).isDisplayed());
        Log.info("Next Page opened");
        driver.findElement(By.cssSelector("#plan-18 > div.plans__details > div.btn-row.plans__btn-row > a.btn.plans__btn-cta")).click();

        /*btnSignUpFree.click();
        set.waitForElement(fieldEmail, 30);
        Log.info("Next Page opened");
*/
        fieldEmail.clear();
        fieldEmail.sendKeys(email);
        Log.info("Email: " + email + " entered");

        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        Log.info("Password: " + password + " entered");

        fieldUserName.clear();
        fieldUserName.sendKeys(userName);
        Log.info("User Name: " + userName + " entered");

        assertTrue(btnSignUp.isDisplayed());
        btnSignUp.click();
        Log.info("Button Continue clicked");
    }

    protected void creatingForm(){

        assertTrue(btnNotRightNow.isDisplayed());
        Log.info("Next Page opened");
        btnNotRightNow.click();
        assertTrue(btnCreateForm.isDisplayed());
        Log.info("Page Navigator opened");
        btnCreateForm.click();
        set.waitForElement(btnSingleLineText, 30);

        btnSingleLineText.click();
        Log.info("Button Single Line Text clicked");
        btnNumber.click();
        Log.info("Button Number clicked");
        btnParagraphText.click();
        Log.info("Button Paragraph Text clicked");
        btnCheckBoxes.click();
        Log.info("Button Check Boxes clicked");
        btnMultipleChoice.click();
        Log.info("Button Multiple Choice clicked");


        assertTrue(fieldSingleLineText.isDisplayed());
        Log.info("Field Single Line Text exists");
        assertTrue(fieldNumbers.isDisplayed());
        Log.info("Field Number exists");
        assertTrue(fieldParagraphText.isDisplayed());
        Log.info("Field Paragraph Text exists");
        try {
            assertTrue(fieldCheckBoxes.isDisplayed());
            Log.info("Field Check Boxes exists");
        }catch (NoSuchElementException ex){
            assertTrue(driver.findElement(By.xpath("//li[5]/fieldset/legend")).isDisplayed());
            Log.info("Field Check Boxes exists");
        }
        try {
            assertTrue(fieldMultipleChoice.isDisplayed());
            Log.info("Field Multiple Choice exists");
        }catch (NoSuchElementException e){
            assertTrue(driver.findElement(By.xpath("//div[2]/form/ul/li[6]/fieldset/legend")).isDisplayed());
            Log.info("Field Check Boxes exists");
        }

        btnSaveForm.click();
        Log.info("Form saved");
        dialogShare.click();
        Log.info("Form shared");


        Log.info(linkShared.getAttribute("value"));

        sharedLink  = linkShared.getAttribute("value");
    }

    protected void checkForms(String test_message){

        Log.info("Check Forms " + sharedLink);
        driver.get(sharedLink);
        set.waitForElement(fieldFirst, 30);
        fieldFirst.clear();
        fieldFirst.sendKeys(test_message);
        Log.info("Form First checked");

        fieldSecond.clear();
        fieldSecond.sendKeys("123");
        Log.info("Form Second checked");

        fieldThird.clear();
        fieldThird.sendKeys(test_message);
        Log.info("Form Third checked");


        WebElement cb_1 = driver.findElement(By.xpath("//input[@id='Field4']/.."));
        WebElement cb_2 = driver.findElement(By.xpath("//input[@id='Field5']/.."));
        WebElement cb_3 = driver.findElement(By.xpath("//input[@id='Field6']/.."));

        cb_1.click();

        Log.info("Check box 1 is selected");

        cb_2.click();

        Log.info("Check box 2 is selected");

        cb_3.click();

        Log.info("Check box 3 is selected");

        WebElement rb_1 = driver.findElement(By.xpath("//input[@id='Field104_0']"));
        WebElement rb_2 = driver.findElement(By.xpath("//input[@id='Field104_1']"));
        WebElement rb_3 = driver.findElement(By.xpath("//input[@id='Field104_2']"));

        switch (rnd.nextInt(3)){
            case 0:
                rb_1.click();
                assertTrue(rb_1.isSelected());
                Log.info("Radio button 1 is selected");
                break;
            case 1:
                rb_2.click();
                assertTrue(rb_2.isSelected());
                Log.info("Radio button 2 is selected");
                break;
            case 2:
                rb_3.click();
                assertTrue(rb_3.isSelected());
                Log.info("Radio button 3 is selected");
                break;
        }

        WebElement btnSubmit = driver.findElement(By.xpath("//input[@id='saveForm']"));
        btnSubmit.click();
        Log.info("Button submit clicked");
        assertTrue(txtGreat.isDisplayed());
        Log.info("Form checked");
    }

    protected void logIn(String email, String password) throws InterruptedException {

        driver.get("https://secure.wufoo.com/login/");

        WebElement fieldEmailLogin = driver.findElement(By.xpath("//input"));
        WebElement fieldPasswordLogin = driver.findElement(By.xpath("//div[2]/div/input"));
        WebElement btnSaveForm = driver.findElement(By.xpath("//input[@id='saveForm']"));


        set.waitForElement(fieldEmail, 5);
        fieldEmailLogin.clear();
        fieldEmailLogin.sendKeys(email);
        Log.info("Login Email: " + email + " entered");

        fieldPasswordLogin.clear();
        fieldPasswordLogin.sendKeys(password);
        Log.info("LogIn Password: " + password + " entered");

        btnSaveForm.click();


    }

    private void logout() throws InterruptedException {
        Actions builder = new Actions(driver);
        Action mouseOverAccount = builder
                .moveToElement(txtAccount)
                .build();
        set.waitForElement(txtAccount, 30);


        mouseOverAccount.perform();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        try {
            set.waitForElement(driver.findElement(By.xpath("//div[@id='lbContent']/ol/li/a/b")), 30).click();
        }catch (StaleElementReferenceException ex){
            mouseOverAccount.perform();
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
            set.waitForElement(driver.findElement(By.xpath("//div[@id='lbContent']/ol/li/a/b")), 30).click();
        }
        //driver.findElement(By.xpath("//a[contains(@href, '/logout/')]")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/logout/')]"))).click();

        try {
            assertTrue( driver.findElement(By.xpath("//a[contains(text(),'Demo')]")).isDisplayed());
            Log.info("Log Out confirmed");
        }catch (NoSuchElementException ex){
            mouseOverAccount.perform();
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
            driver.findElement(By.xpath("//div[@id='lbContent']/ol/li/a/b")).click();
            assertTrue( driver.findElement(By.xpath("//a[contains(text(),'Demo')]")).isDisplayed());
            Log.info("Log Out confirmed");
        }
    }

    private void deleteForm() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'More')]")).click();
        Log.info("Clicked 'More'");

        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Delete form')]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        driver.findElement(By.xpath("//input[@id='delete-input']")).clear();
        driver.findElement(By.xpath("//input[@id='delete-input']")).sendKeys("DELETE");
        driver.findElement(By.xpath("//a[@id='confirmFormDeletion']")).click();
        Log.info("Form deleted");
        Thread.sleep(3000);

    }

    protected void checkLogInAndDeleteForm(String email, String password) throws InterruptedException {
        logIn(email, password);
        deleteForm();
        logout();
    }
}