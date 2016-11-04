package pageObjects;

import core.MethodsFactory;
import infoModels.Movie;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alex Astakhov on 31.10.2016.
 */
public class KinopoiskTop250PS extends MethodsFactory{
    private WebDriver driver;

    static StringBuilder source;

    public KinopoiskTop250PS(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.kinopoisk.ru/top/");
        source = new StringBuilder(this.driver.getPageSource());
    }

    public Movie getNextMovie(){
        source = cutStringBuilder(source, "id=\"top250_place");
        source = cutStringBuilder(source, "class=\"all\"");
        StringBuilder title = cutStringBuilder(source, "\">", " (");
        String year = cutStringBuilder(source, " (", ")</a>").toString();
        source = cutStringBuilder(source, "class=\"continue\"");
        double rate = Double.parseDouble(cutStringBuilder(source, "\">", "</a>").toString());
        return new Movie(title.toString(), year, rate);
    }
}
