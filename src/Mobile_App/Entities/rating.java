package Mobile_App.Entities;

import java.util.Objects;

public class rating {
    private int id;
    private String candidate,title,description,owner;
    private double stars;


    public rating() {
    }

    public rating(int id, String candidate, String title, String description, String owner, double stars) {
        this.id = id;
        this.candidate = candidate;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        rating rating = (rating) o;
        return id == rating.id && Double.compare(rating.stars, stars) == 0 && Objects.equals(candidate, rating.candidate) && Objects.equals(title, rating.title) && Objects.equals(description, rating.description) && Objects.equals(owner, rating.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate, title, description, owner, stars);
    }

    @Override
    public String toString() {
        return "rating{" +
                "id=" + id +
                ", candidate='" + candidate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", stars=" + stars +
                '}';
    }
}
