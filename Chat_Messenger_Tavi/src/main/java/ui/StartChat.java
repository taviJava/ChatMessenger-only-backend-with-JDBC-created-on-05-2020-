package ui;

import Service.UserService;
import persistance.model.UserModel;

import java.io.File;
import java.util.Scanner;

public class StartChat {
    Scanner scanner=new Scanner(System.in);
    private UserUI userUI=new UserUI();
    private MessageUI messageUI=new MessageUI();
    private UserService userService=new UserService();



    private void verifyNewMessage(String usernameLogin){
        for (UserModel userModel:userService.getUsers()){
String numberTableName=usernameLogin+"Number" + userModel.getUsername();
String messageTableName=usernameLogin + "Conversation" + userModel.getUsername();
String messageTableNameBefore=userModel.getUsername() + "Conversation" + usernameLogin;
messageUI.verifyNewMessages(numberTableName,userModel.getUsername(),messageTableName,messageTableNameBefore);
        }
    }
    private void meniu(){
        System.out.println("1. Incepe o Conversatie");
        System.out.println("2. Afiseaza lista persoanelor cu care poti sa conversezi");
        System.out.println("3. Sterge Contul");
        System.out.println("0. Exit");
    }

    private void meniuAfterLogin(){

            System.out.println("Introdu username");
            String usernameLogin = scanner.nextLine();
            System.out.println("Introdu parola");
            String password = scanner.nextLine();
            if (userUI.userService.login(usernameLogin,password)) {
                int option=-1;
                MyRun myRun=new MyRun(usernameLogin,option);
                myRun.start();
                int a=userService.getUsers().size();  //am creat o variabila a pt a stoca marimea listei inainte de stergere sau in caz de stergere a unui user
                while (option!=0) {
                   meniu();
                    option=scanner.nextInt();
                    scanner.nextLine();
                    if (option == 1) {
                        System.out.println("************Introdu username-ul persoanei cu care vrei sa conversezi*************");
                        String username = scanner.nextLine();  //userul cu care vrei sa vorbesti
                        if (userService.exist(username)) {
                            String messageTableNameBefore = username + "Conversation" + usernameLogin;
                            String messageTableName = usernameLogin + "Conversation" + username;
                            String numberTableName = usernameLogin + "Number" + username;
                            messageUI.verifyIfTableExist(messageTableName,messageTableNameBefore,username,numberTableName,usernameLogin);

                        }
                        else {
                            System.out.println("Persoana cu care vrei sa conversezi nu este inregistrata");
                        }
                    }
                    else if (option==2){
                        userService.showList();
                    }
                    else if (option==3){
                       UserModel userModel1 = userService.getUsers().stream().findFirst().filter(userModel -> userModel.getUsername().equals(usernameLogin)).get();
                        userService.removeUser(userModel1.getId());
                    }
                    if ( a==userService.getUsers().size()){

                    }
                    else if (a>userService.getUsers().size()){ //daca userul a fost sters atunci, a care era marimea initiala a listei este mai mare decat marimea actuala a listei, am creat asta pt ca in caz de stergere sa ma scoata automat din meniu
                        option=0;
                    }
                }
                myRun.setOption(option);
            }
            else{
                System.out.println("Username-ul sau parola introdusa sunt gresite");
            }
        }



    public void meniuStartApp () {
        int optiune = -1;
        while (optiune != 0) {
            System.out.println("1. Inregistreaza-te");
            System.out.println("2. Autentifica-te");
            System.out.println("0. Exit");
            optiune=scanner.nextInt();
            scanner.nextLine();
            if (optiune == 1) {
                int a=userService.getUsers().size();
                userUI.addToList();
                if (userService.getUsers().size()>a){
                    System.out.println("Felicitari userul tau a fost creat cu succes!!! ");
                }
            }
            else if (optiune == 2) {
                meniuAfterLogin();
            }
        }
    }
    private class MyRun extends Thread implements Runnable{
        private String stringName;
        private int option;

        public MyRun(String stringName, int option) {
            this.stringName = stringName;
            this.option = option;
        }

        public String getStringName() {
            return stringName;
        }

        public int getOption() {
            return option;
        }

        public void setOption(int option) {
            this.option = option;
        }

        @Override
        public void run() {

            while (getOption()!=0){
                try {
                    verifyNewMessage(getStringName());
                    Thread.sleep(50000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
