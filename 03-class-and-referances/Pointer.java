import java.util.*;

public class Pointer {
    public static void main(String[] args){
         int num1=11;
         int num2=num1;

         System.out.println("frist number is :"+num1);
         System.out.println("second number is :"+num2);

         num1=23;
         System.out.println("frist number is :"+num1);// frist number changes but 2nd not coz 2nd stores a value which is in the frist number . it doese not store frist numbers referance 

         System.out.println("second number is :"+num2);


         HashMap<String,Integer> map1=new HashMap<>();
         HashMap<String,Integer> map2=new HashMap<>();

         map1.put("value", 11);
         map2=map1; // points to the place where map1 points 

         System.out.println(map1); // both of this points to the same place 
         System.out.println(map2); 

         map1.put("value", 235);
         System.out.println(map1); // both of this points to the same  thats why the value of both changes . if i change the value of one   
         System.out.println(map2); 

         /*if map one point to the something in hashmap and map2 point to the another thing in the hash map but after we did map1=map2 so map 1 also point to the hashmap where map 2 points . but what happens with the value where map1 points previously which is not accessible . it goes through a garbage collection and that hashmap removed . */


    }
}
