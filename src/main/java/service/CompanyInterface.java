package service;

import model.Company;

import java.util.Set;

public interface CompanyInterface {
    Company getById(long id);

    Set<Company> getAll();

    Set<Company> get(int offset, int perPage, String sort);

    void save(Company company);

    void update(Company company,String companyName);

    void delete(long companyId);

}
