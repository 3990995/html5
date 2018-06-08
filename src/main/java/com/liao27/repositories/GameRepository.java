package com.liao27.repositories;

import com.liao27.model.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by main on 2018/5/4.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * 查找指定分类的前4条游戏记录并且不包含指定游戏 id 的记录
     *
     * @param categoryId 分类 id
     * @param gameId     游戏 id
     * @return 游戏列表
     */
    List<Game> findTop4ByCategoryIdAndIdNotIn(Long categoryId, Long gameId);

    @EntityGraph(value = "graph.Game.images", type = EntityGraph.EntityGraphType.FETCH)
    Game getOne(Long id);

    /**
     * 根据名字查找游戏
     *
     * @param name 游戏名
     * @return 游戏实体
     */
    @EntityGraph(value = "graph.Game.images", type = EntityGraph.EntityGraphType.FETCH)
    Game findFirstByName(String name);


    /**
     * 根据类别查找游戏列表
     *
     * @param categoryId
     * @return 列表
     */
    @EntityGraph(value = "graph.Game.images", type = EntityGraph.EntityGraphType.FETCH)
    List<Game> findAllByCategoryId(Long categoryId);

    /**
     * 游戏分页
     *
     * @param pageable 分页
     * @return 列表
     */
    @EntityGraph(value = "graph.Game.images", type = EntityGraph.EntityGraphType.FETCH)
    Page<Game> findAll(Pageable pageable);

    /**
     * 根据游戏名字模糊查询
     * @param keyword 游戏名字
     * @return 游戏列表
     */
    List<Game> findByNameContaining(String keyword);
}