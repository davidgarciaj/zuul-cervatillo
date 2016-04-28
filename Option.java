
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("andare"),
    QUIT("smettere"), 
    HELP("aiuto"), 
    LOOK("guarda"), 
    EAT("mangiare"), 
    BACK("indietro"), 
    TAKE("prendere"), 
    DROP("cadere"), 
    ITEMS("elementi"), 
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
