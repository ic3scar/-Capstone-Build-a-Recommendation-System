
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private HashMap<String, Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings firstr = new FirstRatings();
        myMovies = firstr.loadMovies(moviefile);
        myRaters = firstr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> ratinglist = new ArrayList<Rating>();
        for (Movie movie : myMovies){
            String id = movie.getID();
            double aveRating = getAverageByID(id, minimalRaters);
            if (aveRating!=0.0){
                Rating rating = new Rating(id, aveRating);
                ratinglist.add(rating);
            }
        }
        return ratinglist;
    }
    
    public String getTitle(String id){
        String res = "Not Found";
        for (Movie movie : myMovies){
            if (movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return res;
    }
    
    public String getID(String title){
        String res = "No such title.";
        for (Movie movie : myMovies){
            if (movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return res;
    }
    
    public double getAverageByTitle(String title){
        String id = getID(title);
        double averating = getAverageByID(id, 1);
        return averating;
    }
}
