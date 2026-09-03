package com.example.Course.DTO;

import java.util.List;

public class PageResponse<T>{
    private List<T> items;
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;
    private boolean isLast;

    public PageResponse(List<T> items, int page, int size, int totalItems, int totalPages, boolean isLast) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public PageResponse() {}
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public int getPage() {return page;}
    public void setPage(int page) {this.page = page;}
    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
    public long getTotalItems(long totalElements) {return totalItems;}
    public void setTotalItems(long totalItems) {this.totalItems = totalItems;}
    public int getTotalPages() {return totalPages;}
    public void setTotalPages(int totalPages) {this.totalPages = totalPages;}
    public boolean isLast() {return isLast;}
    public void setLast(boolean last) {isLast = last;}
}
