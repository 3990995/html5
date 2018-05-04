package com.liao27.services;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
import com.liao27.model.entity.Game;

import java.util.List;

/**
 * Created by main on 2018/5/4.
 */
public interface GameService {

    /**
     * 保存一个新的 游戏
     * @param game 游戏
     * @return 游戏
     */
    Game addGame(Game game) throws BusinessException;

    /**
     * 删除 游戏
     * @param id 游戏 id
     * @return 删除是否成功
     */
    boolean remove(Long id) throws BusinessException;

    /**
     * 查找所有游戏数据
     * @return 游戏列表
     */
    List<GameBean> findAll();

    /**
     * 根据类别查找游戏列表
     * @param categoryId 类别 id
     * @return 游戏列表
     */
    List<GameBean> findAllByCategoryId(Long categoryId);
}
