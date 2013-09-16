/**
 * The main class for this project
 * Created with IntelliJ IDEA.
 * User: eric/Yaning
 * Date: 15/09/13
 * Time: 4:23 PM
 */

import java.util.*;
import java.lang.String;

public class ProgramMain {

    public static int HeldKarpEqn( ArrayList<Cities> S , Cities el){
        assert(S.contains(el));
        if (S.size() == 1){
            return Cities.Home.distance(el);   //from start to city L, change vars later
        }
        else {
            //make a new arraylist and remove l
            ArrayList<Cities> S_minusL = (ArrayList<Cities>) S.clone();
            S_minusL.remove(el);
            int min = Integer.MAX_VALUE;
            for (Cities m :S_minusL) {
                int dist;
                dist = HeldKarpEqn(S_minusL,m) + m.distance(el);
                if (dist < min) {
                    min = dist;
                }
            }
            return min;
        }
    }

    /*
    public static void HeldKarpDriver (){
        HashSet subCities = new HashSet();
        subCities = ();

        int minC;
        int newC;
        int el;
        int i;

        minC = 10000000;

        i = HeldKarpEqn(subCities, el);
        newC = i + cityA.distance(cityB); //change cities later
        if (newC<minC){
            minC = newC;

        }

    }
    */


    public static void main(String[] args) {
        System.out.println("It's go time!");
        ArrayList<Cities> allCities = new ArrayList<Cities>();
        for (Cities city:Cities.values()) {
            allCities.add(city);
        }

        //take out city one
        allCities.remove(Cities.Home);
        //find a nice l
        int minCost = Integer.MAX_VALUE;
        for (Cities el:allCities) {
            int cost;
            cost = HeldKarpEqn(allCities,el) + Cities.Home.distance(el);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        System.out.println(minCost);
    }

}
