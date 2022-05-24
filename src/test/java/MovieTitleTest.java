import org.testng.annotations.Test;

public class MovieTitleTest {
    Main movie = new Main();

@Test
    public void getMovieDetails(){
    movie.getMovieTitles("a",2000);
}
}
