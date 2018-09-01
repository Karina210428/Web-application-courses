package controllers.entity;

public class Users {
    private int id;
    private String login;
    private String password;
    private String occupation;

    public Users(){};


    public Users(int id, String login, String password, String occupation ){
        this.login = login;
        this.id = id;
        this.occupation = occupation;
        this.password = password;
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

    public String getOccupation() {
        return occupation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
