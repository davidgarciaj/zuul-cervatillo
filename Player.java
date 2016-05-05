import java.util.ArrayList;
import java.util.Stack;
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
    private Room currentRoom;
    private Stack<Room> lastRooms;
    public static final float INITIAL_STRONG = 5.3f;    
    public static final float PLUS_STRONG_KG = 1.5f;

    /**
     * Constructor for objects of class Player
     */
    public Player( Room currentRoom)
    {
        strong = INITIAL_STRONG;
        items = new ArrayList<>();
        this.currentRoom = currentRoom;
        lastRooms = new Stack<>();
        itemsWeigth = 0;
    }

    /**
     * Give a value to strong
     */
    public void setStrong(float strong){ this.strong = strong;}

    /**
     * @return the library of lastRooms
     */
    public float getStong(){return strong;}

    /**
     * @return the library of lastRooms
     */
    public Stack<Room> getLastRooms(){return lastRooms;}

    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom(){return currentRoom;}

    /**
     * Change the currentRoom
     */
    public void setCurrentRoom(Room newRoom){currentRoom = newRoom;}

    /**
     * Actions to Room
     */
    public boolean intoRoom(){
        boolean goal = false;
        if(currentRoom.getDescription().equals("You are in Center of the forest.")){                
            System.out.print("---You have hunted by the hunters---");
            goal = true;
        }
        else if(!currentRoom.getDescription().equals("You are in the reserve, Congratulations.")){
            printLocationInfo();
        }
        else{
            System.out.print(currentRoom.getDescription());
            goal = true;
        }
        System.out.println();
        return goal;
    }

    /**
     * 
     */
    public void printLocationInfo(){
        System.out.println("Player --> Strong: " + strong + 
            " Weigth to items: " + itemsWeigth +
            "\n\n" + currentRoom.getLongDescription());
    }

    /**
     * Add a item to player
     */
    public void takeItem(String nameItem){
        Item item = currentRoom.giveItem(nameItem);
        if(item != null){
            if(item.getTake() == true){
                if((itemsWeigth + item.getKg()) <= strong){
                    items.add(item);
                    itemsWeigth+= item.getKg();
                    System.out.println("You put the " + item.getName() + "in the bag.");
                }
                else{
                    System.out.println("The item is so heavy.");
                    currentRoom.addItem(item);
                }
            }
            else{
                System.out.println("You can´t take this.");
                currentRoom.addItem(item);
            }
        }
        else{System.out.println("This site don´t have this item.");}
    }

    /**
     * Drop a item to player
     */
    public void dropItem(String dropItem){
        Item drop = null;
        boolean notFind = true;
        int cont = 0;
        while(cont < items.size() && notFind){
            if(items.get(cont).getName().equals(dropItem)){
                drop = items.remove(cont);
                itemsWeigth-= drop.getKg();
                notFind = false;
                System.out.println("You drop " + drop.getName() + "to the floor.");
            }
            cont++;
        }
        if(drop != null){
            currentRoom.addItem(drop);
        }
    }

    /**
     * @return String information of the items.
     */
    public String itemList(){
        String info = "This player don´t have any item.";
        if(!items.isEmpty()){
            info = "Items in the player:";
            for(Item item : items){
                info+= item;
            }}
        return info;
    }

    /**
     * Eat a item to up the strong
     * @param item to eat
     */
    public void eatItem(String itemToEat){
        Item eaten = null;
        boolean notFind = true;
        int cont = 0;
        while(cont < items.size() && notFind){
            Item item = items.get(cont);
            if(item.getName().equals(itemToEat)){
                if(item.getCanEat()){
                    eaten = items.remove(cont);
                    strong = strong + (eaten.getKg() * PLUS_STRONG_KG);
                    itemsWeigth-= eaten.getKg();
                }else{System.out.println("You can´t eat this item.");} 
                notFind = false;
            }
            cont++;
        }           
        if(notFind){System.out.println("You don´t have this item.");}    
    }
}
