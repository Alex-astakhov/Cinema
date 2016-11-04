package imdb;

import infoModels.Movie;
import pageObjects.ImdbTop250Rest;
import pageObjects.KinopoiskTop250Rest;

import java.io.IOException;

/**
 * Created by Alex Astakhov on 31.10.2016.
 */
public class ImdbAndKinopoiskRest {

    public static Movie[] getImdbTop() throws IOException {
        ImdbTop250Rest imdb = new ImdbTop250Rest();
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

    public static Movie[] getKinopoiskTop() throws IOException {
        KinopoiskTop250Rest kinopoisk = new KinopoiskTop250Rest();
        Movie[] kinopoiskMovies = new Movie[250];
        for (int i = 0; i < 250; i++) {
            kinopoiskMovies[i] = kinopoisk.getNextMovie();
        }

        return kinopoiskMovies;
    }

    public static void main(String[] args) throws IOException {
        Movie[] imdbMovies = getImdbTop();
        Movie[] kinopoiskMovies = getKinopoiskTop();
        imdbMovies = sortMovies(imdbMovies);
        int npp = 0;
        for (int i = 0; i < 250; i++) {
            for (int j = 0; j < 250; j++) {
                if (imdbMovies[i].getTitle().equals(kinopoiskMovies[j].getTitle()) && (imdbMovies[i].getYear() == kinopoiskMovies[j].getYear())){
                    System.out.println(++npp + ". " + imdbMovies[i].getTitle() + " -");
                    System.out.print("    IMDB: " + imdbMovies[i].getRate());
                    System.out.print("/ Kinopoisk: " + kinopoiskMovies[j].getRate());
                    System.out.println("\n");
                    break;
                }
            }
        }


    }
}
