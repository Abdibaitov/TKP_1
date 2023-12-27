package com.lms.service.impl;

import com.lms.model.entities.Course;
import com.lms.model.entities.Group;
import com.lms.model.entities.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public void addGroup(Long courseId, Group group){
        Course course = courseRepository.findById(courseId).get();
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        List<Group> groups =new ArrayList<>();
        groups.add(group);
        course.setGroups(groups);
        group.setCourses(courses);
        groupRepository.save(group);
    }

    public Group getGroupById(Long id){
                return groupRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found Group by id"+id));
    }

        public void update(Long groupId,Long courseId, Group group){
        Group oldGroup = getGroupById(groupId);
        oldGroup.setGroupName(group.getGroupName());
        oldGroup.setDateOfStart(group.getDateOfStart());
        oldGroup.setDateOfFinish(group.getDateOfFinish());
        oldGroup.setIsActive(group.getIsActive());
        oldGroup.setIsDeleted(group.getIsDeleted());
        Course course = courseRepository.findById(courseId).get();
        List<Group> groups = new ArrayList<>();
        groups.add(oldGroup);
        course.setGroups(groups);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        oldGroup.setCourses(courses);
        groupRepository.save(oldGroup);
    }
        public void deleteById(Long id){
        groupRepository.deleteById(id);
    }

    public List<User> getStudentByName(String name){
        return groupRepository.getStudentByName(name);
    }
}
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final GroupRepository groupRepository;
//    private final UserRepository userRepository;
//
//    public void create(Group group){
//        groupRepository.save(group);
//    }
//    public Group findById(Long id){
//        return groupRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Not found Group by id"+id));
//    }
//
//    public List<Group> findAll(){
//        return groupRepository.findAll();
//    }
//
//    public void update(Long id, Group group){
//        Group oldGroup = findById(id);
//        oldGroup.setGroupName(group.getGroupName());
//        oldGroup.setDateOfStart(group.getDateOfStart());
//        oldGroup.setDateOfFinish(group.getDateOfFinish());
//        oldGroup.setIsActive(group.getIsActive());
//        oldGroup.setIsDeleted(group.getIsDeleted());
//        groupRepository.save(oldGroup);
//    }
//
//    public void deleteById(Long id){
//        groupRepository.deleteById(id);
//    }


