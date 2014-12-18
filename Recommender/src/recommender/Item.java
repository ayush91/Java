package recommender;

import java.util.Comparator;
/**
 * 
 * @author Michael Chan, Danny Troung, Paul Lee, Andrew Bergeron, Ayush Gupta, Nick Marlowe
 *CSC 3102
 *Programming Project #2
 *Professor Zhang
 */
public class Item implements Comparable<Item>{

	    public int userId, movieId;
	    public double rating;
	    public final double UNKNOWNRATING = -999;
	    public Item(int u, int m, double r) {
	        userId = u;
	        movieId = m;
	        rating = r;
	    }
	    public Item(int u, int m) {
	        userId = u;
	        movieId = m;
	        rating = UNKNOWNRATING;
	    }
	    public static Comparator<Item> compUserId = new Comparator<Item>() {
	    @Override
	    public int compare(Item i1, Item i2) {
	        if(i1.userId != i2.userId)
	            return i1.userId - i2.userId;
	        else
	        return i1.movieId - i2.movieId;
	    }
	    };
	    public static Comparator<Item> compMovieId = new Comparator<Item>() {
	    @Override
	    public int compare(Item i1, Item i2) {
	        if(i1.movieId != i2.movieId)
	            return i1.movieId - i2.movieId;
	        else
	            return i1.userId - i2.userId;
	    }
	    };
	    @Override
	    public String toString() {
	        return "[ " + userId + ", " + movieId + ", " + rating + " ]";
	    }
	    
		public int compareTo(Item i) {
			if(this.movieId != i.movieId){
				return this.movieId - i.movieId;
			}
			else
				if(this.movieId != i.movieId){
				 return this.movieId - i.movieId;
				}
				return this.userId - i.userId;
			
		}
		
	}

