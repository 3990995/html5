package com.liao27.model.dto;

import com.google.common.collect.Lists;
import com.liao27.model.entity.Category;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

/**
 * 首页内容
 * Created by main on 2018/5/8.
 */
@Slf4j
@Data
public class IndexBean {

    private Long id;

    private CategoryBean category1;

    private CategoryBean category2;

    private CategoryBean category3;

    private CategoryBean category4;

    /**
     * 首页第一部分的游戏列表
     */
    private List<GameBean> gameList1 = Lists.newArrayList();

    /**
     * 首页第2部分的游戏列表[精品推荐]
     */
    private List<GameBean> gameList2 = Lists.newArrayList();

}
