package com.onesmas;

import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Locale;

/**
 * Creates the browser for every test.
 *
 * -Dbrowser=chrome|firefox|edge|safari   (default chrome)
 * -Dgrid.url=http://localhost:4444       (omit to run locally)
 * GRID_USER / GRID_PASS env variables    (only for a Grid that needs a login)
 */
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
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        };

        if (gridUrl.isBlank()) {
            return switch (browser) {
                case "firefox" -> new FirefoxDriver((FirefoxOptions) options);
                case "edge" -> new EdgeDriver((EdgeOptions) options);
                case "safari" -> new SafariDriver((SafariOptions) options);
                default -> new ChromeDriver((ChromeOptions) options);
            };
        }

        ClientConfig config = ClientConfig.defaultConfig();
        String user = System.getenv("GRID_USER");
        String pass = System.getenv("GRID_PASS");
        if (user != null && pass != null) {
            config = config.authenticateAs(new UsernameAndPassword(user, pass));
        }

        try {
            return RemoteWebDriver.builder()
                    .oneOf(options)
                    .address(URI.create(gridUrl).toURL())
                    .config(config)
                    .build();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Bad grid.url: " + gridUrl, e);
        }
    }
}
