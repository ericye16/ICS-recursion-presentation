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

    private static HashMap heldKarpEqnCache = new HashMap();

    public static CostAndPath HeldKarpEqn(ArrayList<Cities> S, Cities el) {
        assert (S.contains(el));

        final ArrayList<Cities> finalS = (ArrayList<Cities>) S.clone();
        final HashSet<Cities> finalSSet = new HashSet<Cities>(finalS);
        final Cities finalEl = el;
        SetAndEl inputVars = new SetAndEl(finalSSet, finalEl);
        CostAndPath cachedCP = (CostAndPath) heldKarpEqnCache.get(inputVars);
        if (cachedCP != null) {
            System.out.print("Cache hit!");
            return cachedCP;
        }

        CostAndPath singleTonCP;
        if (S.size() == 1) {
            ArrayList<Cities> path = new ArrayList<Cities>();
            path.add(el);
            singleTonCP = new CostAndPath(el, Cities.Home.distance(el), path);

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
            singleTonCP = new CostAndPath(el,min, newPath);
        }
        heldKarpEqnCache.put(inputVars, singleTonCP);
        return singleTonCP;   //from start to city L, change vars later
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

    static class SetAndEl {
        final public HashSet<Cities> S;
        final public Cities El;
        public SetAndEl (final HashSet<Cities> nS, final Cities nEl) {
            S = nS;
            El = nEl;
        }
        public int hashCode() {
            return S.hashCode() + El.hashCode();
        }
    }
}
