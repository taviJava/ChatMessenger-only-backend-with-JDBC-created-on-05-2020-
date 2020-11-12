package Service;

import persistance.dao.NumberMessageDao;
import persistance.model.NumberMessageModel;

public class NumberMessageService {
    NumberMessageDao numberMessageDao=new NumberMessageDao();

    public void addNumber(NumberMessageModel numberMessageModel){
        numberMessageDao.add(numberMessageModel);
    }

    public void updateNumber(NumberMessageModel numberMessageModel,String tableNumberName){
        numberMessageDao.update(numberMessageModel,tableNumberName);
    }

    public void createtable(String tablename){
        numberMessageDao.createtableName(tablename);
    }

    public boolean ifTableExist(String tableName){
        return numberMessageDao.iftableExist(tableName);
    }
    public Integer numberMessage(String tableName){
        NumberMessageModel numberMessageModel=new NumberMessageModel(tableName);
        if (numberMessageDao.getAll(numberMessageModel).size()>0){
            return numberMessageDao.getAll(numberMessageModel).get(0).getNumberMessages();}
        return 0;
    }
}
