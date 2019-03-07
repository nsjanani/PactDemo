package model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by janani.sampathkumar on 08/02/2017.
 */
public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private long stock;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(String name, BigDecimal price, long stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(UUID id, String name, BigDecimal price, long stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

}
