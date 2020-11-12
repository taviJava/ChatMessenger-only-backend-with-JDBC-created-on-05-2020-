package Service;

import persistance.dao.MessageDao;
import persistance.model.MessageModel;

import java.util.List;


public class MessageService {
    private MessageDao messageDao=new MessageDao();

public void addMessage(MessageModel messageModel){
    messageDao.add(messageModel);
}

public Integer readVarListMessageSize(MessageModel messageModel){
    int a=messageDao.getAll(messageModel).size();
    return a;
}

public void createTable(String nameTable){
    messageDao.createTable(nameTable);
}
public boolean ifTableExist(String tableName){
  return   messageDao.iftableExist(tableName);
}

public void showMesseges(MessageModel messageModel){
    List<MessageModel> messageModelList=messageDao.getAll(messageModel);
    messageModelList.stream().forEach(messageModel1 -> System.out.println(messageModel1.getTextMessage()));
}

public List<MessageModel> getList(String tableName){
    MessageModel messageModel=new MessageModel(tableName);
    return messageDao.getAll(messageModel);
}




}
