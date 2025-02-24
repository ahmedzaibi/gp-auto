package com.example.demo.Repository;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByModel(RoleModel roleModel);
}
