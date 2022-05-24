import pojo.Movies;
import pojo.MoviesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Main {
    String uri = "https://jsonmock.hackerrank.com/api/movies/", endpoint = "search";
    Integer initialPage = 1;

    public List<String> getMovieTitles(String title, Integer year) {
        List<Movies> allMovies = new ArrayList<>();
        List<String> allMovieTitles = new ArrayList<>();
        MoviesData moviesData;

        do{
            moviesData  = getAllMovieData(title, year, initialPage);
            allMovies.addAll(moviesData.getData());
            initialPage++;
            System.out.println("size = "+allMovies.size());
        }while(initialPage<= moviesData.getTotalPages());

        for (Movies movie: allMovies) {
            allMovieTitles.add(movie.getTitle());
            Collections.sort(allMovieTitles);
        }

        return allMovieTitles;

    }

    private MoviesData getAllMovieData(String title, Integer year, Integer page) {

        return given().header("Accept", "*/*")
                .param("Title", title)
                .param("Year", year)
                .param("page", page)
                .when().get(uri + endpoint).then().extract().response().as(MoviesData.class);

    }
}
