package ui;

import Service.UserService;
import persistance.model.UserModel;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    UserService userService=new UserService();
    Scanner input=new Scanner(System.in);

    public void addToList() {
        System.out.println("Introdu numele");
        String name = input.nextLine();
        System.out.println("Introdu Username-ul");
        String username = input.nextLine();
        System.out.println("Introdu parola");
        String password = input.nextLine();
        UserModel userModel=new UserModel();
        userModel.setName(name).setPassword(password).setUsername(username);
        userService.addUser(userModel);
    }
    public void removeUser(UserModel userModel){
        System.out.println("1. Confirma stergera contului ");
        System.out.println("2. Vreau sa-mi mentin contul");
        int a=input.nextInt();
        if (a==1){
            userService.removeUser(userModel.getId());
            System.out.println("Userul tau a fost sters, oricand doresti poti sa te inregistrezi din nou");
        }
    }


}
