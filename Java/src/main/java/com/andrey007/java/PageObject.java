package com.andrey007.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by andrey on 11/22/17.
 */

public class PageObject {
    protected WebDriver driver;
    public PageObject(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
