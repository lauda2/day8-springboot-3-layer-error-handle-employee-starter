package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.ICompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies(Integer page, Integer size) {
        if (page == null || size == null) {
            return companyRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(page - 1, size);
            return companyRepository.findAll(pageable).toList();
        }
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(int id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        return company.get();
    }

    public Company updateCompany(int id, Company updatedCompany) {
        Company company = getCompanyById(id);
        if (company == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        updatedCompany.setId(id);
        return companyRepository.save(updatedCompany);
    }

    public void deleteCompany(int id) {
        companyRepository.delete(getCompanyById(id));
    }

}
