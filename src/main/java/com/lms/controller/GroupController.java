package com.lms.controller;

import com.lms.model.entities.Course;
import com.lms.model.entities.Group;
import com.lms.service.impl.CourseService;
import com.lms.service.impl.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;

    @ModelAttribute("courseList")
    public List<Course> getCourseList() {
        return courseService.getAllCourse();

    }

//    @ModelAttribute("studentList")
//    public List<Student> getStudents() {
//        return studentService.getAllStudents();
//    }

    @GetMapping
    public String getAllCourses(Model model) {

        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups/group";
    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "groups/addGroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.addGroup(group.getCourseId(), group);
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("updateGroup", group);
        return "groups/updateGroup";

    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String saveUpdateGroup(@PathVariable("id") Long id, @ModelAttribute("groups") Group group) {
        groupService.update(id, group.getCourseId(), group);
        return "redirect:/groups";

    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteGroup(@PathVariable("id") Long id) {
        System.out.println("ID: " + id);
        Group group = groupService.getGroupById(id);
        groupService.deleteById(id);
        System.out.println("ID:: " + id);
        return "redirect:/groups";

    }

//    @GetMapping("/students/{teacherId}")
//    public String getStudentByCompanyId(@PathVariable("teacherId") Long teacherId, Model model) {
//        List<Student> studentList = studentService.getStudentByCompany(teacherId);
//        model.addAttribute("studentList", studentList);
//        model.addAttribute("count", studentList.size());
//        return "teacher/students";
//
//    }
//    @GetMapping("/search")
//    public String getStudentName(Model model, String name){
//        List<Student> students = studentService.getStudentByName(name);
//        List<Student> studentList = studentService.getAllStudents();
//        if(name != null){
//            model.addAttribute("students", students);
//        }
//        else{
//            model.addAttribute("students", studentList);
//        }
//        return "groups/getStudent";
//    }
}
