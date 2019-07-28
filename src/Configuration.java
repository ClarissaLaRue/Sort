import java.io.File;

//Class for configuration of sort
public class Configuration{
    private Boolean help;
    private Boolean correctly;
    private File dir;
    private String prefix;
    private String type;
    private String mode;

    public void setHelp(Boolean help) {
        this.help = help;
    }

    public void setCorrectly(Boolean correctly) {
        this.correctly = correctly;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public File getDir() {
        return dir;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getType() {
        return type;
    }

    public String getMode() {
        return mode;
    }

    public Boolean isHelp(){
        if(help){
            return true;
        }
        return false;
    }

    public Boolean isCorrectly() {
        return correctly;
    }
}
