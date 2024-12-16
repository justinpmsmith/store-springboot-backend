package com.accutrak.toolbox.services.category;

import com.accutrak.toolbox.adapters.repositories.category.CategoryRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.category.AddCategoryCommand;
import com.accutrak.toolbox.domain.commands.category.CategoryCommand;
import com.accutrak.toolbox.domain.commands.category.DeleteCategoryByNameCommand;
import com.accutrak.toolbox.domain.commands.category.GetCategoryByNameCommand;
import com.accutrak.toolbox.domain.entities.Category;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryHandler {

    private final CategoryRepo categoryRepo;
    private final UnitOfWork<Category> categoryUOW;

    @Autowired
    public CategoryHandler(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.categoryUOW = new UnitOfWork<>(this.categoryRepo);
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

    public Transformer deleteCategoryByName(Command command) {
        String name = ((DeleteCategoryByNameCommand) command).getName();
        List<Category> categoryList = categoryRepo.getCategoriesByStringField("name", name);

        if (!categoryList.isEmpty()) {
            categoryUOW.registerRepoOperation(categoryList.get(0), UnitOfWorkInt.UnitActions.DELETE);
            categoryUOW.commit();
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
//    getCategoryNames
}
