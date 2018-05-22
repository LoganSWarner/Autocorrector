
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Logan Warner
 */
public class DamerauLevenshteinComparer {
    private static final int ALPHABET_SIZE = 36; //Alphanumeric only, ignoring case
    
    public static int getDistance(String alpha, String beta) {
        // Note: Java initalizes integer arrays to zero, saving some work
        int[][] d = new int[alpha.length() + 2][beta.length() + 2];
        Map<Character, Integer> da = new TreeMap<>();
        final int maximum_distance = alpha.length() + beta.length();
        
        //Initialize da with 0s for alphanumerics
        for(int i = 48; i < 58; i++) {
            da.put((char) i, 0);
        }
        for(int i = 97; i < 123; i++) {
            da.put((char) i, 0);
        }
        // Initialize the outermost edge with the maximum distance
        d[0][0] = maximum_distance;
        for(int i = 1; i <= alpha.length() + 1; i++) {
            d[i][0] = maximum_distance;
        }
        for(int j = 1; j <= beta.length() + 1; j++) {
            d[0][j] = maximum_distance;
        }
        
        for(int i = 0; i < alpha.length(); i++) {
            int db = 0;
            for(int j = 0; j < beta.length(); j++) {
                int k = da.get(beta.charAt(j));
                int l = db;
                int substitution_cost;
                if(alpha.charAt(i) == beta.charAt(j)) {
                    substitution_cost = 0;
                    db = j + 1;
                } else {
                    substitution_cost = 1;
                }
                d[i+2][j+2] = Math.min(d[i+1][j+1] + substitution_cost,
                                Math.min(d[i+2][j+1] + 1,
                                        Math.min(d[i+1][j+2] + 1,
                                                d[k][l] + (i-k) + 1 + (j-l))));
            }
            da.put(alpha.charAt(i), i);
        }
        
        return d[alpha.length() + 1][beta.length() + 1];
    }
}
