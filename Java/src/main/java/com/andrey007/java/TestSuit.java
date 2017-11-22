package com.andrey007.java;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by andrey on 11/22/17.
 */

public class TestSuit extends Settings {


    final Random rnd = new Random();
    int random_num = rnd.nextInt(10000);
    private String validEmail = "wufoo.test" + random_num + "@gmail.com";
    private String validPassword = "password123456";
    private String validUsername = "UserName" + random_num;


    @Test
    public void testSuit() throws Exception {
        HelperClass helperClass = new HelperClass(driver);
        assertTrue(helperClass.isInitialized());
        Log.info("Web Paged launched");

        helperClass.signUp(validEmail, validPassword, validUsername);
        helperClass.creatingForm();
        helperClass.checkForms("test" + rnd.nextInt(3));
        helperClass.checkLogInAndDeleteForm(validEmail, validPassword);
    }
}