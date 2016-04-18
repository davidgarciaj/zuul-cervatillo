
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

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, float kg)
    {
        this.name = name;
        this.description = description;
        this.kg = kg;
    }

    /**
     * @return     name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return     description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return     name
     */
    public float getKg()
    {
        return kg;
    }
}
