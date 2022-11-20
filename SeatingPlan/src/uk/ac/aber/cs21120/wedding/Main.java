package uk.ac.aber.cs21120.wedding;

import java.util.*;

public class Main {
    public static void main(String[] args) {
         List<List<String>> list = new ArrayList<>();
         for(int i =0; i < 10; i++) {
             list.add(new ArrayList<String>(3));
         }
         String[] subList = new String[]{"A", "D"};
         list.get(3).add("A");
         list.get(3).add("B");
         list.get(3).add("C");
         list.get(3).add("D");
         System.out.println(list.get(3).containsAll(Arrays.asList(subList)));
    }
}
