package com.udacity.jwdnd.course1.cloudstorage.page;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialTab {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy(id = "btn-save-new-credential")
    private WebElement addNewCredentialButton;
    @FindBy(id = "credential-to-save-url")
    private WebElement urlField;
    @FindBy(id = "credential-to-save-username")
    private WebElement usernameField;
    @FindBy(id = "credential-to-save-password")
    private WebElement passwordField;
    @FindBy(id = "credential-to-save-submit")
    private WebElement saveCredentialButton;
    @FindBy(id = "credential-to-update-url")
    private WebElement urlUpdateField;
    @FindBy(id = "credential-to-update-username")
    private WebElement usernameUpdateField;
    @FindBy(id = "credential-to-update-password")
    private WebElement passwordUpdateField;
    @FindBy(id = "credential-to-update-submit")
    private WebElement updateCredentialSubmitButton;

    public CredentialTab(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 5);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public WebElement getCredentialsTab() {
        return credentialsTab;
    }

    public WebElement getAddNewCredentialButton() {
        return addNewCredentialButton;
    }

    public WebElement getUrlField() {
        return urlField;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSaveCredentialButton() {
        return saveCredentialButton;
    }

    public WebElement getUrlUpdateField() {
        return urlUpdateField;
    }

    public WebElement getUsernameUpdateField() {
        return usernameUpdateField;
    }

    public WebElement getPasswordUpdateField() {
        return passwordUpdateField;
    }

    public WebElement getUpdateCredentialSubmitButton() {
        return updateCredentialSubmitButton;
    }

    public void openCredentialsTab() {
        this.credentialsTab.click();
    }

    public void createNewCredential(String url, String username, String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addNewCredentialButton));
        addNewCredentialButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(urlField));
        urlField.click();
        urlField.clear();
        urlField.sendKeys(url);
        usernameField.click();
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        saveCredentialButton.click();
    }

    public void viewCredential(Integer index) {
        WebElement updateCredentialButton = webDriver.findElements(By.className("btn-edit-credential")).get(index);
        updateCredentialButton.click();
    }

    public void updateCredential(Integer index, String url, String username, String password) {
        viewCredential(index);
        webDriverWait.until(ExpectedConditions.visibilityOf(urlUpdateField));
        urlUpdateField.click();
        urlUpdateField.sendKeys(url);
        usernameUpdateField.click();
        usernameUpdateField.sendKeys(username);
        passwordUpdateField.click();
        passwordUpdateField.sendKeys(password);
        updateCredentialSubmitButton.click();
    }

    public void deleteCredential(Integer index) {
        WebElement deleteCredentialButton = webDriver.findElements(By.className("btn-delete-credential")).get(index);
        webDriverWait.until(ExpectedConditions.visibilityOf(deleteCredentialButton));
        deleteCredentialButton.click();
    }

    public void closeUpdateModel() {
        WebElement closeUpdateModalButton = webDriver.findElement(By.id("credential-to-update-cancel"));
        closeUpdateModalButton.click();
    }

    public Credential getCredential(Integer index) {
        WebElement urlField = webDriver.findElements(By.className("credential-url")).get(index);
        WebElement usernameField = webDriver.findElements(By.className("credential-username")).get(index);
        WebElement passwordField = webDriver.findElements(By.className("credential-password")).get(index);
        webDriverWait.until(ExpectedConditions.visibilityOf(urlField));
        webDriverWait.until(ExpectedConditions.visibilityOf(usernameField));
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordField));
        Credential credential = new Credential();
        credential.setUrl(urlField.getText());
        credential.setUsername(usernameField.getText());
        credential.setPassword(passwordField.getText());
        return credential;
    }
}
