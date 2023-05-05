import java.util.Vector;

public class Cart {
    protected int total=0;
    protected Vector<Item> items=new Vector<Item>();
    void add_item(Item item)
    {
        items.add(item);
    }
}
