package pageObjects;

import core.MethodsFactory;
import infoModels.Movie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alex Astakhov on 31.10.2016.
 */
public class ImdbTop250PS extends MethodsFactory{
    private WebDriver driver;

    static StringBuilder source;

    public ImdbTop250PS(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://www.imdb.com/chart/top");
        source = new StringBuilder(this.driver.getPageSource());
    }

    public Movie getNextMovie(){
        source = cutStringBuilder(source, "titleColumn\">");
        StringBuilder title = cutStringBuilder(source, "\">", "</a>");
        String year = cutStringBuilder(source, "secondaryInfo\">(", ")</span>").toString();
        double rate = Double.parseDouble(cutStringBuilder(source, "ratings\">", "</strong>").toString().replace(",", "."));
        source = cutStringBuilder(source, "posterColumn\">");
        return new Movie(title.toString(), year, rate);
    }


    /*public Movie[] getAllMovies(){
        driver.get("http://www.imdb.com/chart/top");
        StringBuilder source = new StringBuilder(driver.getPageSource());
        for (int i = 0; i < 250; i++) {
            MethodsFactory.cutStringBuilder(source, )
        }
        for (int i = 0; i < titles.size(); i++) {
            movies[i] = (new Movie(titles.get(i).getText(), years.get(i).getText().substring(1,5), ratings.get(i).getText().replace(',','.')));
        }
        return movies;
    }*/
}
