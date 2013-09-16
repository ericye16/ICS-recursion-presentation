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

    public static CostAndPath HeldKarpEqn(ArrayList<Cities> S, Cities el) {
        assert (S.contains(el));
        if (S.size() == 1) {
            ArrayList<Cities> path = new ArrayList<Cities>();
            path.add(el);
            return new CostAndPath(el, Cities.Home.distance(el), path);   //from start to city L, change vars later
        } else {
            //make a new arraylist and remove l
            ArrayList<Cities> S_minusL = (ArrayList<Cities>) S.clone();
            S_minusL.remove(el);

            ArrayList<Cities> newPath = null;

            int min = Integer.MAX_VALUE;
            for (Cities m : S_minusL) {
                CostAndPath costAndPath;
                int dist;
                costAndPath = HeldKarpEqn(S_minusL, m);
                dist = costAndPath.cost + m.distance(el);
                if (dist < min) {
                    min = dist;
                    newPath = (ArrayList<Cities>) costAndPath.path.clone();
                    newPath.add(el);
                }
            }
            return new CostAndPath(el,min, newPath);
        }
    }

    public static void main(String[] args) {
        System.out.println("It's go time!");
        ArrayList<Cities> allCities = new ArrayList<Cities>();
        Collections.addAll(allCities, Cities.values());

        //take out city one
        allCities.remove(Cities.Home);
        //find a nice l
        int minCost = Integer.MAX_VALUE;
        ArrayList<Cities> path = null;
        for (Cities el : allCities) {
            int cost;
            CostAndPath costAndPath;
            costAndPath = HeldKarpEqn(allCities, el);
            cost = costAndPath.cost + Cities.Home.distance(el);
            if (cost < minCost) {
                minCost = cost;
                path = (ArrayList<Cities>) costAndPath.path.clone();
                path.add(Cities.Home);
            }
        }

        System.out.printf("The total distance is %d km.\n", minCost); // should be 1932
        System.out.println("The path is: ");
        assert path != null;
        for (Cities c:path) {
            System.out.print(c + " ");
        }

    }


    static class CostAndPath {
        public ArrayList<Cities> path;
        public Cities city;
        public int cost;

        public CostAndPath(Cities c, int co, ArrayList<Cities> p) {
            path = p;
            city = c;
            cost = co;
        }

    }
}
