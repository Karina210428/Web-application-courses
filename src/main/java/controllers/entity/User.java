package controllers.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private int idRole;
    public User(){};


    public User(int id, String login, String password, int idRole){
        this.login = login;
        this.id = id;
        this.password = password;
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
