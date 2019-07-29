import java.io.*;
import java.util.Arrays;

public class Sort implements Runnable {
    private Configuration configuration;
    File file;

    public Sort(Configuration configuration, File file){
        this.configuration = configuration;
        this.file = file;
    }

    @Override
    public void run() {
        if (configuration.getType().equals("i")){
            workWithInt();
        }else if(configuration.getType().equals("s")){
            workWithString();
        }
    }

    //write results to file
    private void printToFile(Integer[] res){
        String fileName = configuration.getPrefix() + file.getName();
        File outFile = new File(fileName);
        try {
            PrintWriter writer = new PrintWriter(outFile);
            for (Integer re : res) {
                writer.println(re);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't make results to file");
        }
    }

    private void printToFile(String[] res){
        String fileName = configuration.getPrefix() + file.getName();
        File outFile = new File(fileName);
        try {
            PrintWriter writer = new PrintWriter(outFile);
            for (String re : res) {
                writer.println(re);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't make results to file");
        }
    }

    private void workWithString() {
        try {
            String input = readFile();
            String[] strings = input.split( " ");
            String[] buf = new String[strings.length];
            String[] res;
            if (configuration.getMode().equals("d")){
                res = SortStrD(strings, buf, 0, strings.length-1);
                printToFile(res);
            }else{
                res = SortStrA(strings, buf, 0, strings.length-1);
                printToFile(res);
            }
        } catch (IOException e) {
            System.out.print("Error of reading file: ");
            System.out.print(file);
        }
    }


    private void workWithInt() {
        try {
            String input = readFile();
            String[] numdersStr = input.split( " ");
            Integer[] numbers = new Integer[numdersStr.length];
            for(int i = 0; i <numdersStr.length; i++){
                numbers[i] = Integer.parseInt(numdersStr[i]);
            }
            Integer[] buf = new Integer[numbers.length];
            Integer[] res;
            if (configuration.getMode().equals("d")){
                res = SortIntD(numbers, buf, 0, numbers.length-1);
                printToFile(res);
            }else{
                res = SortIntA(numbers, buf, 0, numbers.length-1);
                printToFile(res);
            }
        } catch (IOException e) {
            System.out.print("Error of reading file: ");
            System.out.print(file);
        }
    }


    //read from file make one String
    private String readFile() throws IOException {
        StringBuilder input = new StringBuilder();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while(line != null){
            input.append(line);
            input.append(" ");
            line = reader.readLine();
        }
        String res = null;
        res = new String(input);
        return res;
    }

    //Sort for Integer in descending
    private Integer[] SortIntD(Integer[] numbers, Integer[] buf, int left, int right){
        if (left == right)
        {
            buf[left] = numbers[left];
            return buf;
        }
        int middle = (left + right) / 2;
        Integer[] bufL = SortIntD(numbers, buf, left, middle);
        Integer[] bufR = SortIntD(numbers, buf, middle + 1, right);
        Integer[] SortedArray = bufL == numbers ? buf : numbers;
        int curL = left;
        int curR = middle + 1;
        for (int i = left; i <= right; i++) {
            if (curL <= middle && curR <= right) {
                if (bufL[curL] < bufR[curR]) {
                    SortedArray[i] = bufL[curL];
                    curL++;
                }else{
                    SortedArray[i] = bufR[curR];
                    curR++;
                }
            }else if (curL <= middle){
                SortedArray[i] = bufL[curL];
                curL++;
            }else{
                SortedArray[i] = bufR[curR];
                curR++;
            }
        }
        return SortedArray;
    }

    //Sort for Integer in augmentation
    private Integer[] SortIntA(Integer[] numbers, Integer[] buf, int left, int right) {
        if (left == right)
        {
            buf[left] = numbers[left];
            return buf;
        }
        int middle = (left + right) / 2;
        Integer[] bufL = SortIntA(numbers, buf, left, middle);
        Integer[] bufR = SortIntA(numbers, buf, middle + 1, right);
        Integer[] SortedArray = bufL == numbers ? buf : numbers;
        int curL = left;
        int curR = middle + 1;
        for (int i = left; i <= right; i++) {
            if (curL <= middle && curR <= right) {
                if (bufL[curL] < bufR[curR]) {
                    SortedArray[i] = bufR[curR];
                    curR++;
                }else{
                    SortedArray[i] = bufL[curL];
                    curL++;
                }
            }else if (curL <= middle){
                SortedArray[i] = bufL[curL];
                curL++;
            }else{
                SortedArray[i] = bufR[curR];
                curR++;
            }
        }
        return SortedArray;
    }

    //Sort for String in descending
    private String[] SortStrD(String[] strings, String[] buf, int left, int right){
        if (left == right)
        {
            buf[left] = strings[left];
            return buf;
        }
        int middle = (left + right) / 2;
        String[] bufL = SortStrD(strings, buf, left, middle);
        String[] bufR = SortStrD(strings, buf, middle + 1, right);
        String[] SortedArray = bufL == strings ? buf : strings;
        int curL = left;
        int curR = middle + 1;
        for (int i = left; i <= right; i++) {
            if (curL <= middle && curR <= right) {
                if (bufR[curR].compareTo(bufL[curL]) < 0) {
                    SortedArray[i] = bufL[curL];
                    curL++;
                }else{
                    SortedArray[i] = bufR[curR];
                    curR++;
                }
            }else if (curL <= middle){
                SortedArray[i] = bufL[curL];
                curL++;
            }else{
                SortedArray[i] = bufR[curR];
                curR++;
            }
        }
        return SortedArray;
    }

    //Sort for String in augmentation
    private String[] SortStrA(String[] strings, String[] buf, int left, int right){
        if (left == right)
        {
            buf[left] = strings[left];
            return buf;
        }
        int middle = (left + right) / 2;
        String[] bufL = SortStrA(strings, buf, left, middle);
        String[] bufR = SortStrA(strings, buf, middle + 1, right);
        String[] SortedArray = bufL == strings ? buf : strings;
        int curL = left;
        int curR = middle + 1;
        for (int i = left; i <= right; i++) {
            if (curL <= middle && curR <= right) {
                if (bufR[curR].compareTo(bufL[curL]) > 0) {
                    SortedArray[i] = bufL[curL];
                    curL++;
                }else{
                    SortedArray[i] = bufR[curR];
                    curR++;
                }
            }else if (curL <= middle){
                SortedArray[i] = bufL[curL];
                curL++;
            }else{
                SortedArray[i] = bufR[curR];
                curR++;
            }
        }
        return SortedArray;
    }
}
