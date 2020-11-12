package persistance.dao;

import persistance.model.MessageModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements Dao<MessageModel> {
    private Connection connection;

    public MessageDao() {
        String url = "jdbc:mysql://premium1.cloud-center.ro:3306/premium8142_db1?serverTimezone=UTC";
        String username = "premium8142_user1";
        String password = "mZNGnB36";
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(MessageModel messageModel) {
        try{
            Statement statement=connection.createStatement();
            String message="'"+messageModel.getTextMessage()+"'";
            String tableName=messageModel.getTableName();

            int result= statement.executeUpdate("Insert "+tableName+" (textmessage) values "+"(" +message+" )");
            if (result!=1)  {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<MessageModel> getAll(MessageModel messageModel1) {
        List<MessageModel> messageModelList=new ArrayList();

        String tableName=messageModel1.getTableName();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from "+tableName);
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                String textMessage=resultSet.getString("textmessage");
                MessageModel messageModel=new MessageModel();
                messageModel.setId(id);
                messageModel.setTextMessage(textMessage);
                messageModelList.add(messageModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageModelList;
    }

    @Override
    public void update(MessageModel messageModel) {

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

    public void createTable(String tableName){
        try {
            Statement statement=connection.createStatement();
            String querry="create table "+tableName+" (id int  not null auto_increment primary key,textmessage varchar(500));";
            statement.executeUpdate(querry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
