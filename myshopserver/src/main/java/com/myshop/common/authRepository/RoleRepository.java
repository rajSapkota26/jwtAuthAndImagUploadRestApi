package com.myshop.common.authRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.common.authmodel.*;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
