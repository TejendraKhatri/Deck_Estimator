package com.pack.functions;

import com.pack.connectivity.ConnectionClass;
import com.pack.interfaces.MaterialCode;
import com.pack.main.ResultPageController;
import com.pack.objects.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.pack.main.DeckDetailsController.newStairs;

public class UsefulFunctions{
    public static ArrayList<Product> materialList = new ArrayList<>();
    static PreparedStatement st;
    public static ResultSet resultSet = ResultPageController.rs;

    public static boolean userExists(){
        try{
            resultSet = ConnectionClass.connection.createStatement().executeQuery("SELECT * FROM user");
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void populateObservableList(double lth,double bdth , int height){
        calculateBoardingMaterial(lth,bdth);
        calculateJoistsBearers(lth,bdth);
        calculateFootings(lth,bdth,height);
        calculateConcrete(lth,bdth);
        calculateBalusterBeveled(lth,bdth);
        calculateScrews(lth,bdth);
        if(newStairs != null){
            calculateStairMaterials(height);
        }
    }

    private static void calculateStairMaterials(int height) {
        addToMaterialOrderList(MaterialCode.Stair_Stringer,3);
        addToMaterialOrderList(MaterialCode.Wood_2x12x8,1);
        addToMaterialOrderList(MaterialCode.Wood_2x12x10,2);
    }

    private static void calculateScrews(double lth, double bdth) {
        int boxCt = (int) (3.5 * lth * bdth / 115);
        if(boxCt == 0){
            boxCt++;
        }
        addToMaterialOrderList(MaterialCode.Nails,boxCt);
    }

    private static void calculateBalusterBeveled(double lth, double bdth) {
        int qtyBaluster = (int) ((12 * lth + 24 * bdth - 6) / 5.5);
        if(newStairs != null){
            int num = (int) (2 / 5.5 * (120 * 9/ 7.5 - 2));
            qtyBaluster += num;
        }
        addToMaterialOrderList(MaterialCode.BalusterBeveled,qtyBaluster);
    }

    private static void addToMaterialOrderList(int id , int qty){
        if(qty <= 0){
            return;
        }
        try{
            st = ConnectionClass.connection.prepareStatement("SELECT * FROM material WHERE Product_ID = ?");
            st.setInt(1, id);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                materialList.add(new Product(resultSet.getString("Material_Name"),
                        qty,resultSet.getDouble("Unit_Price")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void calculateBoardingMaterial(double lth, double bdth){
        double totalArea = lth * bdth;
        int board_10ft = (int) (Math.ceil(totalArea)/5);
        double remainingArea = totalArea - board_10ft*5;
        int board_8ft = (int) (Math.ceil(remainingArea)/4) + 1;
        addToMaterialOrderList(MaterialCode.DECK_BOARD_10FT,board_10ft);
        addToMaterialOrderList(MaterialCode.DECK_BOARD_8FT,board_8ft);
    }

    private static void calculateJoistsBearers(double lth,double bdth){
        double shorter;
        if(lth<bdth){
            shorter = lth;
            lth = bdth;
            bdth = shorter;
        }
        int joistNums = (int) (bdth * 12 /16 + 1);
        int bearerNums = (int) (lth / 2 + 1 + 2);
        addToMaterialOrderList(MaterialCode.Wood_2x8x10,joistNums + bearerNums);
    }

    private static void calculateFootings(double lth, double bdth,int height){
        int numFootings = (int) (Math.sqrt(lth*bdth) - 1);
        if(height > 8){
            addToMaterialOrderList(MaterialCode.Wood_4x4x10,numFootings);
            addToMaterialOrderList(MaterialCode.Wood_4x4x8,1);
        }
        else{
            addToMaterialOrderList(MaterialCode.Wood_4x4x10,1);
            addToMaterialOrderList(MaterialCode.Wood_4x4x8,numFootings);
        }
    }

    private static void calculateConcrete(double lth, double bdth){
        int concQty = (int) (Math.sqrt(lth * bdth / 4) + 1) ;
        addToMaterialOrderList(MaterialCode.ConcreteMix , concQty);
    }

    public static void addUserToDatabase(String name, String phNu){
        try{
            st = ConnectionClass.connection.prepareStatement("INSERT INTO user( " +
                    "User_Name, User_PhoneNumber) " +
                    "VALUES(?,?)");
            st.setString(1,name);
            st.setString(2,phNu);
            st.executeUpdate();
            System.out.println("USER ADDED!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomerToDatabase(String name, String PhoneNum, String address){
        try{
            st = ConnectionClass.connection.prepareStatement("INSERT INTO customers( " +
                    "Customer_Name,Customer_Address,Customer_PhoneNumber) " +
                    "VALUES(?,?,?)");
            st.setString(1,name);
            st.setString(3,PhoneNum);
            st.setString(2,address);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMaterialPrice(int id, double price){
        try{
            st = ConnectionClass.connection.prepareStatement("UPDATE material SET Unit_Price = ? WHERE Product_ID =?");
            st.setInt(2, id);
            st.setDouble(1,price);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


