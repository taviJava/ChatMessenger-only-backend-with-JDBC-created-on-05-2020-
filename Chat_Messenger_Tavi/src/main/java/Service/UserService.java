package Service;

import persistance.dao.UserDao;
import persistance.model.UserModel;
import java.util.List;

public class UserService {
    private UserDao userDao=new UserDao();

    public void addUser(UserModel userModel){
        UserModel userModel1=new UserModel();
        List<UserModel> userModelList=userDao.getAll(userModel1);
        if (userModelList.size()==0){
            userDao.add(userModel);
        }else if (userModelList.size()>0){
            if (exist(userModel.getUsername())){
                System.out.println("Acest Username este deja inregistrat, incercati cu unul diferit!");
            }else{
                userDao.add(userModel);
            }
        }
    }

    public void removeUser(int id){
        userDao.remove(id);
    }

    public List<UserModel> getUsers(){
        UserModel userModel=new UserModel();
        List<UserModel> userModelList=userDao.getAll(userModel);
        return userModelList;
    }

    public void update(UserModel userModel){
        userDao.update(userModel);
    }


    public boolean exist (String username){
        UserModel userModel=new UserModel();
        List<UserModel> userModelList=userDao.getAll(userModel);
        for (UserModel userModel1 : userModelList) {
            if (userModel1.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean login(String username,String password){
        UserModel userModel=new UserModel();
        List<UserModel> userModelList=userDao.getAll(userModel);
        for (UserModel userModel1 : userModelList) {
            if (userModel1.getUsername().equals(username)&&userModel1.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public void showList(){
        UserModel userModel=new UserModel();
        List<UserModel> userModelList=userDao.getAll(userModel);
        userModelList.stream().forEach(userModel1 -> System.out.println(userModel1.getId()+" "+userModel1.getName()+" "+userModel1.getUsername()));

    }


}
