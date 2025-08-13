package com.example.demo.web.exception.response;

import java.time.LocalDate;

public class MyLibraryResponse {

    private Integer id;
    private String title;
    private String author;
    private String shopName;
    private LocalDate buyDate;
    private String comments;

    public MyLibraryResponse(Integer id, String title, String author, String shopName, LocalDate buyDate,
            String comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.shopName = shopName;
        this.buyDate = buyDate;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getShopName() {
        return shopName;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public String getComments() {
        return comments;
    }

}
