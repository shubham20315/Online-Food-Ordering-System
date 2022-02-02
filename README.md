# Online Food Ordering System
The online food ordering system is a command-line app. It provides a platform to order food from nearby restaurants and track the order. 

# Features
1. Authentication
A new user can sign up and login after providing correct credentials. Since, authentication is there, it implies that data of all customers is saved and shown when required.

2. Order
A customer can order food from any nearby restaurant. Currently, the restaurants are limited to India only. To make life easy, a list of restaurants is shown to the customer along with estimated delivery time. Customer does not have to worry about finding restaurants that serve in his/her area. Moreover, the customer can add items to cart which is also saved at the backend and can be retrieved anytime the user wants logs in.

3. The customer can pay for the food online via credit/debit card, UPI or netbanking. As of now, these are the only payment methods allowed. The delivery charges are based on the distance between user's location and restaurant's location. There is a provision to apply promo code if the user wants to get discount. There are 2 valid promocodes in the system as of now. However, each promocode can be used only once.

4. Tracking
The customer has an option to track the order after it has been placed. If the estimated time exceeds the threshold of estimated time + 10% of estimated time, then the customer can cancel the order. Moreover, the user can rate the app and food. The ratings are also stored.

# How to set-up?
There is a `seed.sql` file that initializes tables like states, cities, pincodes. These tables have to be created and populated before the application can run smoothly. Remaining tables will be created automatically if not present already.
The list of restaurants and their menu needs to be populated in the database. This has to be done manually by executing queries. 

# Project Structure
The code has been implemented following the DAO design pattern. DAOs and models have been placed in different directories. DAOs implement CRUD operations and interact with the database. Models implement the businsess logic and one does not need to know what runs under the hood. The methods from models can be called directly in the `App` file which is the entry point of our program i.e. it contains the `main` method. 


# Database
The name of the database file is `food_ordering` and is placed in a separate directory named `databases`. The configuration of the database can be done from `Database` file in the `daos` directory.
