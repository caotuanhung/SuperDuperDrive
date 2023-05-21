package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.page.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling redirecting users
     * back to the login page after a succesful sign up.
     * Read more about the requirement in the rubric:
     * https://review.udacity.com/#!/rubrics/2724/view
     */
    @Test
    public void testRedirection() {
        // Create a test account
        doMockSignUp("Redirection", "Test", "RT", "123");

        // Check if we have been redirected to the log in page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling bad URLs
     * gracefully, for example with a custom error page.
     * <p>
     * Read more about custom error pages at:
     * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
     */
    @Test
    public void testBadUrl() {
        // Create a test account
        doMockSignUp("URL", "Test", "UT", "123");
        doLogIn("UT", "123");

        // Try to access a random made-up URL.
        driver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
    }


    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling uploading large files (>1MB),
     * gracefully in your code.
     * <p>
     * Read more about file size limits here:
     * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
     */
    @Test
    public void testLargeUpload() {
        // Create a test account
        doMockSignUp("Large File", "Test", "LFT", "123");
        doLogIn("LFT", "123");

        // Try to upload an arbitrary large file
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        String fileName = "upload5m.zip";

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }

    /**
     * Verify that none-login user only access to login page and signup page.
     * Verify that login user can access to homepage after login, then can not access to homepage after logout.
     */
    @Test
    public void testAccessToPages() {
        driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/bad-link");
		Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Thanh", "Nguyen", "thanhnt", "1234567890");
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("thanhnt", "1234567890");
		Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-logout")));
        homePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

    }

    /**
     * Verify that login user can create a note.
     */
    @Test
    public void testCreateNote() {
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Thanh", "Nguyen", "thanhnt", "1234567890");

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("thanhnt", "1234567890");

        HomePage homePage = new HomePage(driver);
        NoteTab noteTab = homePage.getNoteTab();
        noteTab.openNoteTab();
        noteTab.createNewNote("New title", "New description");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.className("note-title")).get(0)));
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.className("note-description")).get(0)));
        String actualTitle = driver.findElements(By.className("note-title")).get(0).getText();
        String actualDescription = driver.findElements(By.className("note-description")).get(0).getText();
		Assertions.assertEquals("New title", actualTitle);
		Assertions.assertEquals("New description", actualDescription);
    }

    /**
     * Verify that login user can update an exist note.
     */
    @Test
    public void testUpdateNote() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hungct", "123");

        HomePage homePage = new HomePage(driver);
        NoteTab noteTab = homePage.getNoteTab();
        noteTab.openNoteTab();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        WebElement editNoteButton = driver.findElements(By.className("btn-edit-note")).get(0);
        webDriverWait.until(ExpectedConditions.visibilityOf(editNoteButton));
        editNoteButton.click();
        noteTab.updateNote("Update new title", "Update new description");
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.className("note-title")).get(0)));
        String newActualTitle = driver.findElements(By.className("note-title")).get(0).getText();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.className("note-description")).get(0)));
        String newActualDescription = driver.findElements(By.className("note-description")).get(0).getText();
        Assertions.assertEquals("Update new title", newActualTitle);
        Assertions.assertEquals("Update new description", newActualDescription);
    }

    /**
     * Verify that login user can delete an exist note
     */
    @Test
    public void testDeleteNote() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hungct", "123");

        HomePage homePage = new HomePage(driver);
        NoteTab noteTab = homePage.getNoteTab();
        noteTab.openNoteTab();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        noteTab.deleteNote(0);
        WebElement deleteNoteNotification = driver.findElements(By.className("delete-note-notification")).get(0);
        webDriverWait.until(ExpectedConditions.visibilityOf(deleteNoteNotification));
        Assertions.assertEquals(deleteNoteNotification.getText(), "Delete note successfully!");
    }



    /**
     * Verify that login user can create credential.
     */
    @Test
    public void testCredentialFeatures() {
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Thanh", "Nguyen", "thanhnt", "1234567890");

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("thanhnt", "1234567890");

        HomePage homePage = new HomePage(driver);
        CredentialTab credentialTab = homePage.getCredentialTab();
        credentialTab.openCredentialsTab();
        credentialTab.createNewCredential("https://facebook.com", "facebook_account", "facebook_password");
        Credential credential = credentialTab.getCredential(0);
        Assertions.assertEquals(credential.getUrl(), "https://facebook.com");
        Assertions.assertEquals(credential.getUsername(), "facebook_account");
        Assertions.assertNotEquals(credential.getPassword(), "facebook_password");
    }

    /**
     * Verify that login user can view credential details.
     */
    @Test
    public void viewCredential() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hungct", "123");

        HomePage homePage = new HomePage(driver);
        CredentialTab credentialTab = homePage.getCredentialTab();
        credentialTab.openCredentialsTab();

        credentialTab.viewCredential(0);
        credentialTab.getWebDriverWait().until(ExpectedConditions.visibilityOf(credentialTab.getPasswordUpdateField()));
        String actualPassword = credentialTab.getPasswordUpdateField().getAttribute("value");
        Assertions.assertEquals("123456", actualPassword);
    }

    /**
     * Verify that login user can update an exist credential.
     */
    @Test
    public void updateCredential() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hungct", "123");

        HomePage homePage = new HomePage(driver);
        CredentialTab credentialTab = homePage.getCredentialTab();
        credentialTab.openCredentialsTab();

        credentialTab.updateCredential(0, "https://new-url.com", "new-username", "new-password");
        Credential credential = credentialTab.getCredential(0);
        Assertions.assertEquals("https://new-url.com", credential.getUrl());
        Assertions.assertEquals("new-username", credential.getUsername());
        Assertions.assertNotEquals("new-password", credential.getPassword());
    }

    /**
     * Verify that login user can delete an exist credential.
     */
    @Test
    public void deleteCredential() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hungct", "123");

        HomePage homePage = new HomePage(driver);
        CredentialTab credentialTab = homePage.getCredentialTab();
        credentialTab.openCredentialsTab();

        credentialTab.deleteCredential(0);
        WebElement deleteCredentialMessage = driver.findElements(By.className("delete-credential-message")).get(0);
        credentialTab.getWebDriverWait().until(ExpectedConditions.visibilityOf(deleteCredentialMessage));
        String message = deleteCredentialMessage.getText();
        Assertions.assertEquals("Delete credential successfully!", message);
    }
}
