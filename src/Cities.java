import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * User: eric
 * Date: 15/09/13
 * Time: 5:29 PM
 */
public class Cities {

    public int indx;
    public static String[] cityNames = {"Home","Cornell", "Harvard", "Yale", "Boston", "Falls"};

    private HashMap<TwoCities,Integer> distances;

    class TwoCities {
        public Cities cityOne;
        public Cities cityTwo;
        public TwoCities(Cities one, Cities two) {
            cityOne = one;
            cityTwo = two;
        }
        public int hashCode() {
            return 0x1 << cityOne.indx & 0x1 << cityTwo.indx; // goodness I hope this works
        }
        public boolean equals(Object obj) {
            if (obj instanceof TwoCities && ((TwoCities) obj).cityOne == this.cityOne &&
                    ((TwoCities) obj).cityTwo == this.cityTwo) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public Cities(int cityId) {
        indx = cityId;
        for (int i = 0; i < cityNames.length; i++) {
            if (i == cityId) continue;

        }
    }

    public static void initializeMap() {
        for (int i = 0; i < cityNames.length; i++) {
            for (int j = 1; j < i; j++) { // quadratic loop
                switch (i) {
                    case 0:
                }
            }
        }
    }

    public int distance(Cities other) {
        //TODO: implement
        return 0;
    }
}
