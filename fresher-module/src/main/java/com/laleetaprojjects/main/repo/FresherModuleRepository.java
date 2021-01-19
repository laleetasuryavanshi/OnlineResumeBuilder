package com.laleetaprojjects.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.laleetaprojjects.main.entity.FresherInfo;

public interface FresherModuleRepository extends CrudRepository<FresherInfo, Long> {

}
