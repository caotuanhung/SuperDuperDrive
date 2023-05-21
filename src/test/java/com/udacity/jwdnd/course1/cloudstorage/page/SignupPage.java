package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(css = "#inputFirstName")
    private WebElement firstnameField;
    @FindBy(css = "#inputLastName")
    private WebElement lastnameField;
    @FindBy(css = "#inputUsername")
    private WebElement usernameField;
    @FindBy(css = "#inputPassword")
    private WebElement passwordField;
    @FindBy(css = "#buttonSignUp")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    public void signup(String firstname, String lastname, String username, String password) {
        this.firstnameField.click();
        this.firstnameField.sendKeys(firstname);

        this.lastnameField.click();
        this.lastnameField.sendKeys(lastname);

        this.usernameField.click();
        this.usernameField.sendKeys(username);

        this.passwordField.click();
        this.passwordField.sendKeys(password);

        this.submitButton.click();
    }
}
