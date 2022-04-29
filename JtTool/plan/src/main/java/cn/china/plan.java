package cn.china;

public class plan {
    private String height;
    private String voyage;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String out() {
        return "plan{" + "height='" + height + '\'' + ", voyage='" + voyage + '\'' + '}';
    }
}
