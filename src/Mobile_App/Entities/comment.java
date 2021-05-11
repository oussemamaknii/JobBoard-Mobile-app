package Mobile_App.Entities;

import java.time.LocalDate;
import java.util.Date;

public class Comment {

    private int id;
    private String name;
    private String email;
    private long phone;
    private String message;
    private Date created_at;
    private int idEvent;

    public Comment(int id, String name, String email, long phone, String message, Date created_at, int idEvent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.created_at = created_at;
        this.idEvent = idEvent;
    }

    public Comment() {

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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override


    public String toString() {
        return "comment{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", message=" + message + ", created_at=" + created_at + ", idEvent=" + idEvent + '}';
    }
}
