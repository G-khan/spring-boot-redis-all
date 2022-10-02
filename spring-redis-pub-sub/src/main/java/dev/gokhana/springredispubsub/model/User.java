package dev.gokhana.springredispubsub.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 384738883699211442L;
    // define fields
    private String id;
    private String name;
    private String email;
    private int score;

    // define constructors
    public User() {
    }

    public User(String id, String name, String email, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
    }

    // define getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // define equals() and hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (score != user.score) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    // define toString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                '}';
    }


}
