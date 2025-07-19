package com.example.demo.persistence.entity;

public class User {
    private Integer id;
    private String login_id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLoginId() {
        return login_id;
    }
    public void setLoginId(String login_id) {
        this.login_id = login_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", login_id=").append(login_id);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }

    


}
