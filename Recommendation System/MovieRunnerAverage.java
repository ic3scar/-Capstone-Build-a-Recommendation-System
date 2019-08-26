import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings secondr = new SecondRatings(moviefile, ratingsfile);
        System.out.println("There are " + secondr.getMovieSize() + " movies loaded.");
        System.out.println("There are " + secondr.getRaterSize() + " raters loaded.");
        
        ArrayList<Rating> ratinglist = secondr.getAverageRatings(50);
        for (Rating rating : ratinglist){
            String title = secondr.getTitle(rating.getItem());
            System.out.println(title + " has rating: "+ rating.getValue());
        }
        System.out.println("There are " + ratinglist.size() + " movies with at least 50 ratings.");
        
        ArrayList<Rating> ratinglist12 = secondr.getAverageRatings(12);
        System.out.println("There are " + ratinglist12.size() + " movies with at least 12 ratings.");
        double lowestRating = 100.0;
        String id = "";
        for (Rating rating : ratinglist12){
            double averageRating = rating.getValue();
            if (rating.getValue()<lowestRating){
                lowestRating = averageRating;
                id = rating.getItem();
            }
        }
        String title = secondr.getTitle(id);
        System.out.println(title + " has the lowest rating: "+lowestRating); 
    }
    
    public void getAverageRatingOneMovie(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings secondr = new SecondRatings(moviefile, ratingsfile);
        String title = "Vacation";
        String id = secondr.getID(title);
        System.out.println("The ID of " + title + ": "+id);
        double averageRating = secondr.getAverageByTitle(title);
        if (averageRating!=0.0) System.out.println("Its rating is: "+averageRating);
    }
}
