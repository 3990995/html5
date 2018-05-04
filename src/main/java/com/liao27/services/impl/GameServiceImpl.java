package com.liao27.services.impl;

import com.google.common.collect.Lists;
import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameBean;
import com.liao27.model.entity.Game;
import com.liao27.repositories.GameRepository;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by main on 2018/5/4.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game addGame(Game game) throws BusinessException {
        if (gameRepository.findFirstByName(game.getName())
                != null) {
            throw new BusinessException("游戏已经存在");
        }
        return gameRepository.save(game);
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
