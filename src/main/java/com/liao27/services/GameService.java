package com.liao27.services;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
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

    /**
     * 保存一个新的游戏
     * @param logo 游戏 logo
     * @param video 视频介绍
     * @param images 图片介绍
     * @param name 游戏名称
     * @param categoryId 所属类别
     * @param size 游戏大小
     * @param details 简介
     * @param descriptions 详细描述
     * @return 游戏实体
     * @throws BusinessException 业务异常
     * @throws IOException io异常
     */
    Game addGame(MultipartFile logo, MultipartFile video, MultipartFile[] images, String name, Long categoryId, String size, String details, String descriptions) throws BusinessException, IOException;
}
