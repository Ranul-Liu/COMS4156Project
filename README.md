# Marketplace System
Team: CHLW  
Members: Yichen Liu, Siyu Wu, Mengyuan Huang, Jiyao Chen
## 1. Documented API
   * ___Player___
      * `GET /player`
        * Description: Return a full list of registered players
           * Sample HTTP Request: localhost:8080/player
      * `GET /player`
        * Description: Return a list of users that matches the given parameter
           * Optinal Parameters:
              * `player_id` (Type: Integer)
              * `email` (Type: String)
              * `playername` (Type: String)
           * Sample HTTP Request:  
           `localhost:8080/player?player_id=1`
           * Sample Response:  
              `Status: 200`
              ```json
              [
                  {
                      "player_id": 1,
                      "playername": "sw3607",
                      "email": "sw3607@columbia.edu"
                  }
              ]
              ```
      * `POST /player`
        * Description: Create a new player with username and email. UserID will be generated automatically.
           * Sample Request Body:
           ```json
           {
              "playername": "sw3607",
              "email": "sw3607@columbia.edu"
           }
           ```
           * Sample Response:  
           `status: 201`
           ```json
           [
               {
                   "player_id": 1,
                   "playername": "sw3607",
                   "email": "sw3607@columbia.edu"
               }
           ]
           ```
      * `PUT /player`
        * Description: Update player information. Input must contain playerID. Only playername and email can be updated.
           * Sample Request Body:
           ```json
           {
               "player_id": 1,
               "playername":"newname@columbia.edu",
               "email":"newemail@columbia.edu"
           }
           ```
           * Sample Response:  
           `status: 200`
           ```json
           [
               {
                   "player_id": 1,
                   "playername": "newname@columbia.edu",
                   "email": "newemail@columbia.edu"
               }
           ]
           ```

      * `DELETE /player` (coming soon)
   * ___Item___
      * `GET /item`
        * Description: Return a full list of items
             * Sample HTTP Request:  
             `localhost:8080/item`
      * `GET /item`
        * Description: Return a list of items that matches the given parameters
           * Optional Parameters:
              * `item_id` (Type: Integer)
              * `item_name` (Type: String)
              * `item_description` (Type: String)
              * `item_category` (Type: String)
           * Sample HTTP Request:  
           `localhost:8080/item?item_id=1`
           * Sample Response:  
              `Status: 200`
              ```json
              [
                {
                  "item_id": 1,
                  "item_name":"shield",
                  "item_description":"a stone shield",
                  "item_category":"defence"
                }
              ]
              ```
      * `POST /item`  
        * Description: Add a new item to the database.
          * Sample Request Body:
          ```json
          {
              "item_name":"shield",
              "item_description":"a stone shield",
              "item_category":"defense"
          }
          ```
          * Sample Response:  
             `Status: 201`
             ```json
             [
               {
                 "item_id": 1,
                 "item_name":"shield",
                 "item_description":"a stone shield",
                 "item_category":"defence"
               }
             ]
             ```

      * `PUT /item`
        * Description: Update an existing item in the database.
          * Sample Request Body:  
          ```json
          {
              "item_id": 1,
              "item_name":"shield",
              "item_description":"a stone shield",
              "item_category":"defence"
          }
          ```
          * Sample Response:  
             `Status: 200`
             ```json
             [
               {
                 "item_id": 1,
                 "item_name":"shield",
                 "item_description":"a stone shield",
                 "item_category":"defence"
               }
             ]
             ```

      * `DELETE /item` (coming soon)
   * ___Transaction___
      * `GET /transaction`
        * Description: Return a full list of transactions
          * Sample HTTP Request:
          `localhost:8080/transaction`
      * `GET /transaction`
        * Description: Return a list of transactions that matches the given parameters
          * Optional parameters:
            * `transaction_id` (Type: Integer)
            * `item_id` (Type: Integer)
            * `buyer_id` (Type: Integer)
            * `seller_id` (Type: Integer)
            * `quantity` (Type: Integer)
            * `open` (Type: Boolean)
            * `post_time` (Type: LocalDateTime)
            * `close_time` (Type: LocalDateTime)
            * `price` (Type: Integer)
            * `accept` (Type: Boolean)
          * Sample HTTP Request:  
          `localhost:8080/transaction?transaction_id=1`
          * Sample Response:  
          `Status: 200`
          ```json
          [
              {
                "transaction_id": 1,
                "item_id": 1,
                "buyer_id": 2,
                "seller_id": 1,
                "quantity": 1,
                "open": true,
                "post_time": "2022-12-04-17-22-53",
                "close_time": null,
                "price": 100,
                "accept": false
              }
          ]
          ```
      * `POST /transaction`
        * Description: Add a new transaction to the database.
          * Sample Request Body:
          ```json
          {
              "seller_id":"1",
              "item_id" : "1",
              "price" : "100",
              "quantity" : "1",
              "accept" : false,
              "open" : true
          }
          ```
          * Sample Response:  
          `Status: 201`
          ```json
          [
              {
                "transaction_id": 1,
                "item_id": 1,
                "buyer_id": null,
                "seller_id": 1,
                "quantity": 1,
                "open": true,
                "post_time": "2022-12-04-17-22-53",
                "close_time": null,
                "price": 100,
                "accept": false
              }
          ]
          ```
      * `PUT /transaction/{seller_id}/{transaction_id}`
        * Description: Update a given transaction.
          * Sample Request Body:  
          ```json
          {
              "initial_price" : "3",
              "quantity" : "3"
            }
            ```
            * Sample Response:
            `Status: 200`
            ```json
            [
                {
                  "transaction_id": 1,
                  "item_id": 1,
                  "buyer_id": null,
                  "seller_id": 1,
                  "quantity": 3,
                  "open": true,
                  "post_time": "2022-12-04-17-22-53",
                  "close_time": null,
                  "price": 3,
                  "accept": false
                }
            ]
            ```
      * `PUT /transaction/close/{seller_id}/{transaction_id}`
        * Description: Close the given transaction.
          * Sample Response:
          `Status: 200`
          ```json
          [
              {
                "transaction_id": 1,
                "item_id": 1,
                "buyer_id": null,
                "seller_id": 1,
                "quantity": 3,
                "open": false,
                "post_time": "2022-12-04-17-22-53",
                "close_time": "2022-12-04-17-22-54",
                "price": 3,
                "accept": false
              }
          ]
          ```
      * `DELETE /transaction` (coming soon)

   * ___Negotiation___
      * `Get /view-negotiation`
        * Description: Return a list of negotiations according to the transaction_id.
          * Sample HTTP Request: `localhost:8080/view-negotiation?fk_transaction_id=1`
          * Sample Response:  
          `Status: 200`  
          ```json
          [
              {
                "negotiation_id": 1,
                "buyer_id": 2,
                "post_time": "2022-12-04-17-22-53",
                "close_time": null,
                "price": 3,
                "quantity": 5,
                "open": true,
                "accept": null,
                "transaction": {"transaction_id":1}
              }
          ]
          ```
      * `POST /addnegotiation/{buyer_id}`
        * Description: Add a new negotiation.
          * Sample Request Body:  
          ```json
          {
              "buyer_id":1,
              "price":3,
              "quantity":5,
              "transaction":{
                "transaction_id":1
              }
          }
          ```
          * Sample Response:  
          `Status: 201`  
          ```json
          [
              {
                "negotiation_id": 1,
                "buyer_id": 2,
                "post_time": "2022-12-04-17-22-53",
                "close_time": null,
                "price": 3,
                "quantity": 5,
                "open": true,
                "accept": null,
                "transaction": {"transaction_id":1}
              }
          ]
          ```
      * `PUT /accept-negotiation/{seller_id}/{negotiation_id}`
        * Description: Accept one negotiation.
          * Sample HTTP Request: `localhost:8080/accept-negotiation/1/1`
          * Sample Response:  
          `Status: 200`
          ```json
          [
              {
                "negotiation_id": 1,
                "buyer_id": 2,
                "post_time": "2022-12-04-17-22-53",
                "close_time": "2022-12-04-17-22-54",
                "price": 3,
                "quantity": 5,
                "open": false,
                "accept": true,
                "transaction": {"transaction_id":1, "Open": false, "Accept": true}
              }
          ]
          ```
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
