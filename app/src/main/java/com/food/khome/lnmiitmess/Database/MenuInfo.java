package com.food.khome.lnmiitmess.Database;

/**
 * Created by khome on 5/2/16.
 */
public class MenuInfo {
    public String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String title;
    public String detail;
}
