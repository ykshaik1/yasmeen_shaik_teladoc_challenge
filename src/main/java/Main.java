import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import pojo.Movies;
import pojo.MoviesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Main {
    String uri = "https://jsonmock.hackerrank.com/api/movies/", endpoint = "search";
    Integer initialPage = 0;

    public List<String> getMovieTitles(String title, Integer year) {
    	initialPage = 1;
        List<String> allMovieTitles = new ArrayList<>();
        List<Movies> allMovies = getAllMovies(title, year);

        for (Movies movie : allMovies) {
            allMovieTitles.add(movie.getTitle());
            Collections.sort(allMovieTitles);
        }

        return allMovieTitles;
    }

    private List<Movies> getAllMovies(String title, Integer year) {
        MoviesData moviesData;
        List<Movies> allMovies = new ArrayList<>();
        do {
            moviesData = getAllMovieData(title, year, initialPage);
            allMovies.addAll(moviesData.getData());
            initialPage++;
        } while (initialPage <= moviesData.getTotalPages());
        return allMovies;
    }

    private MoviesData getAllMovieData(String title, Integer year, Integer page) {

        return given().header("Accept", "*/*").filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter())
                .param("Title", title)
                .param("Year", year)
                .param("page", page)
                .when().get(uri + endpoint).then().extract().response().as(MoviesData.class);

    }
}
