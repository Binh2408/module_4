package org.example.shoppingcart.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Cart {
    private Map<Product,Integer> products = new HashMap<>();
    public Cart() {
    }

    public Cart(Map<Product,Integer> products) {
        this.products = products;
    }

    private boolean checkItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        if (!checkItemInCart(product)){
            products.put(product,1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public void decreaseProduct(Product product) {
        if(checkItemInCart(product)) {
            Map.Entry<Product,Integer> itemEntry = selectItemInCart(product);
            Integer currentQuantity = itemEntry.getValue();
            if(currentQuantity <= 1) {
                products.remove(itemEntry.getKey());
            } else {
                products.replace(itemEntry.getKey(),currentQuantity-1);
            }
        }
    }

    public Integer countProductQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity(){
        return products.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }
}
