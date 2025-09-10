package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void empty() {
        companyRepository.empty();
    }

    public List<Company> getCompanies(Integer page, Integer size) {
        List<Company> companies = companyRepository.getCompanies();
        if (page != null && size != null) {
            int start = (page - 1) * size;
            int end = Math.min(start + size, companies.size());
            if (start >= companies.size()) {
                return new ArrayList<>();
            }
            return companies.subList(start, end);
        }
        return companies;
    }

    public Company createCompany(Company company) {
        return companyRepository.createCompany(company);
    }

    public Company getCompanyById(int id) {
        if (companyRepository.getCompanyById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        return companyRepository.getCompanyById(id);
    }

    public Company updateCompany(int id, Company updatedCompany) {
        return companyRepository.updateCompany(id, updatedCompany);
    }
}
