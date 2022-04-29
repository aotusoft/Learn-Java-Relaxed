package cn.china;

public class car {
    private String brand;
    private String seats;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String out() {
        return "car{" + "brand='" + brand + '\'' + ", seats='" + seats + '\'' + '}';
    }
}
