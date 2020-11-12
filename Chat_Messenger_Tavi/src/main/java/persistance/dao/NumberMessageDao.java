package persistance.dao;

import persistance.model.NumberMessageModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NumberMessageDao  {
    private Connection connection;

    public NumberMessageDao() {
        String url = "jdbc:mysql://premium1.cloud-center.ro:3306/premium8142_db1?serverTimezone=UTC";
        String username = "premium8142_user1";
        String password = "mZNGnB36";
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(NumberMessageModel numberMessageModel) {
        try{
            Statement statement=connection.createStatement();
            String tableName=numberMessageModel.getNameTable();
            int result= statement.executeUpdate("Insert "+tableName+" (numbermessages) values "+"("+numberMessageModel.getNumberMessages()+")");
            if (result!=1)  {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void remove(String tablename) {
        try {
            Statement statement = connection.createStatement();
            int result=     statement.executeUpdate("Delete from "+tablename+" where numbermessages != -1");
            if (result!=1)  {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<NumberMessageModel> getAll(NumberMessageModel numberMessageModel1) {
        List<NumberMessageModel> numberMessageModelList=new ArrayList();

        String tableName=numberMessageModel1.getNameTable();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from "+tableName);
            while (resultSet.next()){
                int number=resultSet.getInt("numbermessages");
                NumberMessageModel numberMessageModel=new NumberMessageModel();
                numberMessageModel.setNumberMessages(number);
                numberMessageModelList.add(numberMessageModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberMessageModelList;
    }


    public void update(NumberMessageModel numberMessageModel,String tableName) {
        remove(tableName);
        add(numberMessageModel);
    }

    public boolean iftableExist(String tableName){
        boolean result=false;
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createtableName(String tableName){
        try {
            Statement statement=connection.createStatement();
            String querry="create table "+tableName+" (numbermessages int);";
            statement.executeUpdate(querry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
