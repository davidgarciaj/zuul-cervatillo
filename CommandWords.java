import java.util.Iterator;
import java.util.ArrayList;

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
    private ArrayList<Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new ArrayList<>();
        validCommands.add(Option.GO);
        validCommands.add(Option.QUIT);
        validCommands.add(Option.HELP);
        validCommands.add(Option.LOOK);
        validCommands.add(Option.EAT);
        validCommands.add(Option.BACK);
        validCommands.add(Option.TAKE);
        validCommands.add(Option.DROP);
        validCommands.add(Option.ITEMS);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String infoComandos = " ";
        Iterator<Option> it = validCommands.iterator();
        while(it.hasNext()){            
            Option command = it.next();            
            infoComandos+= command.getCommand() + " ";
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
        Iterator<Option> it = validCommands.iterator();
        while(it.hasNext()){            
            Option command = it.next();  
            if(command.getCommand().equals(aString))
                return true;
        }
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
        Iterator<Option> it = validCommands.iterator();
        while(it.hasNext()){            
            Option command = it.next();            
            if(command.getCommand().equals(commandWord))
                return command;
        }
        return Option.UNKNOW;
    }
}
