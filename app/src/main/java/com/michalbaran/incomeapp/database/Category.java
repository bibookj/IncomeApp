package com.michalbaran.incomeapp.database;

import com.reactiveandroid.Model;
import com.reactiveandroid.annotation.Column;
import com.reactiveandroid.annotation.PrimaryKey;
import com.reactiveandroid.annotation.Table;

@Table (name = "Categories", database = AppDatabase.class)

public class Category extends Model {

    @PrimaryKey private Long id;

    @Column
    private String category;

    public Category(){

    }
    public Category(String category){
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }
}
