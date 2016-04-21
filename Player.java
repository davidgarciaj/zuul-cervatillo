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

    /**
     * Constructor for objects of class Player
     */
    public Player( float strong, Room currentRoom)
    {
        this.strong = strong;
        items = new ArrayList<>();
        this.currentRoom = currentRoom;
        lastRooms = new Stack<>();
        itemsWeigth = 0;
    }
    
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
        System.out.println(currentRoom.getLongDescription());
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
     * Drop a item to player
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
