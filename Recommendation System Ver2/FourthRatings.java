import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double total = 0.0;
        for (Rater rater : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        double similarity = 0;
        ArrayList<String> myRatedItems = me.getItemsRated();
        
        for (String id : myRatedItems){
            if (r.hasRating(id)){
                similarity += (me.getRating(id)-5)*(r.getRating(id)-5);
            }
        }
        return similarity;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : allRaters){
            if (!r.getID().equals(id)){
                //System.out.println(me.getID() + "  " + r.getID());
                double similarity = dotProduct(me, r);
                if (similarity>0){
                    Rating similarityRating = new Rating(r.getID(), similarity);
                    res.add(similarityRating);
                }
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> moviesSimilarRating = new ArrayList();
        ArrayList<Rating> ratersSimilarRating = getSimilarities(id);
        System.out.println("Successfully loaded.");
        for (String movieID : MovieDatabase.filterBy(filterCriteria)){
            int count = 0;
            double totalWeight = 0.0;
            
            for (int i=0; i<numSimilarRaters && i<ratersSimilarRating.size(); i++){
                Rating rating = ratersSimilarRating.get(i);
                Rater r = RaterDatabase.getRater(rating.getItem());
                //System.out.println("Here");
                if (r.hasRating(movieID)){
                    count++;
                    totalWeight += rating.getValue() * r.getRating(movieID);
                }
            }
            if (count>=minimalRaters){
                Rating avgMovieRating = new Rating(movieID, totalWeight/count);
                moviesSimilarRating.add(avgMovieRating);
            }
        }
        Collections.sort(moviesSimilarRating, Collections.reverseOrder());
        return moviesSimilarRating;
    }
}
