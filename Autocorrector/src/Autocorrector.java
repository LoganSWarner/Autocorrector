
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;



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
        
        typos_in.lines().forEach((typo_word) -> {
            typo_words.add(typo_word);
        });
        
        ArrayList<String> corrected_words = new ArrayList<>();
        
        // TODO: populate array list with corrections
        
        corrected_words.forEach((corrected_word) -> {
            System.out.println(corrected_word);
        });
    }
    
}
