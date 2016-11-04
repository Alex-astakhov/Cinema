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
public class ImdbTop250Rest extends MethodsFactory{

    static StringBuilder source;

    public ImdbTop250Rest() throws IOException {

        HttpResponse httpResponse = Request.Get("http://www.imdb.com/chart/top").addHeader("Accept-Language","en-EN").execute().returnResponse();
        HttpEntity entity = httpResponse.getEntity();
        source = new StringBuilder(EntityUtils.toString(entity));
    }

    public Movie getNextMovie(){
        source = cutStringBuilder(source, "titleColumn\">");
        StringBuilder title = cutStringBuilder(source, ">", "</a>");
        String year = cutStringBuilder(source, "secondaryInfo\">(", ")</span>").toString();
        double rate = Double.parseDouble(cutStringBuilder(source, "ratings\">", "</strong>").toString().replace(",", "."));
        source = cutStringBuilder(source, "posterColumn\">");
        return new Movie(title.toString(), year, rate);
    }

}
