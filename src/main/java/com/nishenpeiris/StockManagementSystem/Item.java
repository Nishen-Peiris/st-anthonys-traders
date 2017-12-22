package com.nishenpeiris.StockManagementSystem;

import javax.persistence.Entity;

@Entity
public class Item extends AbstractEntity {
    private String name;
    private String code;
    private String description;

    public Item(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    protected Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
