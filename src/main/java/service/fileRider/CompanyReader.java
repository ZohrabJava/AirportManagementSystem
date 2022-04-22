package service.fileRider;

import model.Address;
import model.Company;
import service.DBInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CompanyReader {
    public static Set<Company> companyReader() {
        Set<Company> companies = null;
        try {
            companies = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/companies.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                Company com = new Company(LocalDate.parse(DBInput.dateConvert( arr[1])), arr[0]);
                companies.add(com);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
