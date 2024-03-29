package com.jeesite.modules.texcel.entity;

public class Header {
    private String name;
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Header{" +
                "name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }
}
