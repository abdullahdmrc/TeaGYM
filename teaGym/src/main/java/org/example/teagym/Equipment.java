package org.example.teagym;
import java.util.ArrayList;

public class Equipment {
    String name;
    ArrayList<Category> categories = new ArrayList<>();
    int quantity;
    public Equipment(String name, Category category, int quantity)
    {
        this.name = name;
        categories.add(category);
        this.quantity = quantity;
    }
    public Equipment(String name, String[] categs, int quantity)
    {
        this.name = name;
        for(String categ : categs)
            addCategory(categ);
        this.quantity = quantity;
    }
    public Equipment(String name, String[] categs)
    {
        this.name = name;
        for(String categ : categs)
            addCategory(categ);
        quantity = 1;
    }
    public Equipment(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public Equipment(String name, ArrayList<Category> categories)
    {
        this.name = name;
        this.categories = categories;
    }
    public void addQuantity(int quantity)
    {
        this.quantity = this.quantity + quantity;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Category> getCategories()
    {
        return categories;
    }

    @Override
    public String toString()
    {
        String categoriesStr = categories.toString().replaceAll("^\\[|\\]$", "");
        return String.format("%s,%d, %s", name, quantity, categoriesStr);
    }

    public void addCategory(Category category)
    {
        categories.add(category);
    }
    public void addCategory(String cat)
    {
        switch (cat.trim().toLowerCase()){
            case "chest":
                categories.add(Category.CHEST);
                break;
            case "triceps" :
                categories.add(Category.TRICEPS);
                break;
            case "back" :
                categories.add(Category.BACK);
                break;
            case "biceps" :
                categories.add(Category.BICEPS);
                break;
            case "shoulder" :
                categories.add(Category.SHOULDER);
                break;
            case "leg" :
                categories.add(Category.LEG);
                break;
        }
    }

    enum Category {
        CHEST, TRICEPS, BACK, BICEPS, SHOULDER, LEG;
    }
}



