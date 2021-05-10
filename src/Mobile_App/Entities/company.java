package Mobile_App.Entities;

import java.util.Date;
import java.util.Objects;

public class company {
    private int id,user_id,status,contactPhone;
    private double stars;
    private String website,category,country,description,companyName,contactEmail,companyAddress,facebookLink,twitterLink,companyImageName;
    private Date foundedDate;


    public company() {
    }

    public company(int id, int user_id, int status, int contactPhone, double stars, String website, String category, String country, String description, String companyName, String contactEmail, String companyAddress, String facebookLink, String twitterLink, String companyImageName, Date foundedDate) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
        this.contactPhone = contactPhone;
        this.stars = stars;
        this.website = website;
        this.category = category;
        this.country = country;
        this.description = description;
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.companyAddress = companyAddress;
        this.facebookLink = facebookLink;
        this.twitterLink = twitterLink;
        this.companyImageName = companyImageName;
        this.foundedDate = foundedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        company company = (company) o;
        return id == company.id && user_id == company.user_id && status == company.status && contactPhone == company.contactPhone && Double.compare(company.stars, stars) == 0 && Objects.equals(website, company.website) && Objects.equals(category, company.category) && Objects.equals(country, company.country) && Objects.equals(description, company.description) && Objects.equals(companyName, company.companyName) && Objects.equals(contactEmail, company.contactEmail) && Objects.equals(companyAddress, company.companyAddress) && Objects.equals(facebookLink, company.facebookLink) && Objects.equals(twitterLink, company.twitterLink) && Objects.equals(companyImageName, company.companyImageName) && Objects.equals(foundedDate, company.foundedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, status, contactPhone, stars, website, category, country, description, companyName, contactEmail, companyAddress, facebookLink, twitterLink, companyImageName, foundedDate);
    }

    @Override
    public String toString() {
        return "company{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", status=" + status +
                ", contactPhone=" + contactPhone +
                ", stars=" + stars +
                ", website='" + website + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", facebookLink='" + facebookLink + '\'' +
                ", twitterLink='" + twitterLink + '\'' +
                ", companyImageName='" + companyImageName + '\'' +
                ", foundedDate=" + foundedDate +
                '}';
    }
}
