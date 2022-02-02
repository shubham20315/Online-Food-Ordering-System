package com.iiitd;

import com.iiitd.models.*;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class App {

    public static void main(String[] args) {

        /*Timestamp ts = Timestamp.valueOf("2021-12-01 20:41:44");

        ts.setMinutes(ts.getMinutes() + 25);

        System.out.println(ts.getMinutes());
        System.out.println(ts.getHours());
        System.out.println(ts);
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(ts));
        Timestamp current = new Timestamp(System.currentTimeMillis());

        System.out.println(current.getNanos() - ts.getNanos());

        Timestamp diff = new Timestamp((current.getTime() - ts.getTime()));
        System.out.println(diff);*/

        Scanner sc = new Scanner(System.in);
        String entryOptionChosen = "";
        Boolean goToRestaurants = false;

        while (!entryOptionChosen.equals("#")) {
            System.out.println("Select an option");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println();
            System.out.println("Press # to exit");

            entryOptionChosen = sc.nextLine();
            //System.out.println(entryOptionChosen);

            if (entryOptionChosen.equals("1")) {
                User user = new User();
                System.out.println("Enter first name");
                user.setFirstName(sc.nextLine());

                System.out.println("Enter last name");
                user.setLastName(sc.nextLine());

                System.out.println("Enter email");
                String email = sc.nextLine();
                while (!user.checkEmail(email)) {
                    System.out.println("That email is already registered. Please enter a different email ID.");
                    email = sc.nextLine();
                }
                user.setEmail(email);


                System.out.println("Enter password");
                user.setPassword(sc.nextLine());

                HashMap<Integer, String> statesMap = new HashMap<Integer, String>();
                int stateChosen = 0;
                int seq = 1;

                System.out.println("Choose state");
                List<State> states = new State().getAll();
                System.out.println(states.toArray().toString());
                if (states.isEmpty()) {
                    System.out.println("States could not be fetched");
                    exit(0);
                }

                for(State state : states){
                    System.out.println(seq + ".\t" + state.getName());
                    statesMap.put(seq, state.getName());
                    seq++;
                }

                Boolean stateValid = false;
                while (!stateValid) {
                    if (!sc.hasNextInt()) {
                        System.out.println("Please enter a numeric value");
                        sc.next();
                        continue;
                    }

                    stateChosen = sc.nextInt();
                    if (stateChosen < 1 || stateChosen > seq - 1) {
                        System.out.println("Please choose a valid state");
                        continue;
                    }
                    stateValid = true;

                }
                user.setState(statesMap.get(stateChosen));

                HashMap<Integer, String> citiesMap = new HashMap<Integer, String>();
                int cityChosen = 0;
                System.out.println("Choose city");
                City city = new City();
                ResultSet cities = city.getAll(statesMap.get(stateChosen));
                if (cities == null) {
                    System.out.println("Cities could not be fetched");
                    exit(0);
                }
                // loop through the result set
                seq = 1;
                try {
                    while (cities.next()) {
                        System.out.println(seq + ".\t" + cities.getString("name"));
                        citiesMap.put(seq, cities.getString("name"));
                        seq++;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                Boolean cityValid = false;
                while (!cityValid) {
                    if (!sc.hasNextInt()) {
                        System.out.println("Please enter a numeric value");
                        sc.next();
                        continue;
                    }

                    cityChosen = sc.nextInt();
                    if (cityChosen < 1 || cityChosen > seq - 1) {
                        System.out.println("Please choose a valid city");
                        continue;
                    }
                    cityValid = true;

                }
                //System.out.println("City: " + cityChosen);
                user.setCity(citiesMap.get(cityChosen));

                System.out.println("Enter pincode");
                sc.nextLine();
                String pincodeEntered = sc.nextLine();
                user.setPincode(pincodeEntered);
                //System.out.println("Pincode is " + user.getPincode());

                Pincode pincode = new Pincode();
                pincode.findLatitudeAndLongitude(pincodeEntered);
                if (pincode.getLatitude() == 0 && pincode.getLongitude() == 0) {
                    System.out.println("Pincode not recognizable");
                    exit(0);
                }
                user.setLatitude(pincode.getLatitude());
                user.setLongitude(pincode.getLongitude());

                System.out.println("Enter address");
                user.setAddress(sc.nextLine());

                System.out.println("Processing..");

                String msg = user.create();
                System.out.println(msg);


            } else if (entryOptionChosen.equals("2")) {
                User user = new User();
                String email, password;
                System.out.println("Enter email");
                email = sc.nextLine();
                System.out.println("Enter password");
                password = sc.nextLine();
                System.out.println("Processing..");

                if (user.authenticate(email, password)) {
                    System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
                    System.out.println();

                    String postLoginOptionChosen = "";
                    while (!postLoginOptionChosen.equals("#")) {
                        if (goToRestaurants) {
                            postLoginOptionChosen = "1";
                            goToRestaurants = false;
                        } else {
                            System.out.println("Select an option");
                            System.out.println("1. View restaurants");
                            System.out.println("2. Go to cart");
                            System.out.println("3. Track order");
                            System.out.println();
                            System.out.println("Press # to exit");

                            postLoginOptionChosen = sc.nextLine();
                        }

                        if (postLoginOptionChosen.equals("1")) {
                            Restaurant restaurant = new Restaurant();

                            String restaurantsOptionChosen = "";
                            while (!restaurantsOptionChosen.equals("*")) {
                                System.out.println("Select a restaurant");
                                List<Restaurant> restaurants = restaurant.show(user);
                                System.out.println();
                                System.out.println("Press * to go back");
                                System.out.println("Press # to exit");
                                restaurantsOptionChosen = sc.nextLine();

                                //while()
                                if (restaurantsOptionChosen.equals("*")) {
                                    // do nothing
                                } else if (restaurantsOptionChosen.equals("#")) {
                                    exit(0);
                                } else {
                                    String menuOptionChosen = "";
                                    while (!menuOptionChosen.equals("*")) {
                                        Menu menu = new Menu();
                                        List<Menu> menuItems = menu.show(restaurants.get(parseInt(restaurantsOptionChosen) - 1).getId());

                                        System.out.println();
                                        System.out.println("Press * to go back");
                                        System.out.println("Press # to exit");
                                        menuOptionChosen = sc.nextLine();


                                        if (menuOptionChosen.equals("*")) {
                                            // do nothing
                                        } else if (menuOptionChosen.equals("#")) {
                                            exit(0);
                                        } else {

                                            System.out.println("Enter quantity");

                                            System.out.println();
                                            System.out.println("Press * to go back");
                                            System.out.println("Press # to exit");

                                            String enteredQuantity = sc.nextLine();

                                            if (enteredQuantity.equals("*")) {
                                                // do nothing
                                            } else if (enteredQuantity.equals("#")) {
                                                exit(0);
                                            } else {

                                                Cart cart = new Cart(user.getId(), restaurants.get(parseInt(restaurantsOptionChosen) - 1).getId(), menuItems.get(parseInt(menuOptionChosen) - 1).getId(), parseInt(enteredQuantity));
                                                if (cart.addItem()) {
                                                    System.out.println("Item added to cart!");
                                                } else {
                                                    System.out.println("Item could not be added to cart!");
                                                }
                                            }


                                        }

                                    }

                                }

                            }


                        } else if (postLoginOptionChosen.equals("2")) {
//                          show cart
                            Cart cart = new Cart();
                            String cartOptionChosen = "";

                            while (!cartOptionChosen.equals("*")) {
                                List<Cart> cartItems = cart.show(user.getId());
                                if (!cartItems.isEmpty()) {
                                    System.out.println();
                                    System.out.println("Select an option");
                                    System.out.println("1. Add item");
                                    System.out.println("2. Delete item");
                                    System.out.println("3. Empty cart");
                                    System.out.println("4. Checkout");
                                    System.out.println();
                                    System.out.println("Press * to go back");
                                    System.out.println("Press # to exit");

                                    cartOptionChosen = sc.nextLine();

                                    if (cartOptionChosen.equals("*")) {
                                        // do nothing
                                    } else if (cartOptionChosen.equals("#")) {
                                        exit(0);
                                    } else {
                                        if (cartOptionChosen.equals("1")) {
                                            goToRestaurants = true;
                                            cartOptionChosen = "*";
                                        } else if (cartOptionChosen.equals("2")) {
                                            System.out.println("Enter item number");
                                            System.out.println();
                                            System.out.println("Press * to go back");
                                            System.out.println("Press # to exit");

                                            String itemOptionChosen = "";
                                            itemOptionChosen = sc.nextLine();

                                            cart.removeItem(cartItems.get(parseInt(itemOptionChosen) - 1).getId());

                                        } else if (cartOptionChosen.equals("3")) {
                                            cart.empty(user.getId());
                                            cartOptionChosen = "*";

                                        } else if (cartOptionChosen.equals("4")) {
                                            // payment
                                            Bill bill = new Bill();
                                            if (bill.getSubtotal(user.getId()) <= 100) {
                                                System.out.println("Cannot proceed to checkout");
                                                System.out.println("Minimum order value should be above Rs. 100");
                                            } else {
                                                Promotion promotion = new Promotion();

                                                String checkoutOptionChosen = "";
                                                while (!checkoutOptionChosen.equals("*")) {
                                                    System.out.println("Subtotal: " + bill.getSubtotal(user.getId()));
                                                    Restaurant restaurant = new Restaurant();
                                                    restaurant = restaurant.find(cartItems.get(0).getRestaurantId());
                                                    System.out.println("Delivery: " + bill.getDeliveryCharges(user, restaurant));
                                                    if (promotion.isValid()) {
                                                        System.out.println("Promo code applied: " + promotion.getPromoCode());
                                                    }
                                                    System.out.println("Total: " + bill.calculateBill(promotion));

                                                    System.out.println();

                                                    System.out.println("1. Apply promo code");
                                                    System.out.println("2. Proceed to payment");
                                                    System.out.println();
                                                    System.out.println("Press * to go back");
                                                    System.out.println("Press # to exit");

                                                    checkoutOptionChosen = sc.nextLine();
                                                    if (checkoutOptionChosen.equals("*")) {
                                                        // do nothing
                                                    } else if (checkoutOptionChosen.equals("#")) {
                                                        exit(0);
                                                    } else if (checkoutOptionChosen.equals("1")) {
                                                        String promoCode = "";

                                                        while (!promoCode.equals("*")) {
                                                            System.out.println("Enter promo code");
                                                            System.out.println();
                                                            System.out.println("Press * to go back");
                                                            System.out.println("Press # to exit");
                                                            promoCode = sc.nextLine();
                                                            if (promoCode.equals("*")) {
                                                                // do nothing
                                                            } else if (promoCode.equals("#")) {
                                                                exit(0);
                                                            } else {
                                                                if (promotion.checkPromoCode(user.getId(), promoCode)) {
                                                                    promotion.setValid(true);
                                                                    promoCode = "*";
                                                                }
                                                            }
                                                        }

                                                    } else if (checkoutOptionChosen.equals("2")) {
                                                        List<Cart> cartContent = cart.get(user.getId());

                                                        // proceed to payment
                                                        String paymentOptionChosen = "";
                                                        while (!paymentOptionChosen.equals("*")) {
                                                            System.out.println("Select an option");
                                                            System.out.println("1. UPI");
                                                            System.out.println("2. Netbanking");
                                                            System.out.println("3. Credit/Debit card");
                                                            System.out.println();
                                                            System.out.println("Press * to go back");
                                                            System.out.println("Press # to exit");

                                                            paymentOptionChosen = sc.nextLine();

                                                            if (paymentOptionChosen.equals("*")) {
                                                                // do nothing
                                                            } else if (paymentOptionChosen.equals("#")) {
                                                                exit(0);
                                                            } else if (paymentOptionChosen.equals("1")) {
                                                                System.out.println("Enter UPI ID");
                                                                String upiId = sc.nextLine();
                                                                UPI upi = new UPI();
                                                                upi.setUpiId(upiId);
                                                                upi.pay(user.getId(), cartContent.get(0).getRestaurantId(), bill.calculateBill(promotion), promotion.getValue());
                                                                if (upi.isPaymentSuccessful()) {
                                                                    System.out.println("Payment is successful!");
                                                                    System.out.println("Order placed successfully");
                                                                    paymentOptionChosen = "*";
                                                                    checkoutOptionChosen = "*";
                                                                    cartOptionChosen = "*";
                                                                    cart.empty(user.getId());
                                                                }

                                                            } else if (paymentOptionChosen.equals("2")) {
                                                                System.out.println("Enter username");
                                                                String netbankingUsername = sc.nextLine();
                                                                System.out.println("Enter password");
                                                                String netbankingPassword = sc.nextLine();

                                                                Netbanking netbanking = new Netbanking();
                                                                netbanking.setUsername(netbankingUsername);
                                                                netbanking.setPassword(netbankingPassword);
                                                                netbanking.pay(user.getId(), cart.getRestaurantId(), bill.calculateBill(promotion), promotion.getValue());
                                                                if (netbanking.isPaymentSuccessful()) {
                                                                    System.out.println("Payment is successful!");
                                                                    System.out.println("Order placed successfully");

                                                                }

                                                            } else if (paymentOptionChosen.equals("3")) {
                                                                System.out.println("Enter card no.");
                                                                String cardNo = sc.nextLine();
                                                                System.out.println("Enter expiry month");
                                                                String expiryMonth = sc.nextLine();
                                                                System.out.println("Enter expiry year");
                                                                String expiryYear = sc.nextLine();
                                                                System.out.println("Enter cvv");
                                                                String cvv = sc.nextLine();

                                                                Card card = new Card();
                                                                card.setNumber(cardNo);
                                                                card.setExpiryMonth(parseInt(expiryMonth));
                                                                card.setExpiryYear(parseInt(expiryYear));
                                                                card.setCvv(parseInt(cvv));
                                                                card.pay(user.getId(), cart.getRestaurantId(), bill.calculateBill(promotion), promotion.getValue());
                                                                if (card.isPaymentSuccessful()) {
                                                                    System.out.println("Payment is successful!");
                                                                    System.out.println("Order placed successfully");
                                                                }
                                                            } else {
                                                                System.out.println("Please select a valid option");
                                                            }
                                                        }

                                                    } else {
                                                        System.out.println("Please select a valid option");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    cartOptionChosen = "*";
                                }
                            }

                        } else if (postLoginOptionChosen.equals("3")) {
//                          track order
                            Track track = new Track();
                            Order order = new Order();
                            order = order.get(user.getId());
                            Restaurant restaurant = new Restaurant();
                            restaurant = restaurant.find(order.getRestaurantId());
                            track.calculateEstimatedTime(user, order, restaurant);
                            //System.out.println("You'll receive your order by " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(track.getEstimatedTime())));
                            if(track.getEstimatedTime() < 1){
                                System.out.println("You should have received your order");
                            } else{
                                System.out.println("You'll receive your order within " + track.getEstimatedTime() + " mins");
                            }
                            System.out.println("1. Mark order as received ");

                            if(track.hasTimeExceeded()){
                                System.out.println("2. Cancel ");
                            }

                            System.out.println();
                            System.out.println("Press * to go back");
                            System.out.println("Press # to exit");

                            String trackingOptionChosen = sc.nextLine();

                            while(!trackingOptionChosen.equals("*")){
                                if(trackingOptionChosen.equals("1")){
                                    System.out.println("Thank you for ordering from us!");
                                    System.out.println("Select an option");
                                    System.out.println("1. Rate our app and food?");
                                    System.out.println();
                                    System.out.println("Press # to exit");

                                    String ratingOptionChosen = sc.nextLine();
                                    if(ratingOptionChosen.equals("#")){
                                        exit(0);
                                    } else{
                                        System.out.println("Enter a rating for app between 1 and 5 (both inclusive)");
                                        String appRating = sc.nextLine();
                                        System.out.println("Enter a rating for food between 1 and 5 (both inclusive)");
                                        String foodRating = sc.nextLine();
                                        Rating rating = new Rating();
                                        rating.save(user.getId(), parseInt(appRating), parseInt(foodRating));
                                        System.out.println("Thank you for the ratings!");
                                        exit(0);

                                    }

                                } else if(trackingOptionChosen.equals("2")){
                                    if(order.cancel(user.getId())){
                                        System.out.println("Order has been cancelled successfully");
                                    } else{
                                        System.out.println("Order could not be cancelled");
                                    }
                                }
                            }

                        } else if (postLoginOptionChosen.equals("#")) {
                            exit(0);
                        } else {
                            System.out.println("Press select a valid option");
                        }

                    }

                } else {
                    System.out.println("Sorry, you are not registered with us!");
                }
            } else if (entryOptionChosen.equals("#")) {
                exit(0);
            } else {
                System.out.println("Please select a valid option");
            }
        }
    }
}
