package imdb;

import infoModels.Movie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ImdbTop250;
import pageObjects.ImdbTop250PS;
import pageObjects.KinopoiskTop250PS;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Astakhov on 31.10.2016.
 */
public class ImdbAndKinopoisk {

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

    public static Movie[] getImdbTop(WebDriver driver){
        ImdbTop250PS imdb = new ImdbTop250PS(driver);
        Movie[] imdbMovies = new Movie[250];
        for (int i = 0; i < 250; i++) {
            imdbMovies[i] = imdb.getNextMovie();
        }
        return imdbMovies;
    }

    public static Movie[] sortMovies(Movie[] movies){
        boolean swap = true;
        while (swap){
            swap = false;
            for (int i = 0; i < movies.length - 1; i++) {
                if (movies[i].getTitle().compareToIgnoreCase(movies[i + 1].getTitle()) > 0){
                    Movie m = movies[i];
                    movies[i] = movies[i + 1];
                    movies[i + 1] = m;
                    swap = true;
                }
            }
        }
        return movies;
    }

    public static Movie[] getKinopoiskTop(WebDriver driver){
        KinopoiskTop250PS kinopoisk = new KinopoiskTop250PS(driver);
        Movie[] kinopoiskMovies = new Movie[250];
        for (int i = 0; i < 250; i++) {
            kinopoiskMovies[i] = kinopoisk.getNextMovie();
        }

        return kinopoiskMovies;
    }

    public static void main(String[] args) {
        WebDriver driver = setUpBrowser();
        Movie[] imdbMovies = getImdbTop(driver);
        Movie[] kinopoiskMovies = getKinopoiskTop(driver);
        tearDown(driver);
        imdbMovies = sortMovies(imdbMovies);
        for (int i = 0; i < 250; i++) {
            System.out.print((i+1) + ". " + imdbMovies[i].getTitle() + ": IMDB: " + imdbMovies[i].getRate());
            for (int j = 0; j < 250; j++) {
                if (imdbMovies[i].getTitle().equals(kinopoiskMovies[j].getTitle())){
                    System.out.print("/ Kinopoisk: " + kinopoiskMovies[j].getRate());
                }
            }
            System.out.println();
        }


    }
}
