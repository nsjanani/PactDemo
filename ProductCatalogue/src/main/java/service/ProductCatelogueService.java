package service;

import model.Product;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by janani.sampathkumar on 08/02/2017.
 */
public class ProductCatelogueService {

    private Map<UUID, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(UUID id) {
        return products.get(id);
    }

    public Product createProduct(String name, BigDecimal price, Long stock) {
        validate(name,price,stock);
        Product product = new Product(name,price, stock);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(UUID id, String name, BigDecimal price, Long stock) {
        Product product = products.get(id);
        if(product == null)
            throw new IllegalArgumentException("No product with id '" + id + "'found");
        validate(name, price, stock);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return product;
    }

    private void validate(String name, BigDecimal price, Long stock) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Product parameter 'name' cannot be empty");
        if(price == null)
            throw new IllegalArgumentException("Product paratmer 'price' cannot be empty");
        if(stock == null)
            throw new IllegalArgumentException("Product paratmer 'stock' cannot be empty");
    }
}


