package recommender;

import java.util.Comparator;
/**
 * 
 * @author Michael Chan, Danny Troung, Paul Lee, Andrew Bergeron, Ayush Gupta, Nick Marlowe
 *CSC 3102
 *Programming Project #2
 *Professor Zhang
 */
 public class User implements Comparable<User> {
    // for gender F=0, M=1;
    public int userId, age, gender, zipcode;
    public String occupation;
    
    public User(int userId, int age, int gender, int zipcode, String occupation) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.zipcode = zipcode;
        this.occupation = occupation;
    }
    
    public static Comparator<User> comparator = new Comparator<User>() {
   
    @Override
    public int compare(User u1, User u2) {
        return u1.userId - u2.userId;
    	}
    };
    
    @Override
    public String toString() {
        return "[ " + userId + ", " + age + ", " + gender + ", " + zipcode + ", " + occupation + " ]";
    }

	public int compareTo(User u1) {
		
		return this.userId - u1.userId;
	}

	
}