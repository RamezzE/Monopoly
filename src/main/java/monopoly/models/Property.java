package monopoly.models;

import java.util.ArrayList;

/**
 *
 * @author ramez
 */

public class Property extends Tile {
    
    private int rent;
    private int baseRent;
    private int fullSetRent;    
//    private int rentWithOneHouse;
//    private int rentWithTwoHouses;
//    private int rentWithThreeHouses;
//    private int rentWithFourHouses;
//    private int rentWithHotel;
//    private int housePrice;
//    private int hotelPrice;
//    private int mortgage;
    private Boolean isOwned;
    private Boolean fullSetIsOwned;
    private int ownerID;
    private int noToFullSet;
    private int location;
    
    public int[] fullSet;
    
    public Property() {}
    
    public Property(int location,String name, String color , int price, int baseRent, int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses, int rentWithHotel, int housePrice, int mortgage, int noToFullSet) {
        
        this.location = location;
        this.name = name;
        this.color = color;
        this.price = price;
        this.baseRent = baseRent;
        rent = baseRent;
//        this.rentWithOneHouse = rentWithOneHouse;
//        this.rentWithTwoHouses = rentWithTwoHouses;
//        this.rentWithThreeHouses = rentWithThreeHouses;
//        this.rentWithFourHouses = rentWithFourHouses;
//        this.rentWithHotel = rentWithHotel;
//        this.housePrice = housePrice;
//        this.mortgage = mortgage;
        fullSetRent = baseRent * 2;
//        hotelPrice = housePrice;
        this.noToFullSet = noToFullSet;
        
        isOwned = false;
        fullSetIsOwned = false;
        ownerID = -1;
        fullSet = new int[noToFullSet];
        this.type = "property";
    }
    
    public void setFullSet(int T1) {
        fullSet[0] = location;
        fullSet[1] = T1;
    }
    
    public void setFullSet(int T1,int T2) {
        fullSet[0] = location;
        fullSet[1] = T1;
        fullSet[2] = T2;
    }
    
    public void getFullSetNames(ArrayList<Tile> Board) {
        for (int i = 0; i < fullSet.length; i++) {
            System.out.print(Board.get(fullSet[i]).name + " ");
        }
        System.out.println();
    }
    
    public void setIsOwned(Boolean bool, int ownerID) {
        
        //set the property's owned and ownerID attributes
        
        isOwned = bool;
        this.ownerID = ownerID;
    }
    
    public Boolean getIsOwned() {
        return isOwned;
    }
        
    public int getOwnerID() {
        return ownerID;
    }
    
    private void setFullSetIsOwned(ArrayList<Tile> Board, Boolean bool) {
        fullSetIsOwned = bool;
        if (bool) {
            rent = fullSetRent;
            for (int i = 1;i<noToFullSet;i++) {
                ((Property)Board.get(fullSet[i])).rent = ((Property)Board.get(fullSet[i])).fullSetRent;
            }
            return;
        }
        rent = baseRent;
        for (int i = 1;i<noToFullSet;i++) {
            ((Property)Board.get(fullSet[i])).rent = ((Property)Board.get(fullSet[i])).baseRent;
        }
    }
    
    public int getBaseRent() {
        return baseRent;
    }
    
    public int getFullSetRent() {
        return fullSetRent;
    }
      
    public Boolean isFullSet(ArrayList<Tile> Board, ArrayList<Player> player) {
        for (int i = 0;i<noToFullSet;i++) {
            if (((Property)Board.get(fullSet[i])).getOwnerID() == -1) {
                setFullSetIsOwned(Board,false);
                return false;
            }
            if(!player.get(ownerID).getName().equals(player.get(((Property)Board.get(fullSet[i])).ownerID).getName())) {
                return false;
            }
        }
        setFullSetIsOwned(Board,true);
        return fullSetIsOwned;
    }
    
}