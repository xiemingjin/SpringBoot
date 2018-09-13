package com.xiemj.javasestudy.jdk;

import com.xiemj.pojo.User;

import java.util.*;

/**
 * JDK1.8新特性
 */
public class Jdk implements Formula{


    @Override
    public double calculate(int a) {
        return sqrt(a*100);
    }


    public static void main(String[] args) {


        Jdk jdk = new Jdk();
        System.out.println(jdk.calculate(6));
        System.out.println(jdk.sqrt(6));


        String ss = "";

        ss.equals(ss);
        User user = new User();
        User user1 = new User();

        System.out.println(user==user1);
        System.out.println("sssss"+user.equals(user1));
        user.equals(user1);

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);
  /*      Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });*/

     /*   Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
*/


        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("1");

        System.out.println(list);
       /* list.forEach(System.out::println);
        System.out.println("11111");*/
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.offer("1");
        linkedList.offer("1");
        linkedList.offer("2");
        linkedList.offer("3");
        System.out.println(linkedList.getFirst());
       /* linkedList.forEach(System.out::println);*/
    }
}
