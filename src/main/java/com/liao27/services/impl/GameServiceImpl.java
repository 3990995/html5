package com.liao27.services.impl;

import com.google.common.collect.Lists;
import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
import com.liao27.model.entity.Category;
import com.liao27.model.entity.Game;
import com.liao27.repositories.CategoryRepository;
import com.liao27.repositories.GameRepository;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.UUID;


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

    private String saveFile(MultipartFile file) throws IOException {
        String ofn = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + ofn.substring(ofn.lastIndexOf("."));
        file.transferTo(new File(this.uploadLocation + fileName));
        return fileName;
    }

    @Override
    @Transactional
    public Game addGame(MultipartFile logo, MultipartFile video, MultipartFile[] images, String name, Long categoryId, String size, String details, String descriptions) throws BusinessException, IOException {

        if (gameRepository.findFirstByName(name)
                != null) {
            throw new BusinessException("游戏已经存在");
        }

        Game game = new Game();
        game.setName(name);
        game.setDetails(details);
        game.setDescriptions(descriptions);
        game.setSize(size);

        if (logo != null) {
            game.setLogo(this.saveFile(logo));
        }

        if (video != null) {
            game.setVideo(this.saveFile(video));
        }

        if (null != images && images.length > 0) {
            List<String> files = Lists.newArrayList();
            for (MultipartFile file : images) {
                files.add(this.saveFile(file));
            }
            if (files.size() > 0){
                game.setImages(files);
            }
        }
        if (categoryId != null && categoryId > 0){
            Category category = categoryRepository.getOne(categoryId);
            if (category != null){
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
        List<Game> list = gameRepository.findAll();
        return copyProperties(list);
    }

    public List<GameBean> findAllByCategoryId(Long categoryId) {
        List<Game> list = gameRepository.findAllByCategoryId(categoryId);
        return copyProperties(list);
    }

    private List<GameBean> copyProperties(List<Game> games) {
        List<GameBean> beans = Lists.newArrayList();
        for (Game game : games) {
            GameBean bean = new GameBean();
            BeanUtils.copyProperties(game, bean);
            beans.add(bean);
        }
        return beans;
    }
}
