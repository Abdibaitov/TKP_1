package com.lms.service.impl;

import com.lms.model.entities.Company;
import com.lms.model.entities.Course;
import com.lms.repository.CompanyRepository;
import com.lms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final CompanyRepository companyRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public void addCourse(Long companyId, Course course) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Not found Company by id" + companyId));
        course.setCompany(company);
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Course by id" + id));
    }

    public void updateCourse(Long companyId, Long courseId, Course course){
        Course oldCourse = getCourseById(courseId);
        oldCourse.setCourseName(course.getCourseName());
        oldCourse.setDurationMonth(course.getDurationMonth());
        oldCourse.setLocalDate(course.getLocalDate());
        oldCourse.setIsActive(course.getIsActive());
        oldCourse.setIsDeleted(course.getIsDeleted());
        Company company = companyRepository.findById(companyId).get();
        List<Course> courses = new ArrayList<>();
        courses.add(oldCourse);
        company.setCourses(courses);
        oldCourse.setCompany(company);
        courseRepository.save(oldCourse);
    }

        public void deleteById(Long id){
        courseRepository.deleteById(id);
    }


}
//    private final CourseRepository courseRepository;
//
//    public void create(Course course){
//        courseRepository.save(course);
//    }
//    public Course findById(Long id){
//        return courseRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Not found Course by id"+id));
//
//    }
//    public List<Course> findAll(){
//        return courseRepository.findAll();
//    }
//    public void update(Long id,Course course){
//        Course oldCourse = findById(id);
//        oldCourse.setCourseName(course.getCourseName());
//        oldCourse.setDurationMonth(course.getDurationMonth());
//        oldCourse.setLocalDate(course.getLocalDate());
//        oldCourse.setIsActive(course.getIsActive());
//        oldCourse.setIsDeleted(course.getIsDeleted());
//        courseRepository.save(oldCourse);
//    }
//    public void deleteById(Long id){
//        courseRepository.deleteById(id);
//    }

