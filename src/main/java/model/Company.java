package model;

import java.sql.Date;
import java.time.LocalDate;

public class Company {
    private LocalDate foundingDate;
    private String companyName;

    public Company(LocalDate foundingDate, String companyName) {
        this.foundingDate = foundingDate;
        this.companyName = companyName;
    }
    public Company(Date foundingDate, String companyName) {
        this.foundingDate =LocalDate.parse(foundingDate.toString());
        this.companyName = companyName;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "foundingDate='" + foundingDate + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
