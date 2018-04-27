package com.liao27.repositories;

import com.liao27.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by main on 2018/4/27.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


}
