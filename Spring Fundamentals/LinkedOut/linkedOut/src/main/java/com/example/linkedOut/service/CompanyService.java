package com.example.linkedOut.service;

import com.example.linkedOut.model.entity.Company;
import com.example.linkedOut.model.service.CompanyServiceModel;
import com.example.linkedOut.model.view.CompanyDetailViewModel;
import com.example.linkedOut.model.view.CompanyViewModel;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel);

    boolean checkExistence(String name);

    List<String> getAllCompanies();

    Optional<Company> findCompanyByName(String company);

    List<CompanyViewModel> getAllCompaniesAsViewModels();

    CompanyDetailViewModel getCompanyById(String id);

    void deleteCompany(String id);
}
