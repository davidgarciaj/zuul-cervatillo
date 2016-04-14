/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room suroeste, start, sureste, oeste, centro, este, noroeste, meta, noreste;

        // create the rooms
        suroeste = new Room("You are in South-west of the forest.");
        start = new Room("You are in the initial of the forest.");
        sureste = new Room("You are in South-east of the forest.");
        oeste = new Room("You are in West of the forest.");
        centro = new Room("You are in Center of the forest.");
        este = new Room("You are in East of the forest.");
        noroeste = new Room("You are in North-west of the forest.");
        meta = new Room("You are in the reserve, Congratulations.");
        noreste = new Room("You are in North-east of the forest.");

        // initialise room exits
        suroeste.setExit("north",oeste);
        suroeste.setExit("east",start);
        start.setExit("north",centro);
        start.setExit("east", sureste);
        start.setExit("west", suroeste);
        start.setExit("north-west",oeste);
        sureste.setExit("north",este);
        sureste.setExit("west",start);
        sureste.setExit("north-west",centro);
        oeste.setExit("north",noroeste);
        oeste.setExit("east",centro);
        oeste.setExit("south",suroeste);
        oeste.setExit("south-east",start);
        centro.setExit("north",meta);
        centro.setExit("east",este);
        centro.setExit("south",start);
        centro.setExit("west",oeste);
        centro.setExit("south-east",sureste);
        centro.setExit("north-west",noroeste);
        este.setExit("north",noreste); 
        este.setExit("south",sureste);
        este.setExit("west",centro);
        este.setExit("north-west",meta);
        noroeste.setExit("east", meta);
        noroeste.setExit("south",oeste);
        noroeste.setExit("south-east",centro);
        noreste.setExit("south", este);
        noreste.setExit("west", meta);

        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to hunting!");
        System.out.println("You are the little deer and you need to scape of the hunters in the forest");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
            wantToQuit = false;
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.printCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        boolean goal = false;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
            nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            
            if(currentRoom.getDescription().equals("You are in Center of the forest.")){                
                System.out.print("---You have hunted by the hunters---");
                goal = true;
            }
            else if(!currentRoom.getDescription().equals("You are in the reserve, Congratulations.")){
               printLocationInfo();
            }
            else{goal = true;}
            System.out.println();
        }        
        return goal;
    }

    /**
     * 
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
