package com.martinez.dyim.pointofsale.model;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Dyim on 05/10/2017.
 */

@IgnoreExtraProperties
public class User {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;

    public User() {

    }
    public User(String id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public User(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @Exclude
    public String getFullName() {
        return firstName+" "+middleName.substring(0,1)+". "+lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getId() { return id; }
}
