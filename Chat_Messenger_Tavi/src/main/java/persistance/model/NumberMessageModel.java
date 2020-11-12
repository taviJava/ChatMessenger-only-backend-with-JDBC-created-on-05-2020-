package persistance.model;

public class NumberMessageModel {
    private int numberMessages;
    private String nameTable;

    public NumberMessageModel(String nameTable) {
        this.nameTable = nameTable;
    }

    public NumberMessageModel() {
    }

    public int getNumberMessages() {
        return numberMessages;
    }

    public void setNumberMessages(int numberMessages) {
        this.numberMessages = numberMessages;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }
}
