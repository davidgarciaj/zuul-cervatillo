import java.util.Stack;
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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

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
        // create the items
        Item galletas1, galletas2, bayas, cadaver1, cadaver2, cuernos;
        
        galletas1 = new Item("Galletas","Niños por el parque te dan de comer galletas.", 0.3f, true, true);
        galletas2 = new Item("Galletas","Niños por el parque te dan de comer galletas.", 0.3f, true, true);
        bayas = new Item("Bayas", "Deliciosas bayas para comer", 0.1f, true, true);
        cadaver1 = new Item("Cadaver", "Cadaver de mejor amigo.",47.5f, false, false);
        cadaver2 = new Item("Cadaver", "Cadaver de un ciervo anciano.",52.76f, false, false);
        cuernos = new Item("Cuernos", "Grandes cuernos para poder defenderte.",5.2f, true, false);
        
        // create the rooms
        suroeste = new Room("You are in South-west of the forest.", player.INITIAL_STRONG, null);
        start = new Room("You are in the initial of the forest.", player.INITIAL_STRONG, null);
        sureste = new Room("You are in South-east of the forest.", player.INITIAL_STRONG, null);
        oeste = new Room("You are in West of the forest.", 5.5f, null);
        centro = new Room("You are in Center of the forest.", player.INITIAL_STRONG, null);
        este = new Room("You are in East of the forest.", player.INITIAL_STRONG, null);
        noroeste = new Room("You are in North-west of the forest.", player.INITIAL_STRONG, null);
        meta = new Room("You are in the reserve, Congratulations.", 5.8f, cuernos);
        noreste = new Room("You are in North-east of the forest.", player.INITIAL_STRONG, null);
        
        //insert items in the rooms
        suroeste.addItem(galletas1);
        sureste.addItem(bayas);
        oeste.addItem(galletas2);
        centro.addItem(cadaver2);
        noroeste.addItem(cadaver1);
        noreste.addItem(cuernos);

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
        
        // start game outside
        player = new Player(start);  
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
        System.out.println("Type '" + Option.HELP.getCommand() + "' if you need help.");
        System.out.println();
        player.printLocationInfo();
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

        Option commandWord = command.getCommandWord();
        switch(commandWord){
            case HELP:  printHelp();
                        break;
            case GO:    wantToQuit = goRoom(command);
                        break;
            case LOOK:  player.printLocationInfo();
                        System.out.println();
                        wantToQuit = false;
                        break;
            case EAT:   if(command.hasSecondWord()){
                            player.eatItem(command.getSecondWord());
                            System.out.println();
                        }
                        else{System.out.println("What item do you want to eat?.\n");}
                        wantToQuit = false;
                        break;
            case QUIT:  wantToQuit = quit(command);
                        break;
            case BACK:  if(player.getLastRooms().empty()){
                            System.out.println("ERROR: No es posible volver a la localizacion anterior.\n");
                        }
                        else{
                            wantToQuit = goRoom(command);
                        }
                        break;
            case TAKE:  if(command.hasSecondWord()){
                            player.takeItem(command.getSecondWord());
                            System.out.println();
                        }
                        else{System.out.println("What item do you want to take?.\n");}
                        wantToQuit = false;
                        break;
            case DROP:  if(command.hasSecondWord()){
                            player.dropItem(command.getSecondWord());
                            System.out.println();}
                        else{System.out.println("What item do you want to drop?.\n");}
                        wantToQuit = false;
                        break;
            case ITEMS:     System.out.println(player.itemList() + "\n");
                            wantToQuit = false;
                            break;
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
        Stack<Room> lastRooms = player.getLastRooms();
        Room playerRoom = player.getCurrentRoom();
        boolean goal = false;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            if(command.getCommandWord() == Option.BACK){
                playerRoom = lastRooms.pop();
                goal = player.intoRoom();
            }else{
                System.out.println("Go where?");
                return false;
            }
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = playerRoom.getExit(direction, player.getStrong(),player.getItems());

        if (nextRoom == null) {
            if(command.getCommandWord() == Option.GO){
                System.out.println("There is no door!");
            } 
        }
        else {
            lastRooms.push(playerRoom);
            player.setCurrentRoom(nextRoom);
            player.setStrong(player.INITIAL_STRONG);
            goal = player.intoRoom();
        }     

        return goal;
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
