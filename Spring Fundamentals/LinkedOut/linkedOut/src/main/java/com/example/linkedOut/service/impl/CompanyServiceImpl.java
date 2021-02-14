package com.example.linkedOut.service.impl;

import com.example.linkedOut.model.entity.Company;
import com.example.linkedOut.model.service.CompanyServiceModel;
import com.example.linkedOut.model.view.CompanyDetailViewModel;
import com.example.linkedOut.model.view.CompanyViewModel;
import com.example.linkedOut.repository.CompanyRepository;
import com.example.linkedOut.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel) {

        Company company = this.modelMapper.map(companyServiceModel, Company.class);

        this.companyRepository.saveAndFlush(company);

        return companyServiceModel;
    }

    @Override
    public boolean checkExistence(String name) {
        return this.companyRepository.findCompanyByName(name).isPresent();
    }

    @Override
    public List<String> getAllCompanies() {
        return this.companyRepository.getCompaniesFromDB();
    }

    @Override
    public Optional<Company> findCompanyByName(String name) {
        return this.companyRepository.findCompanyByName(name);
    }

    @Override
    public List<CompanyViewModel> getAllCompaniesAsViewModels() {

        return this.companyRepository.getAllCompanies().stream().map(company -> {
            return this.modelMapper.map(company, CompanyViewModel.class);
        }).collect(Collectors.toList());

    }

    @Override
    public CompanyDetailViewModel getCompanyById(String id) {

        Company company = this.companyRepository.findCompanyById(id);

        return this.modelMapper.map(company,CompanyDetailViewModel.class);
    }

    @Override
    public void deleteCompany(String id) {
        this.companyRepository.deleteById(id);
    }
}
