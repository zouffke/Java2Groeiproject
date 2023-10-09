package be.kdg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class SailboatFunctions {

    public static <T> List<T> filteredList(List<T> list, Predicate<T> predicate){
        List<T> filtered = new ArrayList<>();

        for (T canditate : list){
            if (predicate.test(canditate)){
                filtered.add(canditate);
            }
        }
        return filtered;
    }

    public static <T> Double average (List<T> list, ToDoubleFunction<T> mapper){
        double avg = 0;
        for (T o : list){
            avg += mapper.applyAsDouble(o);
        }

        return  avg / list.size();
    }

    public static <T> long countIf(List<T> list, Predicate<T> predicate){
        long c = 0;
        for (T o : list){
            if (predicate.test(o)){
                c++;
            }
        }
        return c;
    }
}
