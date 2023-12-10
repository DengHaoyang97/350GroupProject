package com.myproject.onlinelearningsystem.repository;

import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends MongoRepository<Staff, String> {
    // 通过 ID 查找学生
    Optional<Staff> findById(String id);
    List<Staff> findByName(String name);

    // 根据电子邮件查找用户
    Staff findByEmail(String email);
    Optional<Staff> findStaffByIdAndPassword(String id, String password);
    // 根据需要添加更多自定义查询方法
}
