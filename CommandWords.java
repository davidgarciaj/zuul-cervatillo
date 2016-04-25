import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("andare",Option.GO);
        validCommands.put("smettere",Option.QUIT);
        validCommands.put("aiuto",Option.HELP);
        validCommands.put("guarda",Option.LOOK);
        validCommands.put("mangiare",Option.EAT);
        validCommands.put("indietro",Option.BACK);
        validCommands.put("prendere",Option.TAKE);
        validCommands.put("cadere",Option.DROP);
        validCommands.put("elementi",Option.ITEMS);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String infoComandos = " ";
        Iterator it = validCommands.entrySet().iterator();
        while(it.hasNext()){            
            Map.Entry entry = (Map.Entry) it.next();
            if(entry.getValue() != null){
                infoComandos+= entry.getKey() + " ";
            }
        }
        System.out.println(infoComandos);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {

        if(validCommands.containsKey(aString))
            return true;

        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option order = validCommands.get(commandWord);
        if(order == null){order = Option.UNKNOW;}
        return order;
    }
}
