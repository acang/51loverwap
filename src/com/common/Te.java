package com.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Te
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        HashSet<Integer> hs = new HashSet<Integer>();   
        Random r = new Random();
        while(hs.size()<12)
        {
            hs.add(r.nextInt(50));
            //hs.iterator();
        }
        Iterator<Integer> i = hs.iterator();
            
               System.out.println(i.next());


    }

}
