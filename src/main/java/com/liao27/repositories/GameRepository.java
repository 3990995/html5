package com.liao27.repositories;

import com.liao27.model.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by main on 2018/5/4.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * 根据名字查找游戏
     *
     * @param name 游戏名
     * @return 游戏实体
     */
    Game findFirstByName(String name);

    /**
     * 根据类别查找游戏列表
     *
     * @param categoryId
     * @return 列表
     */
    List<Game> findAllByCategoryId(Long categoryId);

    /**
     * 游戏分页
     *
     * @param pageable 分页
     * @return 列表
     */
    @EntityGraph(value = "graph.Game.images", type = EntityGraph.EntityGraphType.FETCH)
    Page<Game> findAll(Pageable pageable);

}