package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(css = "#btn-logout")
    private WebElement logoutButton;

    private NoteTab noteTab;
    private CredentialTab credentialTab;

    public NoteTab getNoteTab() {
        return noteTab;
    }

    public CredentialTab getCredentialTab() {
        return credentialTab;
    }

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.noteTab = new NoteTab(webDriver);
        this.credentialTab = new CredentialTab(webDriver);
    }

    public void logout() {
        this.logoutButton.click();
    }

}
