/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.oauth2service.respository;

import com.kt3.oauth2service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 97lynk
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

     Role findByName(String name);
}
