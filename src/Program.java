import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    private String helpString = "Please, enter arguments:\npath to dir with files --out-prefix=<name> --content-type=<s for strings or i for int> --sort-mode=<d for descending or a for augmentation >";

    public void Start(String[] args){
        Parser parser = new Parser();
        try {
            //Parse args
            Configuration configuration = parser.parseArgs(args);

            if (!configuration.isCorrectly()){
                System.out.println("Invalid arguments, try again");
                System.out.println(helpString);
            }
            if(configuration.isHelp()){
                System.out.println(helpString);
            }

            //Find all files in dir
            File workDir = configuration.getDir();
            File[] files = workDir.listFiles();
            if (files == null){
                System.out.println("Directory is empty");
                return;
            }

            //Make thread Pool to sort files
            ExecutorService threadPool = Executors.newFixedThreadPool(4);
            for (File file: files) {
                threadPool.execute(new Sort(configuration, file));
            }
            threadPool.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
