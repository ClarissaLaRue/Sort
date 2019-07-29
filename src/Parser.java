import java.io.File;

public class Parser {

    public Configuration parseArgs(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        if (args.length == 1){
            if (args[0].equals("--help")){//end if help
                configuration.setHelp(true);
                configuration.setCorrectly(true);
                return configuration;
            }
        }
        configuration.setHelp(false);
        if (args.length != 4){//not enough arguments
            configuration.setCorrectly(false);
            return configuration;
        }
        //try to get path, prefix, type and mode
        File dir = new File(args[0]);
        if (!dir.exists()){
            throw new Exception("Can't find directory");
        }
        configuration.setDir(dir);
        String[] strings = args[1].split("=");
        if (strings[0].equals("--out-prefix")){
            configuration.setPrefix(strings[1]);
        }else{
            configuration.setCorrectly(false);
            return configuration;
        }
        strings = args[2].split("=");
        if (strings[0].equals("--content-type")){
            if (strings[1].equals("s") || strings[1].equals("i")){
                configuration.setType(strings[1]);
            }else{
                configuration.setCorrectly(false);
                return configuration;
            }
        }else{
            configuration.setCorrectly(false);
            return configuration;
        }
        strings = args[3].split("=");
        if (strings[0].equals("--sort-mode")){
            if (strings[1].equals("a") || strings[1].equals("d")){
                configuration.setMode(strings[1]);
            }else{
                configuration.setCorrectly(false);
                return configuration;
            }
        }else{
            configuration.setCorrectly(false);
            return configuration;
        }
        configuration.setCorrectly(true);
        return configuration;
    }
}
