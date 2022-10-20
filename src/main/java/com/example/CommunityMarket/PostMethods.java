package com.lqg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

url = "";
Connection connection = DriverManager.getConnection(url, "root", "root");

public boolean add_transaction(String userID, String itemID, int price, int quantity) {
    if (!is_logged_in(userID)) {
        return false;
    }
    if (!item_exists(itemID)) {
        return false;
    }

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String.format("select * from Bag where PlayerID = '%s'", userID));
    int num = resultSet.getInt(itemID);
    if (num >= quantity) {
        statement.executeUpdate(String.format("update Bag set %s = %d where PlayerID = '%s'"), itemID, num - quantity, userID);
        statement.executeUpdate(String.format("insert into Posts(PostID, SellerID, ItemID, PostPrice, PostTime," +
            "Quantity, IsOpen) values(%s, %s, %s, %d, %d, %d, true)"), UUID.randomUUID(), userID, itemID, price,
            System.currentTimeMillis(), quantity);
        statement.close();
        return true;
    } else {
        statement.close();
        return false
    }
}

public boolean delete_transaction(String userID, String postID) {
    if (!is_logged_in(userID)) {
        return false;
    }
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String.format(
            "select * from Posts where PostID = '%s' and IsOpen = 'true'", postID));
    if (resulstSet != null) {
        String itemID = resultSet.getString("ItemID");
        int quantity = resultSet.getInt("Quantity");
        ResultSet userBag = statement.executeQuery(String.format("select * from Bag where PlayerID = '%s'", userID));
        int currentNum = userBag.getInt(itemID);
        statement.executeUpdate(String.format("update Bag set %s = %d where PlayerID = '%s'"), itemID, currentNum + quantity, userID);
        close_sale(postID);
        statement.close();
        return true;
    } else {
        statement.close();
        return false;
    }
}

public List<ResultSet> search_transaction(String itemID, String categoryID, boolean byCategory) {
    if (!byCategory) {
        if (!item_exists(itemID)) {
            return null;
        } else {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select * from Posts where ItemId = '%s' and IsOpen = 'true"), itemID);
            statement.close();
            if (resultSet == null) {
                return new ArrayList<ResultSet>();
            } else {
                List toReturn = new ArrayList<ResultSet>();
                toReturn.add(resultSet);
                return toReturn;
            }
        }
    } else {
        if (!category_exists(categoryID)) {
            return null;
        } else {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                "select ItemID from Item where CID = '%s'"), categoryID);
            List toReturn = new ArrayList<ResultSet>();
            while (resultSet.hasNext()) {
                String item = resultSet.getString("ItemID");
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement.executeQuery(String.format(
                    "select * from Posts where ItemId = '%s' and IsOpen = 'true"), item);
                statement1.close();
                if (resultSet1 != null){
                    toReturn.add(resultSet1);
                }
                resultSet = resultSet.next();
            }
            statement.close();
            return toReturn;
        }
    }
}

public boolean buy_item(String userID, String PostID) {
    if (!is_logged_in(userID)) {
        return false;
    }
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String.format(
        "select * from Posts where PostID = '%s' and IsOpen = 'true'", postID));
    if (resultSet != null) {
        String itemID = resultSet.getString("ItemID");
        int quantity = resultSet.getInt("Quantity");
        int price = resultSet.getInt("PostPrice");
        String sellerID = resultSet.getString("SellerID");
        int money = statement.executeQuery(String.format("select * from Bag where PlayerID = '%s'"), userID).getInt("Money");
        if (money < price) {
            return false;
        } else {
            statement.executeUpdate(String.format("update Bag set Money = %d where PlayerID = '%s'"), money - price, userID);
            int currentNum = statement.executeQuery(String.format("select * from Bag where PlayerID = '%s'"), userID).getInt(itemID);
            statement.executeUpdate(String.format("update Bag set %s = %d where PlayerID = '%s'"), itemID, currentNum + quantity, userID);
            int sellerMoney = statement.executeQuery(String.format("select * from Bag where PlayerID = '%s'"), sellerID).getInt("Money");
            statement.executeUpdate(String.format("update Bag set Money = %d where PlayerID = '%s'"), sellerMoney + price, sellerID);
            statement.close();
            close_sale(postID);
            return true;
        }
    } else {
        statement.close();
        return false;
    }

}

public boolean close_sale(String postID) {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String.format(
        "select * from Posts where PostID = '%s' and IsOpen = 'true'", postID));
    if (resultSet == null) {
        statement.close();
        return false;
    } else {
        statement.executeUpdate(String.format("update Posts set IsOpen = false"))
        statement.close();
        return true;
    }
}

public boolean is_logged_in(String userID) {
    //return true is the given userID is the currently logged in user
}

public boolean item_exists(String itemID) {
    //return true if the given itemID exists
}

public boolean category_exists(String categoryID) {
}