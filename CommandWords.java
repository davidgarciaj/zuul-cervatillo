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
    private Option[] validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = Option.values();
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String infoComandos = " ";
        int i = 0;
        while(i < validCommands.length - 1){           
            infoComandos+= validCommands[i].getCommand() + " ";
            i++;
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
        int i = 0;
        while(i < validCommands.length){  
            if(validCommands[i].getCommand().equals(aString))
                return true;
            i++;
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
        int i = 0;
        while(i < validCommands.length){            
            Option command = validCommands[i];            
            if(command.getCommand().equals(commandWord))
                return command;
            i++;
        }
        return Option.UNKNOW;
    }
}
