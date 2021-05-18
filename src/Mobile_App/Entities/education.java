package Mobile_App.Entities;


import java.util.Date;
import java.util.Objects;

public class education {
    private int id,resume_id;
    private String course,institute;
    private Date dateFrom,dateTo;


    public education() {
    }

    public education(int id, int resume_id, String course, String institute, Date dateFrom, Date dateTo) {
        this.id = id;
        this.resume_id = resume_id;
        this.course = course;
        this.institute = institute;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        education education = (education) o;
        return id == education.id && resume_id == education.resume_id && Objects.equals(course, education.course) && Objects.equals(institute, education.institute) && Objects.equals(dateFrom, education.dateFrom) && Objects.equals(dateTo, education.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resume_id, course, institute, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "education{" +
                "id=" + id +
                ", resume_id=" + resume_id +
                ", course='" + course + '\'' +
                ", institute='" + institute + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
