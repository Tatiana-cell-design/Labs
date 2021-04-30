package ru.luxoft.cources;

public class Jeans {
    private String model;
    private String size;
    private Float price;
    private Integer count;

    public Jeans() {
    }

    public Jeans(String model, String size, Float price, Integer count) {
        this.model = model;
        this.size = size;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Jeans{" +
                "model='" + model + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
