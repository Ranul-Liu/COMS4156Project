# Marketplace System
Team: CHLW  
Members: Yichen Liu, Siyu Wu, Mengyuan Huang, Jiyao Chen
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

## TODOs
Currently Item, Transaction, and User these 3 services work independently, but in this system only Item works. We are working hard to make the whole system work. We are also working to fill other functions like validating user login. We will have them done before the demo.
