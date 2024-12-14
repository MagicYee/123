package utils;

public class StringUtils {

    public static int[] getPosition(String args) {
        String[] arr = args.split(",");
        int[] res = new int[2];
        res[0] = Integer.valueOf(arr[0]);
        res[1] = Integer.valueOf(arr[1]);
        return res;
    }
}
