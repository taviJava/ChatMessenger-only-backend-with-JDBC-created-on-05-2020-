package persistance.dao;

import persistance.model.UserModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao <UserModel>{

    private Connection connection;


    public UserDao() {
        String url = "jdbc:mysql://premium1.cloud-center.ro:3306/premium8142_db1?serverTimezone=UTC";
        String username = "premium8142_user1";
        String password = "mZNGnB36";
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(UserModel userModel) {
        try{
            Statement statement=connection.createStatement();
            String username="'"+userModel.getUsername()+"'";
            String name="'"+userModel.getName()+"'";
            String password="'"+userModel.getPassword()+"'";
            int result= statement.executeUpdate("Insert users (name,username,password) values "+"("+name+","+username+","+password+")");
            if (result!=1)  {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            Statement statement = connection.createStatement();
            int result=     statement.executeUpdate("Delete from users where id="+id);
            if (result!=1)  {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getAll(UserModel userModel1) {
        List<UserModel> userModelList=new ArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from users");
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                String name=resultSet.getString("name");
                String username=resultSet.getString("username");
                String password=resultSet.getString("password");
                UserModel userModel=new UserModel();
                userModel.setId(id).setName(name).setUsername(username).setPassword(password);
                userModelList.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userModelList;
    }


    public void update(UserModel userModel) {
        remove(userModel.getId());
        add(userModel);
    }


}
