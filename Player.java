import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private ArrayList<Item> items;
    private float strong;
    private float itemsWeigth;

    /**
     * Constructor for objects of class Player
     */
    public Player( float strong)
    {
        this.strong = strong;
        items = new ArrayList<>();
        itemsWeigth = 0;
    }

    /**
     * Add a item to player
     */
    public void addItem(Item item){
        if((itemsWeigth + item.getKg()) <= strong){
            items.add(item);
            itemsWeigth+= item.getKg();
        }
        else{
            System.out.println("The item is so heavy.");
        }
    }

    /**
     * Add a item to player
     */
    public Item dropItem(String dropItem){
        Item drop = null;
        boolean notFind = true;
        int cont = 0;
        while(cont < items.size() && notFind){
            if(items.get(cont).getName().equals(dropItem)){
                drop = items.remove(cont);
                itemsWeigth-= drop.getKg();
                notFind = false;
            }
            cont++;
        }
        return drop;
    }
}
