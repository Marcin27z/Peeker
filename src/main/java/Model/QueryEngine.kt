package Model

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

class QueryEngine: Runnable {

    private lateinit var driver: WebDriver
    private lateinit var queryEntries: List<QueryEntry>
    private lateinit var queryResults: ArrayList<QueryResult>
    private val observers = ArrayList<Observer>()

    override fun run() {
        val firefoxOptions = FirefoxOptions()
        firefoxOptions.setHeadless(true)
        System.setProperty("webdriver.gecko.driver","C:\\Tools\\geckodriver.exe")
        driver = FirefoxDriver(firefoxOptions)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        Stream.of(queryEntries).forEach { (queryEntry) ->
            driver.get(queryEntry.loginUrl)
            queryResults.add(QueryResult())
            if (queryEntry.login != "") {
                val login = Login(driver, queryEntry.loginFieldXpath, queryEntry.passwordFieldXpath, queryEntry.submitButtonXpath)
                login.login(queryEntry.login, queryEntry.password)
            }
        }
        while (true) {
            for (queryEntry: QueryEntry in queryEntries) {
                driver.navigate().to(queryEntry.url)
                queryResults[queryEntries.indexOf(queryEntry)].setResult(driver.findElement(By.xpath(queryEntry.xPath)).text)
            }
            notifyObservers()
            Thread.sleep(5000)
        }
    }

    fun setQueryEntries(queryEntries: List<QueryEntry>) {
        this.queryEntries = queryEntries
    }

    fun setQueryResults(queryResults: ArrayList<QueryResult>) {
        this.queryResults = queryResults
    }

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    private fun notifyObservers() {
        for(observer in observers) {
            observer.notifyObserver()
        }
    }
}