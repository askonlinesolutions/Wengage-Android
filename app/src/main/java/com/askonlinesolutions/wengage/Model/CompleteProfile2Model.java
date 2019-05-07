package com.askonlinesolutions.wengage.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompleteProfile2Model implements Serializable{

    public int status;
    public int categoryId;
    public List<Integer> subcategoryId_list;

    public HashMap<Integer,List<Integer>> data_map = new HashMap<>();

    public List<String> arrayList = new ArrayList<>();

    public HashMap<String,List<String>> data_map_with_name= new HashMap<>();

    public HashMap<String, List<String>> getData_map_with_name() {
        return data_map_with_name;
    }

    public void setData_map_with_name(HashMap<String, List<String>> data_map_with_name) {
        this.data_map_with_name = data_map_with_name;
    }

    public HashMap<Integer, List<Integer>> getData_map() {
        return data_map;
    }

    public void setData_map(HashMap<Integer, List<Integer>> data_map) {
        this.data_map = data_map;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getSubcategoryId_list() {
        return subcategoryId_list;
    }

    public void setSubcategoryId_list(List<Integer> subcategoryId_list) {
        this.subcategoryId_list = subcategoryId_list;
    }
}
