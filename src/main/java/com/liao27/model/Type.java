package com.liao27.model;

import lombok.Getter;

/**
 * 评论类型
 * Created by main on 2017/10/10.
 */
@Getter
public enum Type {

    USER("USER", "用户评论"),
    GROUP("GROUP", "部落评论");

    private String value;
    private String desc;

    Type(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Type get(String strValue) {
        for (Type e : values()) {
            if (e.getValue().equals(strValue)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
