package persistance.model;

public class UserModel {
    private int id;
    private String name;
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserModel setId(int id) {
        this.id = id;
        return this;
    }
}
