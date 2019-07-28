import java.io.File;
import java.util.Scanner;

public class Program {
    private String helpString = "Please, enter arguments:\npath to dir with files --out-prefix=<name> --content-type=<s for strings or i for int> --sort-mode=<d for descending or a for augmentation >";

    public void Start(String[] args){
        Parser parser = new Parser();
        try {
            Configuration configuration = parser.parseArgs(args);

            if (!configuration.isCorrectly()){
                System.out.println("Invalid arguments, try again");
                System.out.println(helpString);
            }
            if(configuration.isHelp()){
                System.out.println(helpString);
            }

            File workDir = configuration.getDir();
            File[] files = workDir.listFiles();
            Sort sort = new Sort(configuration, files[0]);
            sort.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
