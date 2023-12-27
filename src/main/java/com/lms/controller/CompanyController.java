package com.lms.controller;

import com.lms.model.entities.Company;
import com.lms.service.impl.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("company", new Company());
        return "/companies/save";

    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies/find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companyList", companies);
        return "/companies/find-all";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("updateCompany", company);
        return "/companies/update";
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
        companyService.update(id, company);
        return "redirect:/companies/find-all";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteCompany(@PathVariable("id") Long id) {
        Company company = companyService.getCompanyById(id);
        companyService.deleteById(id);
        return "redirect:/companies";
    }
}







//    @GetMapping("/students/{companyId}")
//    public String getStudentsByCompany(@PathVariable("companyId") Long companyId, Model model) {
//        List<Student> studentList = studentService.getStudentByCompany(companyId);
//        model.addAttribute("studentList", studentList);
//        model.addAttribute("count", studentList.size());
//        return "company/students";
//    }
















//    private final CompanyService companyService;
//
//    @GetMapping("/add")
//    public String add(Model model) {
//        model.addAttribute("company", new Company());
//        return "/company/save";
//
//    }
//
//    @PostMapping("/save")
//    public String saveCompany(@ModelAttribute("company") Company company) {
//        companyService.addCompany(company);
//        return "redirect:/companies/find-all";
//    }
//
//    @GetMapping("/find-all")
//    public String findAll(Model model) {
//        model.addAttribute("companyList", companyService.getAllCompanies());
//        return "/company/find-all";
//    }
//
//    @GetMapping("update/{id}")
//    public String update(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("company", companyService.getCompanyById(id));
//        return "/company/update";
//    }
//
//    @PostMapping("/save-update/{id}")
//    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
//        companyService.update(id, company);
//        return "redirect:/companies/find-all";
//    }


