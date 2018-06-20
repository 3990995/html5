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
     * 首页第一部分的游戏列表
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_index_game1",joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "lists")
    private Set<Game> gameList1 = Sets.newHashSet();

    /**
     * 首页第2部分的游戏列表[精品推荐]
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_index_game2",joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "lists")
    private Set<Game> gameList2 = Sets.newHashSet();

}
