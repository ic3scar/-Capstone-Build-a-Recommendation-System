import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for (CSVRecord record : fr.getCSVParser()){
            Movie curMovie = new Movie(record.get("id"), record.get("title"), record.get("year"),
                                       record.get("genre"),record.get("director"),record.get("country"),
                                       record.get("poster"),Integer.parseInt(record.get("minutes")));;
            movieList.add(curMovie);
        }
        return movieList;
    }
    
    public HashMap<String, Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        HashMap<String, Rater> raterList = new HashMap<String, Rater>();
        for (CSVRecord record : fr.getCSVParser()){
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            String time = record.get("time");
            
            if (!raterList.containsKey(rater_id))
                raterList.put(rater_id, new EfficientRater(rater_id));
            raterList.get(rater_id).addRating(movie_id, rating);
        }
        return raterList;
    }
    
    public void testLoadMovies(){
        String filename = "data/ratedmoviesfull.csv";
        System.out.println("Testing loadMovies:");
        ArrayList<Movie> movieList = loadMovies(filename);
        /*for (Movie movie : movieList){
            if (movie.getGenres().indexOf("Comedy")>-1){
                System.out.println(movie.getTitle() + " has Comedy genre.");
            }
        }*/
        System.out.println("There are "+movieList.size() + " movies loaded.");
        int longMovieCount = 0;
        int comedyCount = 0;
        for (Movie movie : movieList){
            if (movie.getMinutes()>150){
                longMovieCount++;
            }
            if (movie.getGenres().indexOf("Comedy")>-1){
                comedyCount++;
            }
        }
        System.out.println("There are "+comedyCount+" movies that have Comedy genre.");
        System.out.println("There are "+longMovieCount+" movies that are longer than 150 minutes.");
        
        HashMap<String, Integer> numMoviesByDirector = new HashMap<String, Integer>();
        for (Movie movie : movieList){
            String director = movie.getDirector();
            if (! numMoviesByDirector.containsKey(director)){
                numMoviesByDirector.put(director, 1);
            }
            else{
                numMoviesByDirector.put(director, numMoviesByDirector.get(director)+1);
            }
        }
        int maxNum = 0;
        String maxDirector = "";
        for (String director : numMoviesByDirector.keySet()){
            int num = numMoviesByDirector.get(director);
            if (maxNum<num){ 
                maxNum = num;
                maxDirector = director;
            }
        }
        System.out.println(maxDirector + " has the most amount of movies: "+maxNum);
    }
    
    public void testLoadRaters(){
        String filename = "data/ratings.csv";
        System.out.println("Testing loadRaters:");
        HashMap<String, Rater> raterList = loadRaters(filename);
        System.out.println("Number of raters: "+raterList.size());
        /*for (Rater rater : raterList){
            System.out.println("Rater " + rater.getID() + " has " + rater.numRatings() + "ratings.");
            for (String item : rater.getItemsRated()){
                System.out.println("Movie "+item+" has rating "+rater.getRating(item));
            }
        }*/
        
        String rater_id = "193";
        System.out.println(raterList.get(192).getID() + " has " + raterList.get(192).numRatings());
        
        String maxRater = "";
        int maxNum = 0;
        for (Rater rater : raterList.values()){
            if (rater.numRatings()>maxNum){
                maxNum = rater.numRatings();
                maxRater = rater.getID();
            }
        }
        System.out.println("Max Rater: "+maxRater+" , maxNum: "+maxNum);
        System.out.println();
        
        String movie_id = "1798709";
        int count = 0;
        System.out.println("Checking the rating for movie_id " + movie_id);
        for (Rater rater : raterList.values()){
            if (rater.hasRating(movie_id)){
                count ++;
            }
        }
        System.out.println(count);
        
        System.out.println();
        HashSet<String> movies = new HashSet<String>();
        for (Rater rater : raterList.values()){
            for (String item : rater.getItemsRated()){
                if (!movies.contains(item)){
                    movies.add(item);
                }
            }
        }
        System.out.println("There are " + movies.size() + " different movies");

    }
}
