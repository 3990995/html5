package com.liao27.model.dto;

import com.liao27.model.entity.Category;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Data
public class CategoryBean {

    private Long id;

    private String name;

    private Long index;

    public CategoryBean(){}

    public CategoryBean(Long categoryId) {
        this.id = categoryId;
    }

    public static CategoryBean build(Category category) {
        if (category == null) {
            return null;
        }
        CategoryBean categoryBean = new CategoryBean();
        BeanUtils.copyProperties(category, categoryBean);

        return categoryBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CategoryBean that = (CategoryBean) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
