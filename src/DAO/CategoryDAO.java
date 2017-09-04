/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Category;
import Models.Device;
import java.util.List;

/**
 *
 * @author GerardoAGL
 */
public class CategoryDAO {
    
    private Category category;
    
    public CategoryDAO(Category categ){
       
        this.category = categ;
        this.category.setId(size() + 1);
    }
        
    public void save(){
        
        Connection db = new Connection();
        db.saveCategory(this.category);
        db.close();
    }
    
    public static void update(Category category){
        Connection db = new Connection();
        db.updateCategory(category);
        db.close();
    }
    
    public static Category searchById(int id){
        Connection db = new Connection();
        Category category = db.categoryById(id);
        db.close();
        
        return category;
    }
    
    public static int availableSize(){
        Connection db = new Connection();
        int size = db.availableCategorySize();
        db.close();
        
        return size;
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.categorySize();
        db.close();
        
        return size;
    }
    
    public static Category[] all(){
        Connection db = new Connection();
        Category categories[] = db.allCategories();
        db.close();
        
        return categories;
    }
        
}
