package bank.xyz;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class WebTest {

    @BeforeAll
    static void setup() throws MalformedURLException {
        Configuration.browserSize = "1200x1000";
        Configuration.baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#";

        if (true) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setCapability("enableVNC:", "true"); //--vnc
            capabilities.setCapability("enableVideo:", "true");
            Configuration.browserCapabilities = capabilities;
            WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities);
            setWebDriver(driver);
        } else {
            Configuration.browser = "opera";
        }
    }

    @AfterEach
    void close() {
        driver().close();
    }

}
