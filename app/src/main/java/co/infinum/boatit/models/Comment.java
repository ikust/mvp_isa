package co.infinum.boatit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kjurkovic on 20/07/15.
 */
public class Comment implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("author")
    private User author;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getAuthor() {
        return author;
    }
}
