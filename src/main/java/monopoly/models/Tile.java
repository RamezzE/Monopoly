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

/*


 //initializing the whole Board
        
        
        
        Board.add(0,new OtherTiles("go"));

        Board.add(1,new Property("Mediterranean Avenue","Brown" , 60, 2, 10, 30, 90, 160, 250, 50, 30, 2));

        Board.add(2,new OtherTiles("chest"));
        
        Board.add(3,new Property("Baltic Avenue", "Brown" , 60, 4, 20, 60, 180, 320, 450, 50, 30, 2));

        Board.add(4,new OtherTiles("income tax")); //pays 200

        Board.add(5,new OtherTiles("railroads"));

        Board.add(6,new Property("Oriental Avenue", "Light blue" , 100, 6, 30, 90, 270, 400, 550, 50, 50, 3));

        Board.add(7,new OtherTiles("chance"));

        Board.add(8,new Property("Vermont Avenue", "Light blue" , 100, 6, 30, 90, 270, 400, 550, 50,50, 3));

        Board.add(9,new Property("Connecticut Avenue", "Light blue" , 120, 8, 40, 100, 300, 450, 600, 50, 60, 3));

        Board.add(10,new OtherTiles("jail"));
       
        Board.add(11,new Property("St. Charles Place",  "Pink" , 140, 10, 50, 150, 450, 625, 750, 100, 70, 3));

        Board.add(12,new OtherTiles("utilities"));

        Board.add(13,new Property("States Avenue",  "Pink" , 140, 10, 50, 150,  450, 625, 750, 100, 70, 3));

        Board.add(14,new Property("Virginia Avenue",  "Pink" , 160, 12, 60, 180,  500, 700, 900, 100, 80, 3));

        Board.add(new OtherTiles("railroads"));

        Board.add(new Property("St. James Place",  "Orange" , 180, 14, 70, 200, 550, 750, 950, 100, 90, 3));

        Board.add(new OtherTiles("chest"));

        Board.add(new Property("Tennessee Avenue",  "Orange" , 180, 14, 70, 200, 550, 750, 950, 100, 90, 3));

        Board.add(new Property("New York Avenue",  "Orange" , 200, 16, 80, 220, 600, 800, 1000, 100, 100, 3));

        Board.add(new OtherTiles("parking"));

        Board.add(new Property("Kentucky Avenue", "Red" , 220, 18, 90, 250, 700, 875, 1050, 150, 110, 3));

        Board.add(new OtherTiles("chance"));

        Board.add(new Property("IndianaAvenue", "Red" , 220, 18, 90, 250, 700, 875, 1050, 150, 110, 3));

        Board.add(new Property("Illinois Avenue", "Red" , 240, 20, 100, 300, 750, 925, 1100, 150, 120, 3));

        Board.add(new OtherTiles("railroads"));

        Board.add(new Property("Atlantic Avenue", "Yellow" , 260, 22, 110, 330, 800, 975, 1150, 150, 130, 3));

        Board.add(new Property("Ventnor Avenue", "Yellow" , 260, 22, 110, 330, 800, 975 , 1150, 150, 130, 3));

        Board.add(new OtherTiles("utilities"));

        Board.add(new Property("Marvin Gardens", "Yellow" , 280, 24, 120, 360, 850, 1025, 1200, 150, 140, 3));

        Board.add(new OtherTiles("go to jail"));

        Board.add(new Property("Pacific Avenue", "Green" , 300, 26, 130, 390, 900, 1100, 1275, 200, 150, 3));

        Board.add(new Property("North Carolina Avenue", "Green" , 300, 26, 130, 390, 900, 1100, 1275, 200, 150, 3));

        Board.add(new OtherTiles("chest"));

        Board.add(new Property("Pennsylvania Avenue", "Green" , 320, 28, 150, 450, 1000, 1200, 1400, 200, 160, 3));
        
        Board.add(new OtherTiles("railroads"));

        Board.add(new OtherTiles("chance"));

        Board.add(new Property("Park Place", "Dark blue" , 350, 35, 175, 500, 1100, 1300, 1500, 200, 175, 2));

        Board.add(new OtherTiles("luxury tax"));

        Board.add(new Property("Boardwalk", "Dark blue" , 400, 50, 200, 600, 1400, 1700, 2000, 200, 200, 2));
                
        
        
        //linking sets to each other
                
        Property.linkProperties(Board.get(1), Board.get(3));
        
        Property.linkProperties(Board.get(6), Board.get(8),Board.get(9));
        
        Property.linkProperties(Board.get(11), Board.get(13),Board.get(14));
        
        Property.linkProperties(Board.get(16), Board.get(18),Board.get(19));

        Property.linkProperties(Board.get(21), Board.get(23),Board.get(24));
        
        Property.linkProperties(Board.get(26), Board.get(27),Board.get(29));
        
        Property.linkProperties(Board.get(31), Board.get(32),Board.get(34));
        
        Property.linkProperties(Board.get(39),Board.get(37));

        
        try{
            FileOutputStream writeData = new FileOutputStream("defaultBoardData.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            
             writeStream.writeObject(Board);
             //writeStream.flush();
             writeStream.close();

        }
        catch (IOException e) {
            System.out.println("Error writing defaultBoardData to file");
             //e.printStackTrace();
        }
        
        
*/
