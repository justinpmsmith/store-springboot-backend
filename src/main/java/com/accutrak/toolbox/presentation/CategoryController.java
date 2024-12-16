package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Category;
import com.accutrak.toolbox.presentation.resources.CategoryResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryResource categoryResource;

    @Autowired
    public CategoryController(CategoryResource categoryResource) {
        this.categoryResource = categoryResource;
    }

    @PostMapping("/client/addCategory")
    public Transformer addCategory(@RequestBody Category category) {
        return categoryResource.addCategory(category);
    }

    @DeleteMapping("/client/deleteCategoryByName")
    public Transformer deleteCategoryByName(@RequestParam String name) {
        return categoryResource.deleteCategoryByName(name);
    }

    @GetMapping("/client/getAllCategories")
    public Transformer getAllCategories(){
        return categoryResource.getAllCategories();
    }

    @GetMapping("/client/getCategoryNames")
    public Transformer getCategoryNames(){
        return categoryResource.getCategoryNames();
    }

    @GetMapping("/client/getCategoryByName")
    public Transformer getCategoryByName(@RequestParam String name){
        return categoryResource.getCategoryByName(name);
    }

}
