package imdb;

import infoModels.Movie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.Constants;
import pageObjects.ImdbTop250;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class ImdbTop250Print {

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
        driver.get(Constants.TOP_250_URL);
        ImdbTop250 imdb = new ImdbTop250(driver);
        Movie[] movies = imdb.getAllMovies();
        int num = 1;
        for (Movie movie: movies){
            System.out.println((num++) + ". " + movie);
        }
        System.out.print("Самый старый фильм: ");
        getOldestMovie(movies);
        System.out.print("Самый новый фильм фильм: ");
        getNewestMovie(movies);
        tearDown(driver);
    }

    public static void getOldestMovie(Movie[] movies){
        Movie oldestMovie = movies[0];
        int year = movies[0].getYear();
        for (Movie movie: movies){
            if (movie.getYear() < year){
                year = movie.getYear();
                oldestMovie = movie;
            }
        }
        System.out.println(oldestMovie.getTitle() + " (" + oldestMovie.getYear() + ")");
    }

    public static void getNewestMovie(Movie[] movies){
        Movie newestMovie = movies[0];
        int year = movies[0].getYear();
        for (Movie movie: movies){
            if (movie.getYear() > year){
                year = movie.getYear();
                newestMovie = movie;
            }
        }
        System.out.println(newestMovie.getTitle() + " (" + newestMovie.getYear() + ")");
    }
}
