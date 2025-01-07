package com.annarapacz;

import java.util.Arrays;
import java.util.Random;


public class App {

    private static final int square_size = 3;

    public static void main(String[] args) {

        int[][] current_array = new int[square_size][square_size];
        initializeArray(current_array, 1);
        int[][] target_array = new int[square_size][square_size]; // starts with 0s


        System.out.println("Initial array:");
        printCurrentArray(current_array);

        // we will now shuffle due to bird arrival
        for (int i=1; i<=100; i++) {
            shuffle_items(current_array, target_array);
            current_array = Arrays.copyOf(target_array, target_array.length);
            target_array = new int[square_size][square_size];
            if (i == 25 || i == 50 || i == 100) {
                System.out.println("\nArray after " + i + " shuffles:" );
                printCurrentArray(current_array);
            }

        }
        System.out.println("\n=================\n");

        // the average number of beetles per occupied square
        System.out.println("Average number of bugs per occupied field: " + String.format("%.3f", calculateAverage(current_array)));

        // the square(s) with the highest beetle population
        System.out.println("The square(s) with the highest beetle population: " + findMax(current_array));
    }

    private static String findMax(int[][] arr2) {
        int max = 0;
        String pos = "";
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                if (arr2[i][j] > max) {
                    max = arr2[i][j];
                    pos = "[" + i + ", " + j + "]";
                }
                else if (arr2[i][j] == max) {
                    pos += ", [" + i + ", " + j + "]";
                }
            }
        }
        return pos;
    }

    static void shuffle_items(int[][] arr, int[][] arr2) {
        // we are going to shuffle not only fields,but also items
        // so for each field and each item we need to move them to other fields
        // this is because the bugs on the fields are not glued together, but they can freely move to any direction
        // i.e. field with 3 bugs can move bugs to one of the 8 fields around it, each bug separately

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                    int k = arr[i][j];
                    while (k > 0) {
                        move_item(arr, arr2, i, j);
                        k--;
                }
            }
        }

    }

    private static void move_item(int[][] current_array, int[][] target_array, int i, int j) {
        // we need to move the item to one of the 8 fields around it
        //       |1|2|3|
        //       |4|x|5|  x = [position i, j]
        //       |6|7|8|
        Random random = new Random();
        int randomNumber = random.nextInt(8) + 1;

        // we need to check if the field is out of bounds
        // if it is, we need to choose a new field again
        switch (randomNumber) {
            case 1:
                if (i - 1 < 0 || j - 1 < 0) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i - 1][j - 1] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 2:
                if (i - 1 < 0) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i - 1][j] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 3:
                if (i - 1 < 0 || j + 1 >= current_array[i].length) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i - 1][j + 1] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 4:
                if (j - 1 < 0) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i][j - 1] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 5:
                if (j + 1 >= current_array[i].length) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i][j + 1] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 6:
                if (i + 1 >= current_array.length || j - 1 < 0) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i + 1][j - 1] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 7:
                if (i + 1 >= current_array.length) {
                    move_item(current_array, target_array, i, j);
                } else {
                    target_array[i + 1][j] += 1;
                    current_array[i][j] -= 1;
                }
                break;
            case 8:
                if (i + 1 >= current_array.length || j + 1 >= current_array[i].length) {
                    move_item(current_array, target_array, i, j); }
                else {
                    target_array[i + 1][j + 1] += 1;
                }
                break;
        }
    }

    private static void printCurrentArray(int[][] arr) {
        // prints number of beetles per square; dumps the table
        System.out.println("=================");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("|");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    static void initializeArray(int[][] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = num;
            }
        }
    }

    private static double calculateAverage(int[][] arr) {
        double sum = 0;
        int numberOfOccupied = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    sum += arr[i][j];
                    numberOfOccupied++;
                }
            }
        }
        return sum / numberOfOccupied;
    }

}
