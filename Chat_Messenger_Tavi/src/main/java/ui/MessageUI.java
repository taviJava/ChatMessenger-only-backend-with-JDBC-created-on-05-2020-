package ui;

import Service.MessageService;
import Service.NumberMessageService;
import persistance.model.MessageModel;
import persistance.model.NumberMessageModel;
import java.util.Scanner;

public class MessageUI {
Scanner scanner=new Scanner(System.in);
private MessageService messageService=new MessageService();
private NumberMessageService numberMessageService=new NumberMessageService();


    public void verifyNewMessages(String numberTableName,String username,String messageTableName,String messageTableNameBefore) {
        int a=0;
        if (messageService.ifTableExist(messageTableName)) {
            if (numberMessageService.ifTableExist(numberTableName)){
             a = numberMessageService.numberMessage(numberTableName);}

            if (a < messageService.getList(messageTableName).size()) {
                System.out.println("Ai " + (messageService.getList(messageTableName).size() - a) + " mesaje noi de la: " + username);
            }
        } else if (messageService.ifTableExist(messageTableNameBefore)) {
            if (numberMessageService.ifTableExist(numberTableName)){
             a = numberMessageService.numberMessage(numberTableName);}
            if (a < messageService.getList(messageTableNameBefore).size()) {
                System.out.println("Ai " + (messageService.getList(messageTableNameBefore).size() - a) + " mesaje noi de la: " + username);
            }
        }
    }
private void conversation(String username, String usernameLogin,String messageTableName,String numberTableName){
        int option=-1;
        while (option!=0){
            meniu(username);
            option=scanner.nextInt();
            scanner.nextLine();
            MessageModel messageModel=new MessageModel(messageTableName);
            NumberMessageModel numberMessageModel = new NumberMessageModel(numberTableName);
            if (option==1){
                System.out.println("Scrie un mesaj");
                String messagewrited=scanner.nextLine();
                String message = usernameLogin + ": "+messagewrited;
                messageModel.setTextMessage(message);
                messageService.addMessage(messageModel);
                   updateNumberTable(numberMessageModel,messageTableName,numberTableName);
            }else if (option==2){
                    messageService.showMesseges(messageModel);
                   updateNumberTable(numberMessageModel,messageTableName,numberTableName);
            }
        }
}

private void updateNumberTable(NumberMessageModel numberMessageModel,String messageTableName,String numberTableName) {
    numberMessageModel.setNumberMessages(messageService.getList(messageTableName).size());
    if (numberMessageService.ifTableExist(numberTableName)) {
        numberMessageService.updateNumber(numberMessageModel, numberTableName);
    } else if (!numberMessageService.ifTableExist(numberTableName)) {
        numberMessageService.createtable(numberTableName);
        numberMessageService.addNumber(numberMessageModel);
    }
}
    private void meniu(String username){
        System.out.println("1. Scrie-i un mesaj lui: " +username);
        System.out.println("2. Citeste conversatia cu:"+ username);
        System.out.println("0. Iesi din conversatia cu: "+username);
    }

    public void verifyIfTableExist(String messageTableName,String messageTableNameBefore,String username,String numberTableName,String usernameLogin){
        if (messageService.ifTableExist(messageTableNameBefore)){
            conversation(username,usernameLogin,messageTableNameBefore,numberTableName);
        }else if (messageService.ifTableExist(messageTableName)){
            conversation(username,usernameLogin,messageTableName,numberTableName);
        }else{
            messageService.createTable(messageTableName);
            conversation(username,usernameLogin,messageTableName,numberTableName);
        }
    }
}
