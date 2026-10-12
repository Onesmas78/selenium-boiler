package com.onesmas;

import org.junit.platform.commons.function.Try;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Locale;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver create() {
        String browser = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);
        String gridUrl = System.getProperty("grid.url", "");

        AbstractDriverOptions<?> options = switch (browser) {
            case "firefox" -> new FirefoxOptions();
            case "edge" -> new EdgeOptions();
            case "safari" -> new SafariOptions();
            case "chrome" -> new ChromeOptions();
            default -> throw new IllegalArgumentException("Unknown Browser:" + browser);
        };

        if (gridUrl.isBlank()) {
            return switch (browser) {
                case "firefox" -> new FirefoxDriver((FirefoxOptions) options);
                case "edge" -> new EdgeDriver((EdgeOptions) options);
                case "safari" -> new SafariDriver((SafariOptions) options);
                default -> new ChromeDriver((ChromeOptions) options);
            };
        }

        try {
            return new RemoteWebDriver(URI.create(gridUrl).toURL(), options);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Bad url" + gridUrl, e);
        }
    }

}
