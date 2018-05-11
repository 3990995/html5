package com.liao27.repositories;

import com.liao27.model.dto.IndexBean;
import com.liao27.model.entity.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by main on 2018/5/8.
 */
@Repository
public interface IndexRepository extends JpaRepository<Index,Long> {

}
