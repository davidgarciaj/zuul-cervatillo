
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("go"),
    QUIT("quit"), 
    HELP("help"), 
    LOOK("look"), 
    EAT("eat"), 
    BACK("back"), 
    TAKE("take"), 
    DROP("drop"), 
    ITEMS("items"), 
    UNKNOW("unknow");
    
    private final String command;
    
    Option(String command){
        this.command = command;
    }
    
    /**
     * 
     */
    public String getCommand(){return command;}
}
