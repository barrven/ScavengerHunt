package ca.gbc.comp3074.scavengerhunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamMember {
    public static final String TABLE_NAME = "team";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_SMS = "sms";

    public static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+ " ("
                    +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +COLUMN_NAME+" TEXT, "
                    +COLUMN_EMAIL+" TEXT, "
                    +COLUMN_PHONE+" TEXT, "
                    +COLUMN_SMS+" TEXT )";

    private int id;
    private String name;
    private String email;
    private String phone;
    private String sms;

    public TeamMember(int id, String name, String email, String phone, String sms) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sms = sms;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
