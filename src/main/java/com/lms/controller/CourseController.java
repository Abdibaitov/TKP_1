package com.lms.controller;

import com.lms.model.entities.Company;
import com.lms.model.entities.Course;
import com.lms.service.impl.CompanyService;
import com.lms.service.impl.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;

    @ModelAttribute("companyList")
    public List<Company> getCompanyList() {
        return companyService.getAllCompanies();

    }

    @GetMapping
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "courses/course";

    }

    @GetMapping("/addCourse")
    public String addCourses(Model model) {
        model.addAttribute("course", new Course());
        return "courses/addCourse";

    }

    @PostMapping("saveCourse")

    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course.getCompanyId(), course);
        return "redirect:/courses";
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("updateCourse", course);
        return "courses/updateCourse";
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String saveUpdateCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        courseService.updateCourse(course.getCompanyId(), id, course);
        return "redirect:/courses";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteCourse(@PathVariable("id") Long id) {
        Course course1 = courseService.getCourseById(id);
        courseService.deleteById(id);
        return "redirect:/courses";


    }
}

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
//        List<Company> companies = companyService.getAllCompanies();
//        model.addAttribute("companyList", companies);
//        return "/company/find-all";
//    }
//
//    @GetMapping("update/{id}")
//    public String update(@PathVariable("id") Long id, Model model) {
//        Company company = companyService.getCompanyById(id);
//        model.addAttribute("company", company);
//        return "/company/update";
//    }
//
//    @RequestMapping( value = "{id}",method = {RequestMethod.PATCH,RequestMethod.GET})
//    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
//        companyService.update(id, company);
//        return "redirect:/companies/find-all";
//    }
//
//    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET})
//    public String deleteCompany(@PathVariable("id") Long id) {
//        Company company = companyService.getCompanyById(id);
//        companyService.deleteById(id);
//        return "redirect:/companies";
//    }

