# Marketplace System
Team: CHLW  
Members: Yichen Liu, Siyu Wu, Mengyuan Huang, Jiyao Chen
## 1. Documented API
   * ___User___
      * `GET /user`
        * Description: Return a full list of registered users
           * Sample HTTP Request: localhost:8080/user
      * `GET /user`
        * Description: Return a list of users that matches the given parameter
           * Optinal Parameters:
              * `user_id` (Type: Integer)
              * `email` (Type: String)
              * `username` (Type: String)
           * Sample HTTP Request: localhost:8080/users?user_id=1
           * Sample Response:
              ```json
              [
                  {
                      "user_id": 1,
                      "username": "sw3607",
                      "email": "sw3607@columbia.edu"
                  },
                  {
                      "user_id": 2,
                      "username": "cl2930",
                      "email": "cl2930@columbia.edu"
                  }
              ]
              ```
      * `POST /user`
        * Description: Create a new user with username and email. UserID will be generated automatically.
           * Sample Request Body:
           ```json
              
           ```
           * Sample Response: 
           ```json
              
           ```
      * `PUT /user`
        * Description: Update user information. Input must contain userID. Only username and email can be updated.
           * Sample Request Body:
           ```json
              
           ```
           * Sample Response:
           ```json
              
           ```
           
      * `DELETE /user` (coming soon)
   * ___Item___
      * `GET /item`
        * Description: Return a full list of items
             * Sample HTTP Request: localhost:8080/item
      * `GET /item`
        * Description: Return a list of items that matches the given parameter
           * Optinal Parameters:
              * `item_id` (Type: Integer)
              * `item_name` (Type: String)
              * `item_description` (Type: String)
              * `item_category` (Type: String)
           * Sample HTTP Request: localhost:8080/item?item_id=1
           * Sample Response:
              ```json
              
              ```
      * `POST /item`
      * `PUT /item`
      * `DELETE /item`
   * ___Transaction___
      * `GET /transaction`
      * `POST /transaction`
      * `PUT /transaction`
      * `DELETE /transaction`
## System description
This system provides an in-community market with negotiation/auction functionality. Clients can create users and modify the state of players, such as bags of items and amount of money (in-game scenario). Users can post their items to the community market at desired prices, and other users can buy the items or negotiate the price.
## Target Clients
Online market server (ebay, stockX.)  
Fundraising/auction web app/service  
Multiplayer game server  
Event ticket-selling service  
Any online platform or App with item trading and collection
## Build
Clone the whole project from Github, and build it in Intellij.  
The database for demo is not uploaded, but we can upload it later if needed. The url in src/main/resources/application.properties to database is a local address to the database, so the user need to modify it before using. The user also need to modify the username and password. Then the user can check if the connection to database is successfully set.  
## Run and test
### Test
Test files are under src/test/java/com/example/CommunityMarket/Flows. Tests can be done automatically.
### Run
We have finished basic functions related to Users, Transactions, and Items table. They can be run to perform insertion, updating, querying on these 3 tables.
## TODOs
We are working to fill other functions like validating user login. We will have them done before the demo.
