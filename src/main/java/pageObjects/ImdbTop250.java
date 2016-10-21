package pageObjects;

import infoModels.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class ImdbTop250 {
    private WebDriver driver;

    private By movieTitle = By.cssSelector(".titleColumn a");
    private By movieYear = By.cssSelector(".titleColumn span");
    private By movieRating = By.cssSelector(".ratingColumn strong");

    public ImdbTop250(WebDriver driver){
        this.driver = driver;
    }

    public Movie[] getAllMovies(){
        List<WebElement> titles = driver.findElements(movieTitle);
        List<WebElement> years = driver.findElements(movieYear);
        List<WebElement> ratings = driver.findElements(movieRating);
        Movie[] movies = new Movie[titles.size()];
        for (int i = 0; i < titles.size(); i++) {
            movies[i] = (new Movie(titles.get(i).getText(), years.get(i).getText().substring(1,5), ratings.get(i).getText().replace(',','.')));
        }
        return movies;
    }

}
