
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
        // Guard clause for arguments
        if(args.length < 1) {
            System.out.println("Usage: `java Autocorrector <inputfile>`");
            return;
        }
        // Read in files and populate lists
        BufferedReader typos_in = new BufferedReader(new FileReader(new File(args[0])));
        ArrayList<String> typo_words = new ArrayList<>();
        BufferedReader possible_words_in = new BufferedReader(new FileReader(new File("en_US.dic")));
        ArrayList<String> possible_words = new ArrayList<>();
        typos_in.lines().forEach(typo_words::add);
        possible_words_in.lines().forEach(possible_words::add);
        
        ArrayList<String> corrected_words = new ArrayList<>();
        
        // Create corrections
        for(String typo_word: typo_words){
            // Save work by being strict here, 2 to prevent last example from correcting to "dud"
            int closest_dl_distance = 2;
            String closest_word = "UNKNOWN";
            for(String possible_word: possible_words) {
                // Ignore case for comparing so that incorrect casing doesn't matter, also condense 3 or more repeated letters into only 2
                int cur_distance = DamerauLevenshteinComparer.getDistance(typo_word.toLowerCase().replaceAll("(.)\\1{2,}", "$1$1"), possible_word.toLowerCase());
                // Only update if we yound a better match
                if(cur_distance < closest_dl_distance) {
                    closest_dl_distance = cur_distance;
                    closest_word = possible_word;
                }
            }
            corrected_words.add(closest_word);
        }
        
        corrected_words.forEach(System.out::println);
    }
    
}
