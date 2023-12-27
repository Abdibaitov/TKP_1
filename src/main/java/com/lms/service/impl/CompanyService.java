package com.lms.service.impl;

import com.lms.model.entities.Company;
import com.lms.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return  companyRepository.findAll();
    }

    public void addCompany(Company company){
        companyRepository.save(company);
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found Company by id "+id));
    }

        public void update(Long id,Company company){
        Company oldCompany = getCompanyById(id);
        oldCompany.setCompanyName(company.getCompanyName());
        oldCompany.setLocatedCountry(company.getLocatedCountry());
        oldCompany.setDirectorName(company.getDirectorName());
        oldCompany.setLocalDate(company.getLocalDate());
        companyRepository.save(oldCompany);
    }

        public void deleteById(Long id){
        companyRepository.deleteById(id);
    }



}
//    private final CompanyRepository companyRepository;
//    private final UserRepository userRepository;
//
//    public void create(Company company){
//        companyRepository.save(company);
//    }
//
//    public Company findById(Long id){
//        return companyRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Not found Company by id "+id));
//    }
//
//    public List<Company> findAll(){
//        return companyRepository.findAll();
//    }
//
//    public void update(Long id,Company company){
//        Company oldCompany = findById(id);
//        oldCompany.setCompanyName(company.getCompanyName());
//        oldCompany.setLocatedCountry(company.getLocatedCountry());
//        oldCompany.setDirectorName(company.getDirectorName());
//        oldCompany.setLocalDate(company.getLocalDate());
//        companyRepository.save(oldCompany);
//    }
//
//    public void deleteById(Long id){
//        companyRepository.deleteById(id);
//    }