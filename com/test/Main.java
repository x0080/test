package com.test;

import java.util.Scanner;

public class Main {

    static char[] temp;

    //数字对应的字母
    static char[] NumberToChars(String a) {
        int number = 0;
        if(a.equals("*")||a.equals("#")){
            return new char[0];
        }
        try {
            number = Integer.parseInt(a);
        } catch (Exception e) {
            return null;
            //e.printStackTrace();
        }
        if(number<0||number>99){
            return null;
        }
        int np = 0;
        int start = 0;
        if(number>1&&number<=9){
            switch (number){
                default: np = number*3;start=91;break;
                case 7: np = number*4;start=84;break;
                case 8: np = 0;start=116;break;
                case 9: np = 0;start=119;break;
            }
        }
        if(number==7||number==9){
            char[] c = {byteAsciiToChar(start+np+3),byteAsciiToChar(start+np+2),byteAsciiToChar(start+np+1),byteAsciiToChar(start+np)};
            return c;
        }else if(number>1&&number<=9){
            char[] c = {byteAsciiToChar(start+np+2),byteAsciiToChar(start+np+1),byteAsciiToChar(start+np)};
            return c;
        }
        return new char[0];
    }

    static int[] get(char a[][]) {
        int[] result = new int[a.length];
        for(int i=0;i<a.length;i++){
            result[i]=a[i].length-1;
        }
        return result;
    }

    public static char byteAsciiToChar(int ascii){
        char ch = (char)ascii;
        return ch;
    }


    public static boolean isOver(int[] i){
        for(int j:i){
            if(j>0){
                return false;
            }
        }
        return true;
    }

    public static int[] up(int[] index,int[] index2){
        if(index.length==1){
            index[0]--;
            return index;
        }
        int j = index.length-1;
        if(index[j]<=0) {
            index[j]--;
            while (index[j]+1 <= 0&&j>0) {
                index[j - 1]--;
                index[j] = index2[j] + 1;
                j--;
            }
        } else {
            index[j]--;
        }
        for(int i=0;i<index.length;i++){
            if(index[i]>index2[i]){
                index[i]=index2[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner reader = new Scanner(System.in);
        String str;
        int i, sum;
        // Point point;
        while (reader.hasNext()) {
            sum = 0;
            str = reader.next();
            String[] s = str.split(",");
            int n = s.length;
            char temp[][] = new char[n][];
            int oi = 0;
            for (i = 0; i < n; i++) {
                temp[i-oi]=NumberToChars(s[i]);
                if(temp[i-oi]==null){
                    System.out.print("error");
                    return;
                } else if(temp[i-oi].length==0){
                    oi++;
                }
            }
            char o[][] = new char[n-oi][];
            for(int j=0;j<o.length;j++){
                o[j]=temp[j];
            }
            int[] index = null;
            int[] index2 = get(o);
            index = get(o);
            if(index.length>0) {
                index[index.length - 1]++;
                do {
                    index = up(index, index2);
                    Print(o, index, index.length - 1);
                    System.out.print(" ");

                } while (!isOver(index));
            }
        }
    }

    public static void Print(char o[][],int[] index,int i){
        if(i>0) {
            Print(o, index, i - 1);
        }
        System.out.print(o[i][index[i]]);
    }
}
