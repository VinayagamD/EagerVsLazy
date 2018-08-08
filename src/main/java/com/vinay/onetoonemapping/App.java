package com.vinay.onetoonemapping;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String hello = "Hello";
        String hello2 = "Hello";
        String hello1 = new String("Hello");
        System.out.println(hello == hello1);
        System.out.println(hello == hello2);
        System.out.println(hello.equals(hello1));
        System.out.println(8&9);
        System.out.println("A".compareTo("A"));
        System.out.println("a".compareTo("A"));
        System.out.println("A".compareTo("a"));
//        System.out.println("A".compareTo(null));
        char[] helloArray = hello.toCharArray();
        int len = helloArray.length;
        char t;
        for (int i = 0; i <= helloArray.length/2; i++) {
        	t = helloArray[i];
			
			helloArray[i] = helloArray[len-i-1];
			
			helloArray[len-i-1] = t;
		}
        String revHello = new String(helloArray);
        System.out.println(revHello);
    }
}
