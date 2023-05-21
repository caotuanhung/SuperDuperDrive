package com.udacity.jwdnd.course1.cloudstorage.page;

import org.mockito.internal.matchers.Not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteTab {
    private WebDriver webDriver;
    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTab;
    @FindBy(css = "#btn-add-new-note")
    private WebElement addNewNoteButton;
    @FindBy(css = "#note-to-save-title")
    private WebElement noteToSaveTitleField;
    @FindBy(css = "#note-to-save-description")
    private WebElement noteToSaveDescriptionField;
    @FindBy(css = "#note-to-save-submit")
    private WebElement noteToSaveSubmitButton;
    @FindBy(css = "#note-to-update-title")
    private WebElement noteToUpdateTitleField;
    @FindBy(css = "#note-to-update-description")
    private WebElement noteToUpdateDescriptionField;
    @FindBy(css = "#note-to-update-submit")
    private WebElement noteToUpdateSubmitButton;
    public NoteTab(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void openNoteTab() {
        this.notesTab.click();
    }
    public void createNewNote(String title, String description) {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(addNewNoteButton));
        this.addNewNoteButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(noteToSaveTitleField));
        this.noteToSaveTitleField.click();
        this.noteToSaveTitleField.sendKeys(title);
        this.noteToSaveDescriptionField.click();
        this.noteToSaveDescriptionField.sendKeys(description);
        this.noteToSaveSubmitButton.click();
    }

    public void updateNote(String title, String description) {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(noteToUpdateTitleField));
        this.noteToUpdateTitleField.click();
        this.noteToUpdateTitleField.clear();
        this.noteToUpdateTitleField.sendKeys(title);
        this.noteToUpdateDescriptionField.click();
        this.noteToUpdateDescriptionField.clear();
        this.noteToUpdateDescriptionField.sendKeys(description);
        this.noteToUpdateSubmitButton.click();
    }

    public void deleteNote(Integer index) {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(this.webDriver.findElements(By.className("btn-delete-note")).get(index)));
        WebElement deleteNoteButton = this.webDriver.findElements(By.className("btn-delete-note")).get(index);
        deleteNoteButton.click();
    }
}
