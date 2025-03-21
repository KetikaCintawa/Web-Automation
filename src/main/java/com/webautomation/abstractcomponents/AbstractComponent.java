package com.webautomation.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Digunakan untuk global function 
 * bisa dipakai extends di setiap class
 */

public class AbstractComponent {
    WebDriver driver;
    
    public AbstractComponent(WebDriver driver){
        this.driver = driver;
    }
    public void visibilityOfElementLocated(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
}
