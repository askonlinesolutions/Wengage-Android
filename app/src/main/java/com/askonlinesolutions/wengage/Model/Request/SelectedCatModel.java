package com.askonlinesolutions.wengage.Model.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakhi on 11/26/2018.
 */
public class SelectedCatModel {
    private int catId;
    private List<Integer> subCatId;

    public SelectedCatModel(int catId, List<Integer> subCatId) {
        this.catId = catId;
        this.subCatId = subCatId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public List<Integer> getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(List<Integer> subCatId) {
        this.subCatId = subCatId;
    }
}
