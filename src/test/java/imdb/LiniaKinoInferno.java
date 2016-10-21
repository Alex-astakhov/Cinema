package imdb;

import infoModels.Seat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.LiniaKino;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class LiniaKinoInferno {
    static WebDriver setUpBrowser(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    static void tearDown(WebDriver driver){
        driver.close();
        driver.quit();
    }

    public static void main(String[] args) {
        WebDriver driver = setUpBrowser();
        driver.get("http://liniakino.com/showtimes/aladdin/");
        LiniaKino kino = new LiniaKino(driver);
        kino.seanceClick();
        List<Seat> seats = kino.getAllSeats();
        for (Seat s: seats){
            System.out.println(s);
        }
        tearDown(driver);
    }
}
