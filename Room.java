import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private HashMap<String,Room> exits;
    private String description;
    private ArrayList<Item> objects;
    //Restrinciones
    private float neededStrong;
    private Item neededItem;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, float neededStrong, Item neededItem) 
    {
        exits = new HashMap<>();
        this.description = description;
        objects = new ArrayList<>();
        //Restrinciones
        this.neededStrong = neededStrong;
        this.neededItem = neededItem;
    }

    /**
     * Devuelve el objeto room indicado
     * @param direction  Direccion en la que se mueve el game
     * @param strong needed strong in the player to enter
     * @param item needed item in the player to enter
     */
    public Room getExit(String direction, float strong, ArrayList<Item> items){
        Room nextRoom = exits.get(direction);
        boolean find = false;
        if(strong >= nextRoom.getNeededStrong()){
            if(nextRoom.getNeededItem() != null){
                int i = 0;
                while(i < items.size() && !find){
                    if(items.get(i).equalsItem(nextRoom.getNeededItem())){
                        find = true;
                    }
                    i++;
                }
                if(!find){
                System.out.println("You dont have the needed item to pass, you need to have the item '"
                    + nextRoom.getNeededItem().getName() + "'.");
                    nextRoom = null;
                }
            }
        }else{
            System.out.println("You dont have the needed strong to pass, you need " 
                + nextRoom.getNeededStrong() + " if you want pass.");
            nextRoom = null;
        }
        return nextRoom;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salidas = "Exits: ";
        Iterator it = exits.entrySet().iterator();
        while(it.hasNext()){            
            Map.Entry entry = (Map.Entry) it.next();
            if(entry.getValue() != null){
                salidas+= entry.getKey() + " ";
            }
        }
        return  salidas;
    }

    /**
     * Define one exit of this room with his direction.
     * @param the direction to the exit.
     * @param room The room to exit
     */
    public void setExit(String nombre, Room room) 
    {        
        exits.put(nombre,room);
    }

    /**
     * @return The description of the room.
     */
    public float getNeededStrong()
    {
        return neededStrong;
    }

    /**
     * @return The description of the room.
     */
    public Item getNeededItem()
    {
        return neededItem;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String datosRoom = description + "\n " + getExitString();
        if(!objects.isEmpty()){
            datosRoom+="\nItems:";
            for(Item object : objects){
                datosRoom+= object;
            }
        }
        return datosRoom;
    }

    /**
     * Introduce a item in the room
     */
    public void addItem(String name, String itemDescription, float kg, boolean take, boolean canEat){
        objects.add(new Item(name,itemDescription,kg, take, canEat));
    }

    /**
     * Introduce a item in the room
     */
    public void addItem(Item item){
        objects.add(item);
    }

    /**
     * The item go to the room
     */
    public Item giveItem(String name){
        Item drop = null;
        boolean notFind = true;
        int cont = 0;
        while(cont < objects.size() && notFind){
            if(objects.get(cont).getName().equals(name)){
                drop = objects.remove(cont);
                notFind = false;
            }
            cont++;
        }
        return drop;
    }

}
