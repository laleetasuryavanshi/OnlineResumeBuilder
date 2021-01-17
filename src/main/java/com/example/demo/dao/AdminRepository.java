package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.example.demo.entities.Admin;

@Component
@Repository
public interface AdminRepository extends CrudRepository<Admin,Long>{
  

}
