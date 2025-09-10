package com.example.demo.repository;

import com.example.demo.entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public void empty() {
        companies.clear();
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company createCompany(Company company) {
        company.setId(1);
        companies.add(company);
        return company;
    }

    public Company getCompanyById(int id) {
        for (Company c : companies) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public Company updateCompany(int id, Company updatedCompany) {
        Company found = getCompanyById(id);
        if (found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        found.setName(updatedCompany.getName());
        return found;
    }

    public void deleteCompany(int id) {
        companies.remove(getCompanyById(id));
    }
}
