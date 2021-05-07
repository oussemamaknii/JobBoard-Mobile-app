package Mobile_App.Entities;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private int id,isActive,phone;
    private String email,password,firstName,lastName,roles,token,imageName,adresse,professionalTitle;
    private LocalDate dateOfBirth,createdAt,updatedAt,activatedAt;

    public User() {
    }

    public User(int id, int isActive, int phone, String email, String password, String firstName, String lastName, String roles, String token, String imageName, String adresse, String professionalTitle, LocalDate dateOfBirth, LocalDate createdAt, LocalDate updatedAt, LocalDate activatedAt) {
        this.id = id;
        this.isActive = isActive;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.token = token;
        this.imageName = imageName;
        this.adresse = adresse;
        this.professionalTitle = professionalTitle;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.activatedAt = activatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && getIsActive() == user.getIsActive() && getPhone() == user.getPhone() && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getToken(), user.getToken()) && Objects.equals(getImageName(), user.getImageName()) && Objects.equals(getAdresse(), user.getAdresse()) && Objects.equals(getProfessionalTitle(), user.getProfessionalTitle()) && Objects.equals(getDateOfBirth(), user.getDateOfBirth()) && Objects.equals(getCreatedAt(), user.getCreatedAt()) && Objects.equals(getUpdatedAt(), user.getUpdatedAt()) && Objects.equals(getActivatedAt(), user.getActivatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsActive(), getPhone(), getEmail(), getPassword(), getFirstName(), getLastName(), getRoles(), getToken(), getImageName(), getAdresse(), getProfessionalTitle(), getDateOfBirth(), getCreatedAt(), getUpdatedAt(), getActivatedAt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles='" + roles + '\'' +
                ", token='" + token + '\'' +
                ", imageName='" + imageName + '\'' +
                ", adresse='" + adresse + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", activatedAt=" + activatedAt +
                '}';
    }
}
