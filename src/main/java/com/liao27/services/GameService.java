package com.liao27.services;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
import com.liao27.model.dto.GameReq;
import com.liao27.model.entity.Game;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by main on 2018/5/4.
 */
public interface GameService {

    /**
     * 删除 游戏
     *
     * @param id 游戏 id
     * @return 删除是否成功
     */
    boolean remove(Long id) throws BusinessException;

    /**
     * 查找所有游戏数据
     *
     * @return 游戏列表
     */
    List<GameBean> findAll();

    /**
     * 根据类别查找游戏列表
     *
     * @param categoryId 类别 id
     * @return 游戏列表
     */
    List<GameBean> findAllByCategoryId(Long categoryId);

    /**
     * 游戏推荐默认4条记录
     * @param categoryId 指定分类下的
     * @param excludeGameId 不包含指定的游戏
     * @return 游戏列表
     */
    List<GameBean> recommend(Long categoryId, Long excludeGameId);

    /**
     * 保存一个新的游戏
     *
     * @param logo     游戏 logo
     * @param video    视频介绍
     * @param images   图片介绍
     * @param gameBean 游戏提交数据
     * @return 游戏实体
     * @throws BusinessException 业务异常
     * @throws IOException       io异常
     */
    Game addGame(MultipartFile logo, MultipartFile video, MultipartFile[] images, GameBean gameBean) throws BusinessException, IOException;

    /**
     * 根据 id 查找游戏
     *
     * @param gameId 游戏 id
     * @return 游戏数据
     */
    GameBean getGame(Long gameId);
}
