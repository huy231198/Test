package Bai1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class bai1 {

    public static void main(String[] args) {
        String[][] map = {
                {"*", "*", "*", "*",},
                {"*", "*", "*", "*",},
                {"*", "*", "*", "*",},
                {"*", "*", "*", "*",}
        };

        Scanner scanner = new Scanner(System.in);

        int count = 2;

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++)
                points.add(new Point(i, j));

        Point point = random(points);

        int playerX = point.x;
        int playerY = point.y;

        point = random(points);

        int enemyFirstX = point.x;
        int enemyFirstY = point.y;

        point = random(points);

        int enemySecondX = point.x;
        int enemySecondY = point.y;

        point = random(points);
        int foodFirstX = point.x;
        int foodFirstY = point.y;

        point = random(points);
        int foodSecondX = point.x;
        int foodSecondY = point.y;

        map[enemyFirstX][enemyFirstY] = "G";
        map[playerX][playerY] = "P";
        map[enemySecondX][enemySecondY] = "G";
        map[foodFirstX][foodFirstY] = "F";
        map[foodSecondX][foodSecondY] = "F";

        while (true) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[j][i] + " ");
                }
                System.out.println();
            }

            System.out.println("Nhap ky tu ban phim: ");
            String key = scanner.next();

            map[playerX][playerY] = "*";

            switch (key) {
                case "w":
                    playerY -= 1;
                    break;
                case "s":
                    playerY += 1;
                    break;
                case "a":
                    playerX -= 1;
                    break;
                case "d":
                    playerX += 1;
                    break;
            }
            if (playerY == -1) playerY = map.length - 1;
            if (playerY == map.length) playerY = 0;
            if (playerX == -1) playerX = map.length - 1;
            if (playerX == map.length) playerX = 0;
            if (playerX == foodFirstX && playerY == foodFirstY) {
                foodFirstX = 0;
                foodFirstY = 0;
            }
            if (playerX == foodSecondX && playerY == foodSecondY) {
                foodSecondX = 0;
                foodSecondY = 0;
            }
            if (foodFirstX == 0 && foodFirstY == 0 && foodSecondX == 0 && foodSecondY == 0) {
                System.out.println("YOU WON");
                return;
            }

            if ((playerX == enemyFirstX && playerY == enemyFirstY)
                    || (playerX == enemySecondX && playerY == enemySecondY))
//	                    || (e1x == playerX && e1y == playerY)
//	                    || (e2x == playerX && e2y == playerY))
            {
                count -= 1;
                if (count == 1) {
                    System.out.println("LIFE LEFT : 1");
                }

                if (count == 0) {
//	                System.out.println("LIFE LEFT : 0");

                    System.out.println("YOU LOST");
                    return;
                }
            }


            map[enemyFirstX][enemyFirstY] = "*";
            map[enemySecondX][enemySecondY] = "*";
            map[foodFirstX][foodFirstY] = "F";
            map[foodSecondX][foodSecondY] = "F";
            map[0][0] = "*";


            enemyFirstX = (enemyFirstX + 1) % map.length;
            map[enemyFirstX][enemyFirstY] = "G";


            enemySecondY = (enemySecondY + 1) % map.length;
            map[enemySecondX][enemySecondY] = "G";
            map[playerX][playerY] = "P";

        }
    }

    static Point random(ArrayList<Point> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.remove(index);
    }
}