package com.jiangjian.study.spring.domain;

public enum ExpanseType {
    FOOD("食物"),
    ENTERTIMENT("娱乐"),
    ELECTRIC("电费"),
    GAS("煤气"),
    WATER("水费"),
    DRINK("饮料");

    private final String description;

    ExpanseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
