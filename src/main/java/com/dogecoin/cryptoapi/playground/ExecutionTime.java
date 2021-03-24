package com.dogecoin.cryptoapi.playground;

public class ExecutionTime {

    public static void dumbFunction() {
        System.out.println("Hello world");
        long  range = (long) 1e9;

    }


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        dumbFunction();
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
