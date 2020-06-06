package com.hxk.estest.eslasttest.bean;
/*
 * @Author huangxk
 * @Description：
 * @Date
 **/

public class Persion {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tests [id=" + id + ", name=" + name + "]";
    }

}