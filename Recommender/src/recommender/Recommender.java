package recommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Michael Chan, Danny Troung, Paul Lee, Andrew Bergeron, Ayush Gupta,
 *         Nick Marlowe CSC 2730 Programming Project #2 Professor Zhang
 *
 */
public class Recommender {
	public ArrayList<Item> dataByUser = new ArrayList<Item>();
	public ArrayList<Item> dataByMovie = new ArrayList<Item>();
	public ArrayList<Item> recommendList = new ArrayList<Item>();
	public HashMap ratingData = new HashMap(30000);
	public HashMap movieCorrelation = new HashMap(30000);
	public HashMap userCorrelation = new HashMap(30000);

	public Recommender(String dataFile, String UnknownRatingFile)
			throws FileNotFoundException {
		Scanner input = new Scanner(new File(dataFile));
		while (input.hasNext()) {

			int userId = input.nextInt();
			int movieId = input.nextInt();
			double rating = input.nextDouble();
			Item i = new Item(userId, movieId, rating);
			dataByUser.add(i);
			dataByMovie.add(i);
			ratingData.put(i.userId + "#" + i.movieId, i.rating);
			input.nextLine();
		}

		Collections.sort(dataByUser, Item.compUserId);
		Collections.sort(dataByMovie, Item.compMovieId);

		input = new Scanner(new File(UnknownRatingFile));
		while (input.hasNext()) {
			int userId = input.nextInt();
			int movieId = input.nextInt();
			recommendList.add(new Item(userId, movieId));
		}
	}

	public ArrayList<Integer> userOfMovie(int movieId) {
		int index, leftIndex, rightIndex;
		ArrayList<Integer> users = new ArrayList<Integer>();
		index = binSearchMovie(dataByMovie, movieId, 0, dataByMovie.size() - 1);

		if (index == -1)
			return users;
		leftIndex = rightIndex = index;
		do {
			leftIndex--;
		} while (leftIndex >= 0
				&& dataByMovie.get(leftIndex).movieId == movieId);
		leftIndex++;
		do {
			rightIndex++;
		} while (rightIndex <= dataByMovie.size() - 1
				&& dataByMovie.get(rightIndex).movieId == movieId);
		rightIndex--;
		for (int i = leftIndex; i <= rightIndex; i++) {
			users.add(dataByMovie.get(i).userId);
		}
		return users;
	}

	public ArrayList<Integer> moviesOfUser(int userId) {
		int index, leftIndex, rightIndex;
		ArrayList<Integer> movies = new ArrayList<Integer>();
		index = binSearchUser(dataByUser, userId, 0, dataByUser.size() - 1);
		leftIndex = index;
		rightIndex = index;
		do {
			leftIndex--;
		} while (leftIndex >= 0 && dataByUser.get(leftIndex).userId == userId);
		leftIndex++;
		do {
			rightIndex++;
		} while (rightIndex <= dataByUser.size() - 1
				&& dataByUser.get(rightIndex).userId == userId);
		rightIndex--;
		for (int i = leftIndex; i <= rightIndex; i++) {
			movies.add(dataByUser.get(i).movieId);
		}
		return movies;
	}

	public void calculateCorrelation(int smallMovieId, int bigMovieId) {
		double avgDifference = 0;
		double difference = 0;
		ArrayList<Integer> commonUser = commonUserOfMovie(smallMovieId,
				bigMovieId);

		if (commonUser.size() > 0) {
			for (int i = 0; i < commonUser.size(); i++)
				difference += (double) ratingData.get(commonUser.get(i) + "#"
						+ bigMovieId)
						- (double) ratingData.get(commonUser.get(i) + "#"
								+ smallMovieId);
			avgDifference = difference / commonUser.size();
			movieCorrelation.put(smallMovieId + "#" + bigMovieId + "#0",
					avgDifference);
		} else
			movieCorrelation.put(smallMovieId + "#" + bigMovieId + "#0", null);
	}

	public void slope() {
		int userId, movieId, smMovieId, bigMovieId, previousUserId, n;
		double predictedRating;
		ArrayList<Integer> movies = new ArrayList<Integer>();
		previousUserId = -1;

		for (int i = 0; i < recommendList.size(); i++) {

			n = 0;
			predictedRating = 0;
			userId = recommendList.get(i).userId;
			movieId = recommendList.get(i).movieId;

			if (previousUserId != userId)
				movies = moviesOfUser(userId);

			for (int j = 0; j < movies.size(); j++) {
				if (movies.get(j) < movieId) {
					smMovieId = movies.get(j);
					bigMovieId = movieId;
				} else if (movies.get(j) > movieId) {
					smMovieId = movieId;
					bigMovieId = movies.get(j);
				} else
					throw new IllegalArgumentException(
							"A user has 2 ratings on the same movie");

				if (!movieCorrelation.containsKey(smMovieId + "#" + bigMovieId
						+ "#0"))
					calculateCorrelation(smMovieId, bigMovieId);

				if (movieCorrelation.get(smMovieId + "#" + bigMovieId + "#0") != null) {
					if (movieId == bigMovieId) {
						predictedRating += (double) ratingData.get(userId + "#"
								+ movies.get(j))
								+ (double) movieCorrelation.get(smMovieId + "#"
										+ bigMovieId + "#0");
						n++;
					} else {
						predictedRating += (double) ratingData.get(userId + "#"
								+ movies.get(j))
								- (double) movieCorrelation.get(smMovieId + "#"
										+ bigMovieId + "#0");
						n++;
					}
				}
			}
			if (n != 0)
				recommendList.get(i).rating = predictedRating / n;
			else {
				double sumRating = 0;
				for (int k = 0; k < movies.size(); k++)
					sumRating += (double) ratingData.get(userId + "#"
							+ movies.get(k));
				recommendList.get(i).rating = sumRating / movies.size();
			}
			previousUserId = userId;
		}
	}

	public void calculateCorrelationAdjusted(int smMovieId, int bigMovieId,
			int userId) {
		double diff = 0;
		double sumCorrelation = 0;
		double avgDiff = 0;
		ArrayList<Integer> commonUser = commonUserOfMovie(smMovieId, bigMovieId);

		if (commonUser.size() > 0) {
			for (int i = 0; i < commonUser.size(); i++) {
				int smUserId, bigUserId;
				if (userId < commonUser.get(i)) {
					smUserId = userId;
					bigUserId = commonUser.get(i);
				} else {
					smUserId = commonUser.get(i);
					bigUserId = userId;
				}
				diff += ((double) ratingData.get(commonUser.get(i) + "#"
						+ bigMovieId) - (double) ratingData.get(commonUser
						.get(i) + "#" + smMovieId))
						* (double) userCorrelation.get(smUserId + "#"
								+ bigUserId);

				sumCorrelation += (double) userCorrelation.get(smUserId + "#"
						+ bigUserId);
			}
			avgDiff = Math.round(diff / sumCorrelation);

			movieCorrelation.put(smMovieId + "#" + bigMovieId + "#" + userId,
					avgDiff);
		} else
			movieCorrelation.put(smMovieId + "#" + bigMovieId + "#" + userId,
					null);
	}

	public void slopeAdjusted() {
		int userId, movieId, smMovieId, bigMovieId, previousUserId, n;
		double predictionRating;
		ArrayList<Integer> movies = new ArrayList<Integer>();
		previousUserId = -1;
		for (int i = 0; i < recommendList.size(); i++) {

			n = 0;
			predictionRating = 0;
			userId = recommendList.get(i).userId;
			movieId = recommendList.get(i).movieId;
			if (previousUserId != userId)
				movies = moviesOfUser(userId);
			for (int j = 0; j < movies.size(); j++) {
				if (movies.get(j) < movieId) {
					smMovieId = movies.get(j);
					bigMovieId = movieId;
				} else if (movies.get(j) > movieId) {
					smMovieId = movieId;
					bigMovieId = movies.get(j);
				} else
					throw new IllegalArgumentException(
							"One user have 2 ratings on same movie");

				calculateCorrelationAdjusted(smMovieId, bigMovieId, userId);
				if (movieCorrelation.get(smMovieId + "#" + bigMovieId + "#"
						+ userId) != null) {
					if (movieId == bigMovieId) {
						predictionRating += (double) ratingData.get(userId
								+ "#" + movies.get(j))
								+ (double) movieCorrelation.get(smMovieId + "#"
										+ bigMovieId + "#" + userId);
						n++;
					} else {
						predictionRating += (double) ratingData.get(userId
								+ "#" + movies.get(j))
								- (double) movieCorrelation.get(smMovieId + "#"
										+ bigMovieId + "#" + userId);
						n++;
					}
				}
			}
			if (n != 0)
				recommendList.get(i).rating = predictionRating / n;
			else {
				double sumRating = 0;
				for (int k = 0; k < movies.size(); k++)
					sumRating += (double) ratingData.get(userId + "#"
							+ movies.get(k));
				recommendList.get(i).rating = sumRating / movies.size();
			}
			previousUserId = userId;
		}
	}

	public ArrayList<Integer> commonUserOfMovie(int movie1, int movie2) {
		int index1, index2;
		ArrayList<Integer> users1, users2, commonUser;
		users1 = userOfMovie(movie1);
		users2 = userOfMovie(movie2);
		commonUser = new ArrayList<Integer>();
		index1 = 0;
		index2 = 0;

		while (index1 < users1.size() && index2 < users2.size()) {
			if (users1.get(index1) < users2.get(index2))
				index1++;
			else if (users1.get(index1) > users2.get(index2))
				index2++;
			else {
				commonUser.add(users1.get(index1));
				index1++;
				index2++;
			}
		}
		return commonUser;
	}

	public int binSearchUser(ArrayList<Item> arraylist, int keyVal, int i, int j) {
		if (i > j)
			return -1;
		int k = (i + j) / 2;
		if (arraylist.get(k).userId > keyVal)
			return binSearchUser(arraylist, keyVal, i, k - 1);
		if (arraylist.get(k).userId < keyVal)
			return binSearchUser(arraylist, keyVal, k + 1, j);
		return k;
	}

	public int binSearchMovie(ArrayList<Item> arraylist, int keyVal, int i,
			int j) {
		if (i > j)
			return -1;
		int k = (i + j) / 2;
		if (arraylist.get(k).movieId > keyVal)
			return binSearchMovie(arraylist, keyVal, i, k - 1);
		if (arraylist.get(k).movieId < keyVal)
			return binSearchMovie(arraylist, keyVal, k + 1, j);
		return k;
	}

	public void getUserInfo(String userInfoFile) throws FileNotFoundException {
		int userId, age, gender, zipcode;
		String occupation;
		final int NOVAL = -99999;
		final int AGEDIFF_1 = 5;
		final double AGEDIFF_1_COEFF = 0.3;
		final int AGEDIFF_2 = 10;
		final double AGEDIFF_2_COEFF = 0.2;
		final double GENDERMATCH = 0.5;
		final double OCCUPATIONMATCH = 0.5;
		final int ZIPCODEDIFF = 500;
		final double ZIPCODEDIFF_CONEFF = 0.5;

		ArrayList<User> userData = new ArrayList<User>();
		Scanner in = new Scanner(new File(userInfoFile));

		while (in.hasNextLine()) {
			String line = in.nextLine();
			int index[] = new int[4];
			int j = 0;
			for (int i = 0; i < line.length(); i++)
				if (line.substring(i, i + 1).compareTo("|") == 0)
					index[j++] = i;
			try {
				userId = Integer.parseInt(line.substring(0, index[0]));
			} catch (NumberFormatException e) {
				System.out.println("wrong userId");
				userId = NOVAL;
			}
			try {
				age = Integer.parseInt(line.substring(index[0] + 1, index[1]));
			} catch (NumberFormatException e) {
				System.out.println("wrong age");
				age = NOVAL;
			}
			if (line.substring(index[1] + 1, index[2]).compareTo("M") == 0)
				gender = 1;
			else
				gender = 0;
			occupation = line.substring(index[2] + 1, index[3]);
			try {
				zipcode = Integer.parseInt(line.substring(index[3] + 1,
						line.length()));
			} catch (NumberFormatException e) {
				zipcode = NOVAL;
			}
			userData.add(new User(userId, age, gender, zipcode, occupation));
		}
		Collections.sort(userData);
		for (int i = 0; i < userData.size() - 1; i++) {
			for (int j = i + 1; j < userData.size(); j++) {
				double correlation = 1;
				if (Math.abs(userData.get(i).age - userData.get(j).age) <= AGEDIFF_1)
					correlation += AGEDIFF_1_COEFF;
				if (Math.abs(userData.get(i).age - userData.get(j).age) <= AGEDIFF_2)
					correlation += AGEDIFF_2_COEFF;
				if (userData.get(i).gender == userData.get(j).gender)
					correlation += GENDERMATCH;
				if (userData.get(i).occupation
						.compareTo(userData.get(j).occupation) == 0)
					correlation += OCCUPATIONMATCH;
				if (Math.abs(userData.get(i).zipcode - userData.get(j).zipcode) <= ZIPCODEDIFF
						&& userData.get(i).zipcode != NOVAL)
					correlation += ZIPCODEDIFF_CONEFF;
				userCorrelation.put(
						userData.get(i).userId + "#" + userData.get(j).userId,
						correlation);
			}
		}
	}

	public static void makeUnknownRating(String testFileName,
			String unknownRatingFileName) throws FileNotFoundException {
		ArrayList<Item> array = new ArrayList<Item>();
		Scanner in = new Scanner(new File(testFileName));
		while (in.hasNext()) {
			int userId = in.nextInt();
			int movieId = in.nextInt();
			array.add(new Item(userId, movieId));
			in.nextLine();
		}
		PrintWriter pw = new PrintWriter(unknownRatingFileName);
		pw.printf("%d\t%d", array.get(0).userId, array.get(0).movieId);
		for (int i = 1; i < array.size(); i++) {
			pw.println();
			pw.printf("%d\t%d", array.get(i).userId, array.get(i).movieId);
		}
		pw.close();
	}

	public void makeOutputFile(String fileName) throws FileNotFoundException {
		PrintWriter fout = new PrintWriter(fileName);
		fout.printf("%d\t%d\t%f", recommendList.get(0).userId,
				recommendList.get(0).movieId, recommendList.get(0).rating);
		for (int i = 1; i < recommendList.size(); i++) {
			fout.println();
			fout.printf("%d\t%d\t%f", recommendList.get(i).userId,
					recommendList.get(i).movieId, recommendList.get(i).rating);
		}
		fout.close();
	}

	public static double[] measurePerformance(String testFileName,
			String predictionFileName) throws FileNotFoundException {
		double[] measurement = new double[2];
		ArrayList<Item> test = new ArrayList<Item>();
		ArrayList<Item> prediction = new ArrayList<Item>();
		Scanner input = new Scanner(new File(testFileName));

		while (input.hasNext()) {
			int userId = input.nextInt();
			int movieId = input.nextInt();
			double rating = input.nextDouble();
			test.add(new Item(userId, movieId, rating));
			input.nextLine();
		}
		input = new Scanner(new File(predictionFileName));
		while (input.hasNext()) {
			int userId = input.nextInt();
			int movieId = input.nextInt();
			double rating = input.nextDouble();
			prediction.add(new Item(userId, movieId, rating));
		}
		int n = 0, flip = 0;
		double mse, mfc, sumSquare = 0;
		if (test.size() != prediction.size())
			System.out.println("test file and prediction file are not match");
		for (int i = 0; i < test.size(); i++) {
			if (test.get(i).userId != prediction.get(i).userId
					|| test.get(i).movieId != prediction.get(i).movieId)
				System.out
						.println("test file and prediction file are not match");
			sumSquare += Math.pow(
					(test.get(i).rating - prediction.get(i).rating), 2);
			if (Math.abs(test.get(i).rating - prediction.get(i).rating) >= 2)
				flip++;
		}
		mse = sumSquare / test.size();
		mfc = 1.0 * flip / test.size();
		System.out.println("Test file used: -----" + testFileName
				+ "  || prediction filename: ----- " + predictionFileName);
		System.out.println();
		System.out.printf("(MSE) is %f\n", mse);
		System.out.printf("(MFC) is %f\n", mfc);
		System.out.println();
		System.out.println("******************************");
		measurement[0] = mse;
		measurement[1] = mfc;
		return measurement;
	}

	public static void main(String[] args) throws FileNotFoundException {
		double m1[] = new double[2], sumMse1 = 0, sumMfc1 = 0;
		double m2[] = new double[2], sumMse2 = 0, sumMfc2 = 0;
		int n1 = 0, n2 = 0;

		makeUnknownRating("u1.test", "u1.test.UnknownRating");
		Recommender u1 = new Recommender("u1.base", "u1.test.UnknownRating");
		u1.slope();
		u1.makeOutputFile("u1.test.Prediction");
		m1 = measurePerformance("u1.test", "u1.test.Prediction");
		sumMse1 += m1[0];
		sumMfc1 += m1[1];
		n1++;
		u1.getUserInfo("u.user");
		u1.slopeAdjusted();
		u1.makeOutputFile("u1.test.Prediction2");
		m2 = measurePerformance("u1.test", "u1.test.Prediction2");
		sumMse2 += m2[0];
		sumMfc2 += m2[1];
		n2++;
		u1 = null;
		System.gc();

		makeUnknownRating("u2.test", "u2.test.UnknownRating");
		Recommender u2 = new Recommender("u2.base", "u2.test.UnknownRating");
		u2.slope();
		u2.makeOutputFile("u2.test.Prediction");
		m1 = measurePerformance("u2.test", "u2.test.Prediction");
		sumMse1 += m1[0];
		sumMfc1 += m1[1];
		n1++;
		u2.getUserInfo("u.user");
		u2.slopeAdjusted();
		u2.makeOutputFile("u2.test.Prediction2");
		m2 = measurePerformance("u2.test", "u2.test.Prediction2");
		sumMse2 += m2[0];
		sumMfc2 += m2[1];
		n2++;
		u2 = null;
		System.gc();

		makeUnknownRating("u3.test", "u3.test.UnknownRating");
		Recommender u3 = new Recommender("u3.base", "u3.test.UnknownRating");
		u3.slope();
		u3.makeOutputFile("u3.test.Prediction");
		m1 = measurePerformance("u3.test", "u3.test.Prediction");
		sumMse1 += m1[0];
		sumMfc1 += m1[1];
		n1++;
		u3.getUserInfo("u.user");
		u3.slopeAdjusted();
		u3.makeOutputFile("u3.test.Prediction2");
		m2 = measurePerformance("u3.test", "u3.test.Prediction2");
		sumMse2 += m2[0];
		sumMfc2 += m2[1];
		n2++;
		u3 = null;
		System.gc();

		makeUnknownRating("u4.test", "u4.test.UnknownRating");
		Recommender u4 = new Recommender("u4.base", "u4.test.UnknownRating");
		u4.slope();
		u4.makeOutputFile("u4.test.Prediction");
		m1 = measurePerformance("u4.test", "u4.test.Prediction");
		sumMse1 += m1[0];
		sumMfc1 += m1[1];
		n1++;
		u4.getUserInfo("u.user");
		u4.slopeAdjusted();
		u4.makeOutputFile("u4.test.Prediction2");
		m2 = measurePerformance("u4.test", "u4.test.Prediction2");
		sumMse2 += m2[0];
		sumMfc2 += m2[1];
		n2++;
		u4 = null;
		System.gc();

		makeUnknownRating("u5.test", "u5.test.UnknownRating");
		Recommender u5 = new Recommender("u5.base", "u5.test.UnknownRating");
		u5.slope();
		u5.makeOutputFile("u5.test.Prediction");
		m1 = measurePerformance("u5.test", "u5.test.Prediction");
		sumMse1 += m1[0];
		sumMfc1 += m1[1];
		n1++;
		u5.getUserInfo("u.user");
		u5.slopeAdjusted();
		u5.makeOutputFile("u5.test.Prediction2");
		m2 = measurePerformance("u5.test", "u5.test.Prediction2");
		sumMse2 += m2[0];
		sumMfc2 += m2[1];
		n2++;

		System.out.println();
		System.out.println("Slope One Method:");
		System.out.printf("(MSE) is %f\n", sumMse1 / n1);
		System.out.printf("(MFC) is %f\n", sumMfc1 / n1);
		System.out.println();
		System.out.println("Slope One Adjusted:");
		System.out.printf("(MSE) is %f\n", sumMse2 / n2);
		System.out.printf("(MFC) is %f\n", sumMfc2 / n2);
	}

}
