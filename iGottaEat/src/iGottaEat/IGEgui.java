package iGottaEat;

import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class IGEgui extends JFrame implements ActionListener {
  private JPanel start, randomizer;
  private JTextField locationField;
  private JLabel instructL;
  private JLabel welcomeL;
  private JLabel restaurantL;
  private JButton locationButton;
  private JButton randomButton;

  public IGEgui() {
    start = new JPanel();
    randomizer = new JPanel();

    randomButton = new JButton("Randomize");
    locationButton = new JButton("Randomize");
    locationField = new JTextField(10);
    welcomeL = new JLabel();
    instructL = new JLabel();
    restaurantL = new JLabel();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Random Restaurant Generator");
    setSize(300,150);
    
    locationButton.addActionListener(this);
    randomButton.addActionListener(this);

    welcomeL.setText("Welcome to the Random Restaurant Generator!");
    instructL.setText("Please enter your location (Zip code/City, State)");
    restaurantL.setText("");
    
    start.add(welcomeL);
    start.add(instructL);
    start.add(locationField);
    start.add(locationButton);
    randomizer.add(restaurantL);
    randomizer.add(randomButton);
    
    setContentPane(start);
  }

  public void actionPerformed(ActionEvent e) {
    JButton b = (JButton) e.getSource();

    if (b == locationButton) {
      yelpHelp.yelpRestaurants(locationField.getText());
      remove(start);
      setContentPane(randomizer);
      restaurantL.setText(yelpHelp.randomPlace());
    } else if (b == randomButton) {
      restaurantL.setText(yelpHelp.randomPlace());
    }
  }

  public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new IGEgui().setVisible(true);
      }
    });
  }
}

class yelpHelp {
  private static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
  private static int i = 0;

  private static final String CONSUMER_KEY = "NVaDEK37pXM5ZGRfnN3NRg";
  private static final String CONSUMER_SECRET = "RgH-3tzg5URWf-hK8G3NA8anG-s";
  private static final String TOKEN = "V2CM0wuGT55RQ-QNzRzpLsKNZElNMThW";
  private static final String TOKEN_SECRET = "ddiuTnligH82cESsnS_TNi6xHh8";

  private static YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET,
      TOKEN, TOKEN_SECRET);

  public static void yelpRestaurants(String userLocation) {
    restaurants = yelpApi.queryAPI(yelpApi, userLocation);
  }

  // Produces random place
  public static String randomPlace() {
    if (i == 0)
      restaurants = shuffle(restaurants); // shuffles places like a card deck
    Restaurant randomRestaurant = restaurants.get(i);
    i++;
    // Resets counter once all options are used
    if (i == restaurants.size()) {
      i = 0;
    }
    
    return randomRestaurant.getName();
  }

  // Shuffles Arraylist like a deck of cards
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
