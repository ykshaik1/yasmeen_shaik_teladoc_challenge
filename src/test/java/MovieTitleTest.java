import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class MovieTitleTest {
    Main movie;

    @BeforeClass
    public void initiateApp() {
        movie = new Main();

    }

    @Test(priority = 0)
    public void Test01_Movie_Should_Contain_Search_Term() {
        String searchTerm = "maze";
        Integer year = 2000;
        List<String> titles = movie.getMovieTitles(searchTerm, year);
        Assert.assertTrue(titles.get(0).toLowerCase().contains(searchTerm),"results do not contain search term");
        Reporter.log("Search term "+searchTerm+" found in first result : "+titles.get(0),true);
        Assert.assertTrue(titles.get(titles.size()-1).toLowerCase().contains(searchTerm),"results do not contain search term");
        Reporter.log("Search term "+searchTerm+" found in last result : "+titles.get(titles.size()-1),true);
    }

    @Test(priority = 1)
    public void Test02_No_Results_For_Incorrect_Search_Term(){
        String searchTerm = "noSuchMovie";
        Integer year = 2000;
        List<String> titles = movie.getMovieTitles(searchTerm, year);
        Assert.assertTrue(titles.size()==0,"Garbage results received for search term : "+searchTerm);
        Reporter.log("No results for search term : "+searchTerm,true);
    }

    @Test(priority = 2)
    public void Test03_No_Results_For_Incorrect_Search_Year(){
        String searchTerm = "maze";
        Integer year = 1000;
        List<String> titles = movie.getMovieTitles(searchTerm, year);
        Assert.assertTrue(titles.size()==0,"Garbage results received for search year : "+year);
        Reporter.log("No results for search year : "+year,true);
    }

}
