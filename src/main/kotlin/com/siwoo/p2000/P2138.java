package com.siwoo.p2000;
import java.util.Arrays;
import java.util.Scanner;

public class P2138 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a1 = new int[N];
        int[] a2 = new int[N];

        scanner.nextLine();
        String line = scanner.nextLine();
        for (int i=0; i<N; i++)
            a2[i] = a1[i] = Math.abs('0' - line.charAt(i));

        int[] b = new int[N];
        line = scanner.nextLine();
        for (int i=0; i<N; i++)
            b[i] = Math.abs('0' - line.charAt(i));

        int result1 = find(a1, b);
        click(a2, 0);
        int result2 = find(a2, b);
        if (result2 != Integer.MAX_VALUE)
            result2++;

        if (result1 == Integer.MAX_VALUE
                && result2 == Integer.MAX_VALUE)
            System.out.println(-1);
        else {
            System.out.println(Math.min(result1, result2));
        }
    }

    private static int find(int[] a, int[] b) {
        int cnt = 0;
        for (int i=0; i<a.length-1; i++)
            if (a[i] != b[i]) {
                click(a, i + 1);
                cnt++;
            }
        return Arrays.equals(a, b)? cnt: Integer.MAX_VALUE;
    }

    private static void click(int[] a, int index) {
        for (int i=-1; i<2; i++) {
            if (index+i < 0) continue;
            if (index+i >= a.length) continue;
            a[index+i] = a[index+i] == 0? 1: 0;
        }
    }
}