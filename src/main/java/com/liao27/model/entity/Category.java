package com.liao27.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

/**
 * 游戏类别
 * Created by main on 2018/4/27.
 */
@Slf4j
@Data
@Entity
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    /**
     * 类别名称
     */
    @Column(unique = true)
    private String name;

    /**
     * 类别序列
     */
    @Column(name = "sort_index")
    private Long index;

    /**
     * 点赞列表
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    @JsonIgnore
    public List<Game> gameList = Lists.newArrayList();

    public Category() {
    }

    public Category(String name, Long index) {
        this.name = name;
        this.index = index;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        if (!id.equals(category.id)) return false;
        if (!name.equals(category.name)) return false;
        return index.equals(category.index);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + index.hashCode();
        return result;
    }
}
