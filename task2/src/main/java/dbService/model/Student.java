package dbService.model;

import java.sql.Date;

public class Student {

    private long id;
    private String name;
    private String surname;
    private String secondName;
    private Date dateBorn;
    private long groupID;

    public Student(){
        this.id = -1;
        this.groupID = -1;
    }

    public Student(long id, String surname, String name, String secondName, Date dateBorn, long groupID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.dateBorn = dateBorn;
        this.groupID = groupID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }


    @Override
    public String toString() {
        return "\tid=" + id +
                "\tname='" + name + '\'' +
                "\tsurname='" + surname + '\'' +
                "\tsecondName='" + secondName + '\'' +
                "\tdateBorn=" + dateBorn +
                "\tgroupID=" + groupID;
    }


}
