package com.liao27.repositories;

import com.liao27.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by main on 2018/4/27.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findFirstByName(String name);

}
