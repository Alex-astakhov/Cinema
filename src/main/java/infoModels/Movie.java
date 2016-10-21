package infoModels;

/**
 * Created by Alex Astakhov on 16.10.2016.
 */
public class Movie {
    private String title;
    private int year;
    private double rate;

    public Movie(String title, String year, String rate){
        this.title = title;
        this.year = Integer.parseInt(year);
        this.rate = Double.parseDouble(rate);
    }

    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public double getRate(){
        return rate;
    }

    @Override
    public String toString(){
        return getTitle() + " - " + getRate();
    }
}
