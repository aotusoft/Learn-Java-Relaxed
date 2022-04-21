package com.my.pojo;


public class Usertable {

  private long id;
  private String username;
  private String password;
  private String createuser;
  private java.sql.Date createdate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getCreateuser() {
    return createuser;
  }

  public void setCreateuser(String createuser) {
    this.createuser = createuser;
  }


  public java.sql.Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(java.sql.Date createdate) {
    this.createdate = createdate;
  }

}
