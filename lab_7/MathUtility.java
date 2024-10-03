package com.course.maths;

import java.lang.Math;
import java.time.LocalDate;

public class MathUtility 
{
    public long numberGenerator(int prefix, int suffix) 
    {
        long num = prefix;
        for (int i = 0; i < 8; i++) 
        {
            num = num * 10 + (long) Math.random();
        }
        num = num * 10 + suffix;
        return num;
    }

    public double calculateSimpleInterest(double principal, int term, double rate) 
    {
        return principal * term * rate;
    }

    public void printTriangle() 
    {
        int count = 0;
        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j <= i; j++) 
            {
                System.out.print(count + " ");
                count++;
            }
            System.out.println();
        }
        System.out.println();

        count = 9;
        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j < 4 - i; j++) 
            {
                System.out.print(count + " ");
                count--;
            }
            System.out.println();
        }
    }
    public void printMonthFromDate(LocalDate dt) 
    {
        int month = dt.getMonthValue();
        switch (month)
        {
            case 1:
                System.out.println("The month is January");
                break;
            case 2:
                System.out.println("The month is February");
                break;
            case 3:
                System.out.println("The month is March");
                break;
            case 4:
                System.out.println("The month is April");
                break;
            case 5:
                System.out.println("The month is May");
                break;
            case 6:
                System.out.println("The month is June");
                break;
            case 7:
                System.out.println("The month is July");
                break;
            case 8:
                System.out.println("The month is August");
                break;
            case 9:
                System.out.println("The month is September");
                break;
            case 10:
                System.out.println("The month is October");
                break;
            case 11:
                System.out.println("The month is November");
                break;
            case 12:
                System.out.println("The month is December");
                break;
            default:
                System.out.println("Invalid Month");
        }
    }
}