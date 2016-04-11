import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        exits = new HashMap<>();
        this.description = description;
    }

    /**
     * Devuelve el objeto room indicado
     * @param direction  Direccion en la que se mueve el game
     */
    public Room getExit(String direction){
        Room thisRoom = null;
        Iterator<Map.Entry<String, Room>> it = exits.entrySet().iterator();
        while(it.hasNext()){            
			Map.Entry<String,Room> entry = (Map.Entry<String,Room>) it.next();
			if(direction.equals(entry.getKey())){
			    thisRoom = entry.getValue();
			}
        }
        return thisRoom;
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
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {        
        exits.put("north",north);
        exits.put("south",south);
        exits.put("east",east);
        exits.put("west",west);
        exits.put("south-east",southEast);
        exits.put("north-west",northWest);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
