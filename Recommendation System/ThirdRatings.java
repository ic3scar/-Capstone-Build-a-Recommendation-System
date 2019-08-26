import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private HashMap<String, Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings firstr = new FirstRatings();
        myRaters = firstr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double total = 0.0;
        for (Rater rater : myRaters.values()){
            if (rater.hasRating(id)){
                count++;
                total += rater.getRating(id);
            }
        }
        if (count>=minimalRaters) return total/count;
        else return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
        for (String id : myMovies){
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating!=0.0){
                Rating rating = new Rating(id, avgRating);
                avgRatingList.add(rating);
            }
        }
        return avgRatingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
        for (String id : myMovies){
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating!=0.0){
                Rating rating = new Rating(id, avgRating);
                avgRatingList.add(rating);
            }
        }
        return avgRatingList;
    }
}
