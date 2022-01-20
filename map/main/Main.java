package main;

import java.util.Random;
//import array_map.Map;
import linked_map.Map;
//import atd_linked_map.Map;

public class Main {
    public static void main(String[] args) {
        /*
        Map map = new Map();
        char a = Map.RangeType.FIRST, z = Map.RangeType.LAST;
        int i, size = z - a + 1;
        Map.RangeType r = new Map.RangeType(Map.RangeType.NODEF);
        Random rand = new Random();
        while (a <= z)
        {
            i = Math.abs(rand.nextInt() % size);
            if (!map.compute(i, r))
                map.assign(i, a++);
        }
        map.print();

        a = Map.RangeType.FIRST;
        for(i = 0; i < size; i++)
            map.assign(i, a++);
        map.print();

        map.makeNull();
        map.print();*/

        linked_map.Map m = new Map();
        m.assign(1,'a');
        m.print();
        m.assign(2,'b');
        m.print();


        //atd_linked_map.Map m = new Map();


    }
}
