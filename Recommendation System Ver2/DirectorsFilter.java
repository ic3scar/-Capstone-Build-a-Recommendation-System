import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors;
    }
    
    public boolean satisfies(String id){
        ArrayList<String> dirList= new ArrayList(Arrays.asList(myDirectors.split(",")));
        for (String dir : dirList){
            if (MovieDatabase.getDirector(id).contains(dir))
                return true;
        }
        return false;
    }
}
