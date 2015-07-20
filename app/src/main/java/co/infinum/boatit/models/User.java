package co.infinum.boatit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kjurkovic on 20/07/15.
 */
public class User implements Serializable {

    @SerializedName("token")
    private String token;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
