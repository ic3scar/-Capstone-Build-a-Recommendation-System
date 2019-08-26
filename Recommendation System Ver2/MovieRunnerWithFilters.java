import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatings(35);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 35 ratings.");
        Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(title + " has average rating " + rating.getValue());
        }
    }    
    
    public void printAverageRatingsByYear(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(20, f);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 20 ratings that are out after 2000.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            int year = MovieDatabase.getYear(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ year);
        }*/     
    }
    
    public void printAverageRatingsByGenre(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(20, f);
        System.out.println("There are " + avgRatingList.size() + " comedy movies with at least 20 ratings.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            String genre = MovieDatabase.getGenres(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ genre);
        }*/        
    }
    
    public void printAverageRatingsByMinutes(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(5, f);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 5 ratings that are between 105 and 135 minutes.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            int minutes = MovieDatabase.getMinutes(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ minutes);
        }*/          
    }
    
    public void printAverageRatingsByDirectors(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(4, f);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 4 ratings filmed by mentioned directors.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            String directors = MovieDatabase.getDirector(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ directors);
        }*/          
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(yearFilter);
        allFilter.addFilter(genreFilter);
        
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(8, allFilter);
        System.out.println("There are " + avgRatingList.size() + " drama movies with at least 8 ratings after 1990.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            int year = MovieDatabase.getYear(rating.getItem());
            String genre = MovieDatabase.getGenres(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ year + ", "+genre);
        }*/            
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings thirdr = new ThirdRatings(ratingsfile);
        System.out.println("There are " + thirdr.getRaterSize() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String dirList = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter directorsFilter = new DirectorsFilter(dirList);
        Filter minutesFilter = new MinutesFilter(90, 180);
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(directorsFilter);
        allFilter.addFilter(minutesFilter);
        
        ArrayList<Rating> avgRatingList = thirdr.getAverageRatingsByFilter(3, allFilter);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 3 rates that are between 90 and 180 minutes that are filmed by the mentioned directors.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            int minutes = MovieDatabase.getMinutes(rating.getItem());
            String directors = MovieDatabase.getDirector(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ directors + ", "+minutes);
        }*/          
    }
}
