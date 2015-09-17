package iGottaEat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class IGEDriver {

	ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>(); 
	public static ArrayList<User> users = new ArrayList<User>();
	
	private static final String CONSUMER_KEY = "NVaDEK37pXM5ZGRfnN3NRg";
	private static final String CONSUMER_SECRET = "RgH-3tzg5URWf-hK8G3NA8anG-s";
	private static final String TOKEN = "V2CM0wuGT55RQ-QNzRzpLsKNZElNMThW";
	private static final String TOKEN_SECRET = "ddiuTnligH82cESsnS_TNi6xHh8";

	public static void main(String[] args) {
		String userLocation;
		ArrayList<Restaurant> restaurants = null;
		int option = -1;
		Scanner sc = new Scanner(System.in);
		YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
		System.out.println("Welcome to the Random Food Generator Playlist.");
		
		System.out.println("Here are your options: ");
		System.out.println("1 - Create account.");
		System.out.println("2 - Login.");
		System.out.println("3 - Generate a random restaurant");
		System.out.println("4 - View most popular restaurants");
		System.out.println("What do you want to do? Type in corresponding number: ");
		option = sc.nextInt();
		
		if(option == 1){
			System.out.println("Thank you for using iGottaEat");
			System.out.println("Please enter in your email address: ");
			String tempEmail, tempPassword, tempLast, tempFirst;
			sc.nextLine();
			tempEmail= sc.nextLine();
			System.out.println("Your email address is: " + tempEmail);
			System.out.println("Please enter in your password: ");
			tempPassword = sc.nextLine();
			
			
			/*Do not use parser to split/chomp the two, just have as separate params*/
			System.out.println("What is your first name?");
			tempFirst = sc.nextLine();
			System.out.println("What is your last name?");
			tempLast = sc.nextLine();
			
			
			/*Create new user*/
			User temp = new User(tempEmail, tempPassword, tempLast, tempFirst);
			users.add(temp);
		} else if (option == 2){
			System.out.println("Not available to the public yet.");
		} else if (option == 3){
			System.out.println("Please enter your location (Zip code/City, State) :");
			userLocation = sc.next();
			restaurants = yelpApi.queryAPI(yelpApi, userLocation);
			randomPlace(restaurants);
		} else if (option == 4){
			System.out.println("To be implemented.");
		}
		
		
<<<<<<< HEAD
=======
		System.out.println("Please enter your location:");
		userLocation = sc.nextLine();
		restaurants = yelpApi.queryAPI(yelpApi, userLocation);
		randomPlace(restaurants);
>>>>>>> origin/master
	}


	// Produces random place. User can see next option by typing any input
	private static void randomPlace(ArrayList<Restaurant> places) {
		Scanner sc = new Scanner(System.in);
		String ans = "";
		int i = 0; // counter to iterate through arraylist, reset to 0 when reshuffled

		places = shuffle(places); // shuffles places like a card deck

		// Continues to provide user with "random" places to eat until "n" is typed
		do {
			Restaurant randomRestaurant = places.get(i);
			System.out.println("Go eat at " + randomRestaurant.getName());
			System.out.println("Rating: " + randomRestaurant.getRating().toString());
			System.out.println("Phone: " + randomRestaurant.getNumber());
			System.out.println("Address: " + randomRestaurant.getAddress());
			System.out.println(randomRestaurant.getURL());
			System.out.println("Don't like it? Enter any key to get another place or type n to quit.");
			ans = sc.next();
			i++;

			// reshuffles arraylist once all options are used
			if (i == places.size()) {
				places = shuffle(places);
				i = 0;
			}
		} while (!ans.equals("n"));
		sc.close();
	}

	// Shuffles arraylist like a deck of cards
	private static ArrayList<Restaurant> shuffle(ArrayList<Restaurant> places) {
		int j = 0;
		Restaurant temp = null;

		// Randomly swaps one index with another
		for (int i = 0; i < places.size(); i++) {
			j = ThreadLocalRandom.current().nextInt(0, i + 1);
			temp = places.get(j);
			places.set(j, places.get(i));
			places.set(i, temp);
		}
		return places;
	}

}
