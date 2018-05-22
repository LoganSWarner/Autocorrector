
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Logan Warner
 */
public class DamerauLevenshteinComparer {
    private static final int ALPHABET_SIZE = 37; //Alphanumeric only, ignoring case, with apostrophe
    
    public static int getDistance(String alpha, String beta) {
        // Note: Java initalizes integer arrays to zero, saving some work
        // 2 greater than so we can populate the necessary edges
        int[][] d = new int[alpha.length() + 2][beta.length() + 2];
        Map<Character, Integer> da = new TreeMap<>();
        final int maximum_distance = alpha.length() + beta.length();
        
        //Initialize da with 0s for alphanumerics plus '
        for(int i = 48; i < 58; i++) {
            da.put((char) i, 0);
        }
        for(int i = 97; i < 123; i++) {
            da.put((char) i, 0);
        }
        da.put((char) 39, 0);
        // Initialize the outermost edge with the maximum distance
        // and second outermost edges with starting numbers
        d[0][0] = maximum_distance;
        for(int i = 0; i <= alpha.length(); i++) {
            d[i+1][0] = maximum_distance;
            d[i+1][1] = i;
        }
        for(int j = 0; j <= beta.length(); j++) {
            d[0][j+1] = maximum_distance;
            d[1][j+1] = j;
        }
        // Populate the rest of the array
        for(int i = 1; i <= alpha.length(); i++) {
            // db, k, and l are used to track transpositions
            int db = 0;
            for(int j = 1; j <= beta.length(); j++) {
                int k = da.get(beta.charAt(j-1));
                int l = db;
                int substitution_cost;
                // Should be no cost when characters are already the same
                if(alpha.charAt(i-1) == beta.charAt(j-1)) {
                    substitution_cost = 0;
                    db = j;
                } else {
                    substitution_cost = 1;
                }
                // Find the least expensive transformation
                d[i+1][j+1] = Math.min(d[i][j] + substitution_cost, //Substition
                                Math.min(d[i+1][j] + 1, //Insertion
                                        Math.min(d[i][j+1] + 1, //Deletion
                                                d[k][l] + (i-k-1) + 1 + (j-l-1)))); //Transposition
            }
            da.put(alpha.charAt(i-1), i);
        }
        
        return d[alpha.length() + 1][beta.length() + 1];
    }
}
