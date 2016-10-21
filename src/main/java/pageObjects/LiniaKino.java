package pageObjects;

import infoModels.Seat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class LiniaKino {
    WebDriver driver;

    private By seance = By.xpath(".//*[contains(text(),'19 жовтня')]/..//a[contains(text(),'19:20')]");
    private By frame = By.cssSelector("iframe");
    private By seat = By.xpath(".//*[@id='hall-scheme-container']//div[@id]");

    public LiniaKino(WebDriver driver){
        this.driver = driver;
    }

    public void seanceClick(){
        driver.findElement(seance).click();
    }

    public List<Seat> getAllSeats(){
        driver.switchTo().frame(driver.findElement(frame));
        List<WebElement> elements =  driver.findElements(seat);
        List<Seat> seats = new ArrayList<>();
        int row = 1;
        int seat = 0;
        for (WebElement e: elements){
            int curNum = Integer.parseInt(e.getText());
            boolean occup = false;
            if (seat > curNum){
                row++;
            }
            seat = curNum;
            if (e.getAttribute("class").equals("seat seat-occupied")){
                occup = true;
            }
            seats.add(new Seat(row, seat, occup));
        }
        return seats;
    }

}
