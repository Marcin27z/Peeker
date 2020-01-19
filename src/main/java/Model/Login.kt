package Model

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class Login(driver: WebDriver, loginFieldXpath: String, passwordFieldXpath: String, submitButtonXpath: String) {

    private var usernameField: WebElement = driver.findElement(By.xpath(loginFieldXpath))
    private var passwordField: WebElement = driver.findElement(By.xpath(passwordFieldXpath))
    private var submitButton: WebElement = driver.findElement(By.xpath(submitButtonXpath))

    fun login(username: String, password: String) {
        usernameField.sendKeys(username)
        passwordField.sendKeys(password)
        submitButton.click()
    }

}