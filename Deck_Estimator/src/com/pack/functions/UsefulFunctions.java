package com.pack.functions;

import com.pack.interfaces.MaterialCode;
import com.pack.main.ResultPageController;
import com.pack.objects.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsefulFunctions{
    public static ArrayList<Product> materialList = new ArrayList<>();
    static PreparedStatement st;
    static ResultSet resultSet = ResultPageController.rs;

    public static ArrayList populateObservableList(double lth,double bdth , int height){
        calculateBoardingMaterial(lth,bdth);
        calculateJoistsBearers(lth,bdth);
        calculateFootings(lth,bdth,height);
        return  materialList;
    }

    private static void addToMaterialOrderList(int id , int qty){
        try{
            st = ResultPageController.connection.prepareStatement("SELECT * FROM material WHERE Product_ID = ?");
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

    private static void addUserToDatabase(String name, String PhoneNum){

    }

    private static void addCustomer(String name, String phNum, String address){

    }

}


