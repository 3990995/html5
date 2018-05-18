package com.liao27.services.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
import com.liao27.model.entity.Category;
import com.liao27.model.entity.Game;
import com.liao27.repositories.CategoryRepository;
import com.liao27.repositories.GameRepository;
import com.liao27.services.GameService;
import com.liao27.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by main on 2018/5/4.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    /**
     * 存储路径
     */
    @Value("${upload.location}")
    private String uploadLocation;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Game addGame(MultipartFile logo, MultipartFile video, MultipartFile[] images, GameBean gameBean) throws BusinessException, IOException {
        if (gameBean == null) {
            throw new IllegalArgumentException("数据不完整");
        }
        if (gameRepository.findFirstByName(gameBean.getName())
                != null) {
            throw new BusinessException("游戏已经存在");
        }

        Game game = new Game();
        BeanUtils.copyProperties(gameBean, game);

        if (logo != null && Strings.isNotEmpty(logo.getOriginalFilename())) {
            game.setLogo(FileUtil.saveFile(this.uploadLocation, logo));
        }

        if (video != null && Strings.isNotEmpty(video.getOriginalFilename())) {
            game.setVideo(FileUtil.saveFile(this.uploadLocation, video));
        }

        if (null != images && images.length > 0) {
            Set<String> files = Sets.newHashSet();
            for (MultipartFile file : images) {
                if (Strings.isEmpty(file.getOriginalFilename())) {
                    continue;
                }
                files.add(FileUtil.saveFile(this.uploadLocation, file));
            }
            if (files.size() > 0) {
                game.setImages(files);
            }
        }
        if (gameBean.getCategory() != null && gameBean.getCategory().getId() > 0) {
            Category category = categoryRepository.getOne(gameBean.getCategory().getId());
            if (category != null) {
                game.setCategory(category);
            }
        }

        gameRepository.save(game);
        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Long id) throws BusinessException {
        if (id != null && id > 0) {
            try {
                gameRepository.deleteById(id);
                return true;
            } catch (EmptyResultDataAccessException e) {
                throw new BusinessException("记录不存在");
            }
        } else {
            throw new IllegalArgumentException("id 参数无效");
        }
    }

    @Override
    public List<GameBean> findAll() {
        return GameBean.builds(gameRepository.findAll());
    }

    @Override
    public List<GameBean> findAllByCategoryId(Long categoryId) {
        return GameBean.builds(gameRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public List<GameBean> recommend(Long categoryId, Long excludeGameId) {
        return GameBean.builds(gameRepository.findTop4ByCategoryIdAndIdNotIn(categoryId, excludeGameId));
    }

    @Override
    public GameBean getGame(Long gameId) {
        return GameBean.build(gameRepository.getOne(gameId));
    }
}
