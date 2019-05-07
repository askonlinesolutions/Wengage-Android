package com.askonlinesolutions.wengage.wengageInterface;

public interface CategoryItemClickListner {
    public void onCategoryItemClick(int catId);
    public void onSubCategoryItemClick(int catId,int subCatId,boolean isSelected);
    public void onSubCategoryItemClickWithName(String catName,String subCatName,boolean isSelected);
}
