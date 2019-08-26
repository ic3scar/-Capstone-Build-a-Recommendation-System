import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        ArrayList<Rating> avgRatingList = fourthr.getAverageRatings(35);
        System.out.println("There are " + avgRatingList.size() + " movies with at least 35 ratings.");
        Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(title + " has average rating " + rating.getValue());
        }
    }   
    
    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(yearFilter);
        allFilter.addFilter(genreFilter);
        
        ArrayList<Rating> avgRatingList = fourthr.getAverageRatingsByFilter(8, allFilter);
        System.out.println("There are " + avgRatingList.size() + " drama movies with at least 8 ratings after 1990.");
        /*Collections.sort(avgRatingList);
        for (Rating rating : avgRatingList){
            String title = MovieDatabase.getTitle(rating.getItem());
            int year = MovieDatabase.getYear(rating.getItem());
            String genre = MovieDatabase.getGenres(rating.getItem());
            System.out.println(rating.getValue() + ", "+ title + ", "+ year + ", "+genre);
        }*/            
    }    
    
    public void printSimilarRatings(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String raterID = "71";
        ArrayList<Rating> similarRatings = fourthr.getSimilarRatings(raterID, 20, 5);
        Collections.sort(similarRatings, Collections.reverseOrder());
        for (Rating rating : similarRatings){
            String movieID = rating.getItem();
            System.out.println(movieID + ", " + MovieDatabase.getTitle(movieID) + ", " + rating.getValue());
        }
        System.out.println(similarRatings.size());
    }
    
    public void printSimilarRatingsByGenre(){
        System.out.println();
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String raterID = "964";
        Filter genreFilter = new GenreFilter("Mystery");
        ArrayList<Rating> similarRatings = fourthr.getSimilarRatingsByFilter(raterID, 20, 5, genreFilter);
        Collections.sort(similarRatings, Collections.reverseOrder());
        for (Rating rating : similarRatings){
            String movieID = rating.getItem();
            System.out.println(movieID + ", " + MovieDatabase.getTitle(movieID) + ", " + rating.getValue());
        }
        System.out.println(similarRatings.size());
    }
    
    public void printSimilarRatingsByDirector(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String raterID = "120";
        String dirList = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter directorsFilter = new DirectorsFilter(dirList);
        ArrayList<Rating> similarRatings = fourthr.getSimilarRatingsByFilter(raterID, 10, 2, directorsFilter);
        Collections.sort(similarRatings, Collections.reverseOrder());
        for (Rating rating : similarRatings){
            String movieID = rating.getItem();
            System.out.println(movieID + ", " + MovieDatabase.getTitle(movieID) + ", " + rating.getValue());
        }
        System.out.println(similarRatings.size());        
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String raterID = "168";
        Filter minutesFilter = new MinutesFilter(80, 160);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(genreFilter);
        
        ArrayList<Rating> similarRatings = fourthr.getSimilarRatingsByFilter(raterID, 10, 3, allFilter);
        Collections.sort(similarRatings, Collections.reverseOrder());
        for (Rating rating : similarRatings){
            String movieID = rating.getItem();
            System.out.println(movieID + ", " + MovieDatabase.getTitle(movieID) + ", " + rating.getValue());
        }
        System.out.println(similarRatings.size());         
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        FourthRatings fourthr = new FourthRatings();
        
        RaterDatabase.initialize(ratingsfile);
        System.out.println("There are " + RaterDatabase.size() + " raters loaded.");
        
        MovieDatabase.initialize(moviefile);
        System.out.println("There are " + MovieDatabase.size() + " movies loaded in the database.");
        
        String raterID = "314";
        Filter minutesFilter = new MinutesFilter(70, 200);
        Filter yearAfterFilter = new YearAfterFilter(1975);
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(yearAfterFilter);
        
        ArrayList<Rating> similarRatings = fourthr.getSimilarRatingsByFilter(raterID, 10, 5, allFilter);
        Collections.sort(similarRatings, Collections.reverseOrder());
        for (Rating rating : similarRatings){
            String movieID = rating.getItem();
            System.out.println(movieID + ", " + MovieDatabase.getTitle(movieID) + ", " + rating.getValue());
        }
        System.out.println(similarRatings.size());          
    }
}
