package model;

import scala.Int;

import java.io.Serializable;

/**
 * Created by Hale on 23.04.2017.
 */
public class PersistModel implements Serializable {

    private String name;
    private Integer age;

    public PersistModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
