package monopoly.controllers;

import monopoly.models.Property;
import monopoly.models.Tile;

import java.util.*;

/**
 *
 * @author ramez
 */

public class PropertyController {
    
    public static void linkProperties(ArrayList<Tile> Board, int T1, int T2) {
        
        //link related sets with each other
        
        ((Property)Board.get(T1)).setFullSet(T2);
        ((Property)Board.get(T2)).setFullSet(T1);
    }
    
    public static void linkProperties(ArrayList<Tile> Board, int T1,int T2, int T3) {
        
        //link related sets with each other
        
        ((Property)Board.get(T1)).setFullSet(T2,T3);
        ((Property)Board.get(T2)).setFullSet(T1,T3);
        ((Property)Board.get(T3)).setFullSet(T1,T2);
    }


}