package ca.gbc.comp3074.scavengerhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Point {
    public static final String TABLE_NAME = "points";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_RATING = "rating";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+ " ("
                    +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +COLUMN_NAME+" TEXT, "
                    +COLUMN_ADDRESS+" TEXT, "
                    +COLUMN_TASK+" TEXT, "
                    +COLUMN_TAGS+" TEXT, "
                    +COLUMN_RATING+" REAL"
                    + " )";

    private int id;
    private String name;
    private String address;
    private String task;
    private String tags; //just storing tags as csv instead of bothering to create a separate table
    private double rating;

    private ArrayList<String> tagsList;

    public Point(int id, String name, String address, String task, String tags, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.task = task;
        this.tags = tags;
        this.rating = rating;

        List<String> temp = Arrays.asList(tags.split(","));
        this.tagsList = new ArrayList<>(temp);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
