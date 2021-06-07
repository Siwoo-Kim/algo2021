package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] board;
    private static int answer;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        Shark shark = null;
        for (int i=0; i<N; i++) {
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<N; j++) {
                if (board[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                }
            }
        }
        board[shark.y][shark.x] = 0;
        while (bfs(shark)) {
            for (int[] e: board)
                System.out.println(Arrays.toString(e));
            System.out.println();
            shark.eat++;
            if (shark.size == shark.eat) {
                shark.size++;
                shark.eat = 0;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs(Shark shark) {
        Queue<Point> q = new LinkedList<>();
        q.add(shark);
        int[][] distance = new int[N][N];
        distance[shark.y][shark.x] = 0;
        List<Point> result = new ArrayList<>();
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (Point d: Point.D) {
                Point w = new Point(v.y + d.y, v.x + d.x);
                if (!w.valid()) continue;
                if (distance[w.y][w.x] != 0) continue;
                if (board[w.y][w.x] > shark.size) continue;
                distance[w.y][w.x] = distance[v.y][v.x] + 1;
                q.add(w);
                if (shark.eatable(board[w.y][w.x]))
                    result.add(new Point(w.y, w.x, distance[w.y][w.x]));
            }
        }
        if (result.isEmpty())
            return false;
        else {
            Collections.sort(result);
            Point lastPoint = result.get(0);
            shark.y = lastPoint.y;
            shark.x = lastPoint.x;
            answer += distance[lastPoint.y][lastPoint.x];
            board[lastPoint.y][lastPoint.x] = 0;
            return true;
        }
    }

    private static class Point implements Comparable<Point> {
        private static Point[] D = {
                new Point(0, 1), new Point(0, -1),
                new Point(1, 0), new Point(-1, 0),
        };

        int y, x, distance;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }

        public boolean valid() {
            return y >= 0 && y < N && x >= 0 && x < N;
        }

        @Override
        public int compareTo(Point o) {
            if (distance == o.distance) {
                if (y == o.y)
                    return Integer.compare(x, o.x);
                return Integer.compare(y, o.y);
            }
            return Integer.compare(distance, o.distance);
        }
    }

    private static class Shark extends Point {
        int size, eat;

        public Shark(int y, int x, int size, int eat) {
            super(y, x);
            this.size = size;
            this.eat = eat;
        }

        public boolean eatable(int pray) {
            if (pray == 0) return false;
            return pray < size;
        }
    }
}
