
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;



/**
 * @author Logan Warner
 */
public class Autocorrector {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length < 1) {
            System.out.println("Usage: `java Autocorrector <inputfile>`");
            return;
        }
        BufferedReader typos_in = new BufferedReader(new FileReader(new File(args[0])));
        ArrayList<String> typo_words = new ArrayList<>();
        BufferedReader possible_words_in = new BufferedReader(new FileReader(new File("en_US.dic")));
        ArrayList<String> possible_words = new ArrayList<>();
        
        typos_in.lines().forEach(typo_words::add);
        possible_words_in.lines().forEach(possible_words::add);
        
        ArrayList<String> corrected_words = new ArrayList<>();
        
        // TODO: populate array list with suggestions based on closest
        // Damerau-Levenshtein distance, ignoring case, but preserving case of
        // suggestions        
        for(String typo_word: typo_words){
            int closest_dl_distance = Integer.MAX_VALUE;
            String closest_word = typo_word;
            for(String possible_word: possible_words) {
                int cur_distance = DamerauLevenshteinComparer.getDistance(typo_word, possible_word.toLowerCase());
                if(cur_distance < closest_dl_distance) {
                    closest_dl_distance = cur_distance;
                    closest_word = possible_word;
                }
            }
            if(closest_dl_distance < 2) {
                corrected_words.add(closest_word);
            } else {
                corrected_words.add("UNKNOWN");
            }
        }
        
        corrected_words.forEach(System.out::println);
    }
    
}
