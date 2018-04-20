package com.dygstudio.microservice.entity;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends BaseEntity<String> {
    private List<Table> tables = new ArrayList<>();

    public Restaurant(String name,String id,List<Table> tables){
        super(id,name);
        this.tables = tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }
}
