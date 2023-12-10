package com.myproject.onlinelearningsystem.service;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Student;
import com.myproject.onlinelearningsystem.repository.AdminRepository;
import com.myproject.onlinelearningsystem.repository.StaffRepository;
import com.myproject.onlinelearningsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

@Autowired
    private StudentRepository studentRepository;
@Autowired
    private AdminRepository adminRepository;
@Autowired
    private StaffRepository staffRepository;


    public String login_admin(String id, String password) {
        Optional<Admin> admin = adminRepository.findAdminByIdAndPassword(id, password);
        if(admin.orElse(null) == null){
            return null;
        }
        return admin.orElse(null).getId();
    }
    public String login_staff(String id, String password) {
        Optional<Staff> staff = staffRepository.findStaffByIdAndPassword(id, password);
        if(staff.orElse(null) == null){
            return null;
        }
        return staff.orElse(null).getId();
    }
    public String login_student(String id, String password) {
        Optional<Student> student = studentRepository.findStudentByIdAndPassword(id, password);
        if(student.isPresent()){
            if (student.get().checked){
                return student.orElse(null).getId();
            }
        }
        return null;
    }

    public void logout(String id) {}
    // 更新、删除等方法
}
