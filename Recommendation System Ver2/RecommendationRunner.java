import java.util.*;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * http://www.dukelearntoprogram.com/capstone/recommender.php?id=ad1X8RUps4Q3q9
 * Found the CSS part from online resource.
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        String[] movies = {"1220634", "0790636", "2278388", "1631867", "1670345",
                           "2582802", "0816692", "1821549", "2334879", "2053463",
                           "2980516", "1343092", "1675434", "0119217", "1800241",
                           "1959490", "1392214", "2024544", "0796366", "1457767"};
        ArrayList<String> moviesToRate = new ArrayList<String>();
        for (int i=0; i<20; i++)
            moviesToRate.add(movies[i]);
        return moviesToRate;
    }
    
    public void printRecommendationsFor (String webRaterID){
	FourthRatings fourthr = new FourthRatings();
	int numSimilarRaters = 10;
	int minimalRaters = 3; 
	
	ArrayList<Rating> ratersSimilarRating = fourthr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
	if (ratersSimilarRating.size() == 0){
	    System.out.println("The input is invalid.");
	    return;
	}
	printUpperPart();
	for (int i=0; i<20 && i<ratersSimilarRating.size(); i++){
	    Rating r = ratersSimilarRating.get(i);
            if((i+1)%2 == 0){
                System.out.println("<tr class=\"even_rows\"><td>" + (i+1) + "</td>");
            }
            else{
                System.out.println("<tr class=\"odd_rows\"><td>" + (i+1) + "</td>");
            }	   
            
            String URL = MovieDatabase.getPoster(r.getItem());
            String title = MovieDatabase.getTitle(r.getItem());
            String director = MovieDatabase.getDirector(r.getItem());
            String country = MovieDatabase.getCountry(r.getItem());
            int year = MovieDatabase.getYear(r.getItem());
            String genre = MovieDatabase.getGenres(r.getItem());
            int minutes = MovieDatabase.getMinutes(r.getItem());

            System.out.println("<td><table><tr><td class = \"pic\">");

            /*if(URL.length()>3){
                System.out.println("<img src = \""+URL+"\" target=_blank></td>");
            }*/
                  
            System.out.println("<td><h3>"+ title+"</h3>");
            System.out.println("<b>by "+ genre+"</b><br>");
            System.out.println(year+"<br>");
            System.out.println(country+"<br>");
            System.out.println(minutes+" minutes</td></tr></table></td></tr>");         
	}
	printLowerPart();
    }
    
    private void printUpperPart(){
        System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Recommended Movies:</h2></div><table class=\"outside_table\"><tr  class=\"table-header\"><th>&nbsp</th><th class=\"movie_title\">Title</th></tr>");
    }
     
    private void printCSS(){
         System.out.println("<style>* {margin: 0;padding: 0;}img{height: 100px;margin-right:10px;}#header{background-color: #F49F58;margin-top: 0;height: 100px;}h2{padding-left: 15px;padding-top: 40px;color: #FFFFFF;}h3{}body{margin-top: 0;font-family: 'Arial'}th{text-align: left;font-family: 'Arial', sans-serif;padding-top:15px;padding-bottom: 7px;}td{padding-top: 10px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;}tr{padding-bottom: 10px;}.table-header{background-color: #FFB97F;}.odd_rows{background-color: #FFE4CC;}.even_rows{background-color: #FFFFFF;}.outside_table{width: 100%;border-collapse: collapse;}.movie_title{width = 40%;}</style>");
    }
     
    private void printLowerPart(){
        System.out.println("</table>");
    }
}
