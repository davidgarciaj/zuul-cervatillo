
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String name;
    private String description;
    private float kg;
    private boolean take;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, float kg, boolean take)
    {
        this.name = name;
        this.description = description;
        this.kg = kg;
        this.take = take;
    }

    /**
     * @return     name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return     name
     */
    public boolean getTake()
    {
        return take;
    }

    /**
     * @return     description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return     kg
     */
    public float getKg()
    {
        return kg;
    }
    
    /**
     * 
     */
    public String toString(){
        return "\n" + name + ": " + description + ". Peso-->" + kg + "Kg.";
    }
}
