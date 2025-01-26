package com.allianceseeds.api.services.category;

import com.allianceseeds.api.adapters.repositories.category.CategoryRepo;
import com.allianceseeds.api.adapters.repositories.product.ProductRepo;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.category.AddCategoryCommand;
import com.allianceseeds.api.domain.commands.category.DeleteCategoryByNameCommand;
import com.allianceseeds.api.domain.commands.category.GetCategoryByNameCommand;
import com.allianceseeds.api.domain.commands.category.UpdateCategoryCommand;
import com.allianceseeds.api.domain.entities.Category;
import com.allianceseeds.api.domain.entities.Product;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.UnitOfWork;
import com.allianceseeds.api.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryHandler {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    private final UnitOfWork<Category> categoryUOW;
    private final UnitOfWork<Product> productUOW;


    @Autowired
    public CategoryHandler(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.categoryUOW = new UnitOfWork<>(this.categoryRepo);
        this.productUOW = new UnitOfWork<Product>(this.productRepo);

    }

    public Transformer addCategory(Command command) {
        Category category = ((AddCategoryCommand) command).getCategory();

        List<Category> categoryList = categoryRepo.getCategoriesByStringField("name", category.getName());

        if (categoryList.isEmpty()) {
            categoryUOW.registerRepoOperation(category, UnitOfWorkInt.UnitActions.INSERT);
            categoryUOW.commit();
        }

        return new CategoryTransformer<>(true, null);
    }



    public Transformer updateCategory(Command command) {
        Category category = ((UpdateCategoryCommand) command).getCategory();

        List<Category> categoryList = categoryRepo.getCategoriesByStringField("name", category.getName());

        if (!categoryList.isEmpty()) {
            Long id = categoryList.get(0).getId();
            category.setId(id);
            categoryUOW.registerRepoOperation(category, UnitOfWorkInt.UnitActions.INSERT);
            categoryUOW.commit();
        }

        return new CategoryTransformer<>(true, null);
    }

    public Transformer deleteCategoryByName(Command command) {
        String name = ((DeleteCategoryByNameCommand) command).getName();
        List<Category> categoryList = categoryRepo.getCategoriesByStringField("name", name);
        System.out.println("in delete category handler");


        if (!categoryList.isEmpty()) {
            System.out.println("deleting " + name);

            categoryUOW.registerRepoOperation(categoryList.get(0), UnitOfWorkInt.UnitActions.DELETE);
            System.out.println("1");

            categoryUOW.commit();
            System.out.println("2");

            deleteProductsByCategory(name);
        }
        return new CategoryTransformer<>(true, null);
    }

    public Transformer getAllCategories(Command command){
        List<Category> categories = categoryRepo.getAll();
        return new CategoryTransformer<>(true, categories);
    }

    public Transformer getCategoryByName(Command command){
        String name = ((GetCategoryByNameCommand) command).getName();
        List<Category> categories = categoryRepo.getCategoriesByStringField("name", name);

        if(!categories.isEmpty()){
            return new CategoryTransformer<>(true, categories.get(0));
        }
        return new CategoryTransformer<>(false, null);
    }

    public Transformer getCategoryNames(Command command){
        List<String> categories = categoryRepo.getCategoryNames();
        return new CategoryTransformer<>(true, categories);
    }
    public Boolean deleteProductsByCategory(String category){

        List<Product> products = productRepo.getProductsByStringField("category", category);

        if(products.size() > 0){
            productUOW.registerBulkOperation(products, UnitOfWorkInt.UnitActions.DELETE);
            productUOW.commit();
        }

        return true;
    }
}
