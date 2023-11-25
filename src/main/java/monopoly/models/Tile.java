package monopoly.models;

import java.io.*;
/**
 *
 * @author ramez
 */

public abstract class Tile implements Serializable{
    
    protected String name, type, color;
    protected int price;
    
    public String getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String getColor() {
        return color;
    }
}