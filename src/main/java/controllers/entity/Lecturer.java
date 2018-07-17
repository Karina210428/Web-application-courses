package controllers.entity;

public class Lecturer {
    private int id;
    private String surname;
    private String patronymic;
    private String name;

    public Lecturer() {

    }

    public Lecturer(int id, String surname, String name, String patronymic) {
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;

        Lecturer lecturer = (Lecturer) o;

        if (getId() != lecturer.getId())
            return false;
        if (getName() != null ? !getName().equals(lecturer.getName()) : lecturer.getName() != null)
            return false;
        if (getSurname() != null ? !getSurname().equals(lecturer.getSurname()) : lecturer.getSurname()!= null)
            return false;
        return !(getPatronymic() != null ? !getPatronymic().equals(lecturer.getPatronymic()) : lecturer.getPatronymic() != null);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + (surname != null ? surname: "") +
                " " + ( name!= null ? name : " ")+
                " " + (patronymic!= null ? patronymic: "");
    }
}
