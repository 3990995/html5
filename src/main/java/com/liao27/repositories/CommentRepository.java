package com.liao27.repositories;

import com.liao27.model.Type;
import com.liao27.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by main on 2018/5/14.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByGameIdAndType(Long gameId,Type type);

}
