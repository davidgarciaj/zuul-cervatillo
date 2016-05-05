
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
    private boolean canEat;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, float kg, boolean take, boolean canEat)
    {
        this.name = name;
        this.description = description;
        this.kg = kg;
        this.take = take;
        this.canEat = canEat;
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
     * @return     name
     */
    public boolean getCanEat()
    {
        return canEat;
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
        return "\n" + name + ": " + description + " || Peso-->" + kg + "Kg.";
    }
}
