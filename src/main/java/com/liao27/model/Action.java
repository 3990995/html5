package com.liao27.model;

import lombok.Getter;

/**
 * 操作枚举
 * Created by main on 2017/10/10.
 */
@Getter
public enum Action {

    ADD("ADD", "添加"),
    DELETE("DELETE", "删除");

    private String value;
    private String desc;

    Action(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Action get(String strValue) {
        for (Action e : values()) {
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
