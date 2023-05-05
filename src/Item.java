public class Item {
    protected String name;
    private String price;
    private String item_id;
    private String quantity;
    private String brand;
    private String category;
    private String description;
    private boolean available;
    private boolean visibility;
    Item(){}
    void setName(String str)
    {
        name=str;
    }
    void setPrice(String n)
    {
        price=n;
    }
    void setItem_id(String str)
    {
        item_id=str;
    }
    void setBrand(String str)
    {
        brand=str;
    }
    void setQuantity(String n)
    {
        quantity=n;
    }
    void setCategory(String str)
    {
        category=str;
    }
    void setDescription(String str)
    {
        description=str;
    }
    String getName(){return name;}
    String getPrice(){return price;}
    String getItem_id(){return item_id;}
    String getQuantity(){return quantity;}
    String getBrand(){return brand;}
    String getCategory(){return category;}
    String getDescription(){return description;}
}
