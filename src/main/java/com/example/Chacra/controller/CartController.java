package com.example.Chacra.controller;

import com.example.Chacra.model.Cart;
import com.example.Chacra.model.CartItem;
import com.example.Chacra.model.Product;
import com.example.Chacra.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;

    // Crea un nuevo carrito si no existe uno en la sesión
    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity,
                            @ModelAttribute("cart") Cart cart) {

        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no válido: " + productId));

        cart.addItem(new CartItem(product, quantity));

        return "redirect:/carrito";
    }

    @GetMapping("/carrito")
    public String showCart() {
        return "carrito";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId, @ModelAttribute("cart") Cart cart) {
        cart.removeItem(productId);
        return "redirect:/carrito";
    }
}