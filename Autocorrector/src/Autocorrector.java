
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
        BufferedReader possible_words_in = new BufferedReader(new FileReader(new File(args[0])));
        ArrayList<String> possible_words = new ArrayList<>();
        
        typos_in.lines().forEach(typo_words::add);
        possible_words_in.lines().forEach(possible_words::add);
        
        ArrayList<String> corrected_words = new ArrayList<>();
        
        // TODO: populate array list with corrections
        // Design idea: collapse words into single characters with amounts,
        // compute similarity in placement and amount of characters,
        // start with dictionary entries that are the same length collapsed as
        // the input for some efficiency.
        // Stop early if we reach 100% similarity (1.0) or reach a threshold of
        // good-enough similarity once finishing a length section.
        
        corrected_words.forEach(System.out::println);
    }
    
}
