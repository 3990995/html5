package com.liao27.model.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

/**
 * 首页内容
 * Created by main on 2018/5/8.
 */
@Slf4j
@Data
@Entity
@Table(name = "t_index")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id", nullable = false)
    private Long id;

    /**
     * 首页顶部的4个类别
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category1_id")
    private Category category1;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category2_id")
    private Category category2;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category3_id")
    private Category category3;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category4_id")
    private Category category4;

    /**
     * 首页第一部分的游戏列表
     */
    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL,mappedBy = "index1")
    private Set<Game> gameList1;

    /**
     * 首页第2部分的游戏列表[精品推荐]
     */
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "index2")
    private Set<Game> gameList2 = Sets.newHashSet();

}
