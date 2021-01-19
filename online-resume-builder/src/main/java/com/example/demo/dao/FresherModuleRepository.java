package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.FresherInfo;

public interface FresherModuleRepository extends CrudRepository<FresherInfo, Long> {

}
