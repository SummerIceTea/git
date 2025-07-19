package com.example.demo.response;

public class LoginResponse {
    private Integer id;
    private String loginId;
    private String name;

   
    public LoginResponse(Integer id, String loginId, String name) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
    }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
