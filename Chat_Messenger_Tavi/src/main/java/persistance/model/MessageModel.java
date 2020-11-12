package persistance.model;

public class MessageModel {
    private int id;
    private String textMessage;
    private String tableName;

    public MessageModel(String tableName) {
        this.tableName = tableName;
    }

    public MessageModel() {
    }

    public void setId(int id) {
        this.id = id;
    }
        public int getId() {
            return id;
        }


    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
