package utils;

import java.util.List;

public class CollectionUtils {

    public static boolean isEmpty(List list ) {
        if ((list == null) || (list.size() == 0)) {
            return true;
        } else {
            return false;
        }
    }

}
