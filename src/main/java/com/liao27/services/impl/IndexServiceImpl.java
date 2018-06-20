package com.liao27.services.impl;

import com.google.common.collect.Sets;
import com.liao27.model.dto.GameBean;
import com.liao27.model.dto.IndexBean;
import com.liao27.model.entity.Game;
import com.liao27.model.entity.Index;
import com.liao27.repositories.CategoryRepository;
import com.liao27.repositories.GameRepository;
import com.liao27.repositories.IndexRepository;
import com.liao27.services.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by main on 2018/5/8.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public IndexBean getIndex() {
        List<Index> list = indexRepository.findAll();
        if (list.size() > 0) {
            IndexBean indexBean = IndexBean.build(list.get(0));
            return indexBean;
        }
        return new IndexBean();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public IndexBean config(IndexBean indexBean) {
        Index index = new Index();
        index.setId(indexBean.getId());
        Example<Index> example = Example.of(index);

        Index entity = indexRepository.findOne(example).orElse(null);
        if (entity == null){
            entity = new Index();
            indexRepository.saveAndFlush(entity);
        }

//        if (entity.getId() > 0){
//            entity.setGameList1(this.findGamesByList(indexBean.getGameList1(),entity,1));
//            entity.setGameList2(this.findGamesByList(indexBean.getGameList2(),entity,2));
//            indexRepository.saveAndFlush(entity);
//        }

        return IndexBean.build(entity);
    }

//    private Set<Game> findGamesByList(List<GameBean> list,Index refIndex,int index){
//        Set sets = Sets.newHashSet();
//        for (GameBean gb : list) {
//            if (gb.getId() != null && gb.getId() > 0){
//                sets.add(gb.getId());
//            }
//        }
//        if (sets.size() > 0){
//            List<Game> lists = gameRepository.findAllById(sets);
//            for (Game game : lists) {
//                switch (index){
//                    case 1:
//                        game.setIndex1(refIndex);
//                        break;
//                    case 2:
//                        game.setIndex2(refIndex);
//                        break;
//                }
//            }
//            if (lists.size() > 0) {
//                return Sets.newHashSet(lists);
//            }
//        }
//        return null;
//    }
}
