package com.myproject.onlinelearningsystem.repository;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findById(String id);

    Optional<Admin> findAdminByIdAndPassword(String id, String password);
    // 可以添加自定义查询方法
}
