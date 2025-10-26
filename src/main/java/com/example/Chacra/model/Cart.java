package com.example.Chacra.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(newItem.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public BigDecimal getGrandTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}