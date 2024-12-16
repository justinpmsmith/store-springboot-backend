package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Product;
import com.accutrak.toolbox.presentation.resources.ProductResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    private ProductResource productResource;

    @Autowired
    public ProductController(ProductResource productResource) {
        this.productResource = productResource;
    }

    @PostMapping("/client/addProduct")
    public Transformer addLog(@RequestBody Product product){
        return productResource.addProduct(product);

    }

    @DeleteMapping("/client/deleteProductByProdCode")
    public Transformer deleteProductByProdCode(@RequestParam String prodCode){
        return productResource.deleteProductByProdCode(prodCode);
    }

    @DeleteMapping("/client/deleteProductsByCategory")
    public Transformer deleteProductsByCategory(@RequestParam String category){
        return productResource.deleteProductByCategory(category);
    }

    @PostMapping("/client/updateProduct")
    public Transformer updateProduct(@RequestBody Product product){
        return productResource.updateProduct(product);
    }

    @GetMapping("/client/getProductsByCategory")
    public Transformer getProductsByCategory(@RequestParam String category){

        return productResource.getProductsByCategory(category);
    }

    @GetMapping("/client/getAllProducts")
    public Transformer getAllProducts(){
        return productResource.getAllProducts();
    }


}
