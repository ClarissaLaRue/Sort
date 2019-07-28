import java.io.File;
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
        Integer[] integer = {234, 9, 43, 65};
        Integer[] buf = new Integer[integer.length];
        Integer[] res = SortIntD(integer, buf, 0, integer.length-1);
        System.out.println(Arrays.toString(res));
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

    private void SortIntA(){

    }
}
