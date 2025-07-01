package org.example.cart.controller;

import org.example.cart.model.Cart;
import org.example.cart.model.Product;
import org.example.cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cart")

public class CartController {
    private final IProductService productService;
    @Autowired
    public CartController(IProductService productService) {
        this.productService = productService;
    }
    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute("cart") Cart cart,
                            RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        cart.increaseQuantityInCart(product);
        redirectAttributes.addFlashAttribute("message", "Đã thêm vào giỏ hàng!");
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cartItems", cart.getProducts());
        model.addAttribute("totalQuantity", cart.countProductQuantity());
        model.addAttribute("totalPrice", cart.countTotalPayment());
        return "cart";
    }

    @GetMapping("/cart/increase/{id}")
    public String increaseProduct(@PathVariable Long id,
                                  @ModelAttribute("cart") Cart cart) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        cart.increaseQuantityInCart(product);
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease/{id}")
    public String decreaseProduct(@PathVariable Long id,
                                  @ModelAttribute("cart") Cart cart) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        cart.decreaseProduct(product);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        cart.removeProduct(product);
        return "redirect:/cart";
    }

}
