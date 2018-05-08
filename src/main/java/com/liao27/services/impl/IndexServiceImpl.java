package com.liao27.services.impl;

import com.liao27.model.entity.Index;
import com.liao27.repositories.IndexRepository;
import com.liao27.services.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by main on 2018/5/8.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Override
    public Index getIndex() {
        List<Index> list =  indexRepository.findAll();
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
