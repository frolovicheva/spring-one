package ru.geekbrains.spring.one.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.one.model.Product;
import ru.geekbrains.spring.one.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String showAllProductsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}")
    public String showProductInfo(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.findOneById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "product_info_by_id";
    }

//    @GetMapping("/{id}")
//    @ResponseBody
//    public Product showProductByID(@PathVariable(name = "id") Long id, Model model) {
//        return productService.findOneById (id).get ();
//    }

    @GetMapping("/create")
    public String showCreator() {
        return "create_product_form";
    }

    @PostMapping("/create")
    public String createNewProduct(@RequestParam Long id, @RequestParam String name, @RequestParam float price) {
        Product product = new Product (id, name, price);
        productService.save(product);
        return "redirect:/products/all";
    }
}
