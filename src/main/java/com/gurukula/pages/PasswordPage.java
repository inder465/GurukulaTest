package com.gurukula.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PasswordPage extends BasicPage {
	// Constructor
	public PasswordPage(WebDriver driver) {
		super(driver);

	}

	// Elements

	@FindBy(xpath = "/html/body/div[3]/div[1]/div/div/div/div[2]/strong")
	private WebElement lblPasswordErrorMsg;

	@FindBy(name = "password")
	private WebElement txtbxPassword;

	@FindBy(name = "confirmPassword")
	private WebElement txtbxCnfPassword;

	@FindBy(xpath = "//form/button[text()='Save']")
	private WebElement btnSave;

	// Password Error Text Label
	@FindBy(xpath = "//div[1]/div/p")
	private List<WebElement> lblPasswordError;

	// ConfirmPassword Error Text Label
	@FindBy(xpath = "//div[2]/div/p")
	private List<WebElement> lblConfirmPasswordError;

	// Password Strength
	@FindBy(xpath = ".//*[@id='strengthBar']/li[1]")
	private WebElement barStrength1;

	@FindBy(xpath = ".//*[@id='strengthBar']/li[2]")
	private WebElement barStrength2;

	@FindBy(xpath = ".//*[@id='strengthBar']/li[3]")
	private WebElement barStrength3;

	@FindBy(xpath = ".//*[@id='strengthBar']/li[4]")
	private WebElement barStrength4;

	@FindBy(xpath = ".//*[@id='strengthBar']/li[5]")
	private WebElement barStrength5;

	// PasswordMismatch Error Text Label
	@FindBy(xpath = "//div[3]")
	private WebElement lblPasswordMismatchError;

	// Methods
	public void enterNewPassword(String npwd) {
		txtbxPassword.clear();
		txtbxPassword.sendKeys(npwd);
	}

	public void clearNewPassword() {
		txtbxPassword.clear();

	}

	public void clearNewConfPassword() {
		txtbxCnfPassword.clear();

	}

	public void enterNewCnfPassword(String ncnfpwd) {
		txtbxCnfPassword.clear();
		txtbxCnfPassword.sendKeys(ncnfpwd);
	}

	public String getChangePwdError() {
		return lblPasswordErrorMsg.getText();
	}

	public void clickSave() {
		if (btnSave.isEnabled()) {
			btnSave.click();
		} else {
			System.out.println("Save button is disabled!");
		}
	}

	public String getPasswordErrorMessage() {
		String msg = "";
		for (WebElement element : lblPasswordError) {
			if (element.getAttribute("class").equals("help-block ng-scope")) {

				msg = element.getText();
				// System.out.println("Displayed and text: " + msg);
			}

		}
		Reporter.log("Verification of Password Error Message " + msg + "->");
		return msg;
	}

	public String getConfirmPasswordErrorMessage() {
		String msg = "";
		for (WebElement element : lblConfirmPasswordError) {
			if (element.getAttribute("class").equals("help-block ng-scope")) {

				msg = element.getText();
				// System.out.println("Displayed and text: " + msg);
			}

		}
		Reporter.log("Verification of Confirm Password Error Message " + msg + "->");
		return msg;
	}

	public String getPasswordMismatchError() {
		Reporter.log("Verification of Password Mismatch Error Message ->");
		return lblPasswordMismatchError.getText();
	}

	public String getPasswordStrength() {

		String[] strength = new String[5];

		// Get CSS property for background
		strength[0] = barStrength1.getCssValue("background");
		strength[1] = barStrength2.getCssValue("background");
		strength[2] = barStrength3.getCssValue("background");
		strength[3] = barStrength4.getCssValue("background");
		strength[4] = barStrength5.getCssValue("background");

		// trim color information
		strength[0] = strength[0].substring(0, strength[0].indexOf(')') + 1);
		strength[1] = strength[1].substring(0, strength[1].indexOf(')') + 1);
		strength[2] = strength[2].substring(0, strength[2].indexOf(')') + 1);
		strength[3] = strength[3].substring(0, strength[3].indexOf(')') + 1);
		strength[4] = strength[4].substring(0, strength[4].indexOf(')') + 1);

		// Decode color value to color

		// System.out.println(decodeColorBar(strength));

		return this.decodeColorBar(strength);

	}
}
