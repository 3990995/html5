package com.liao27.model.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

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
    @Column
    private int index;

    public Category() {
    }

    public Category(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        if (index != category.index) return false;
        if (!id.equals(category.id)) return false;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + index;
        return result;
    }
}
