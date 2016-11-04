package pageObjects;

import core.MethodsFactory;
import infoModels.Movie;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Alex Astakhov on 31.10.2016.
 */
public class KinopoiskTop250Rest extends MethodsFactory{
    static StringBuilder source;

    public KinopoiskTop250Rest() throws IOException {
        HttpResponse httpResponse = Request.Get("https://www.kinopoisk.ru/top/").execute().returnResponse();
        HttpEntity entity = httpResponse.getEntity();
        source = new StringBuilder(EntityUtils.toString(entity));
    }

    public Movie getNextMovie(){
        source = cutStringBuilder(source, "id=\"top250_place");
        source = cutStringBuilder(source, "class=\"all\"");
        String year = cutStringBuilder(source, " (", ")</a>").toString();
        StringBuilder title = cutStringBuilder(source, "class=\"text-grey\">", "</span>");
        source = cutStringBuilder(source, "class=\"continue\"");
        double rate = Double.parseDouble(cutStringBuilder(source, ">", "</a>").toString());
        return new Movie(title.toString(), year, rate);
    }
}
