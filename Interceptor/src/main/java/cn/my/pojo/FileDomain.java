package cn.my.pojo;

import org.springframework.web.multipart.MultipartFile;

public class FileDomain {
    private String description;
    private MultipartFile myFile;

    public FileDomain() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getMyFile() {
        return myFile;
    }

    public void setMyFile(MultipartFile myFile) {
        this.myFile = myFile;
    }

    @Override
    public String toString() {
        return "FileDomain{" +
                "description='" + description + '\'' +
                ", myFile=" + myFile +
                '}';
    }
}
