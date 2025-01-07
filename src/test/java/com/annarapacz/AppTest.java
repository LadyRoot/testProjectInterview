package com.annarapacz;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AppTest {

    @Test
    @DisplayName("Test if the array is initialized correctly")
    public void testInitializeArray() {
        int[][] current_array = new int[3][3];
        App.initializeArray(current_array, 1);

        int[][] expected_array = new int[3][3];
        expected_array[0][0] = 1;
        expected_array[0][1] = 1;
        expected_array[0][2] = 1;
        expected_array[1][0] = 1;
        expected_array[1][1] = 1;
        expected_array[1][2] = 1;
        expected_array[2][0] = 1;
        expected_array[2][1] = 1;
        expected_array[2][2] = 1;

        assertArrayEquals(expected_array, current_array, "The array should be initialized correctly");
    }


    @Test
    @DisplayName("Test if number of bugs is always the same after shuffle")
    public void testShuffleItemsTotalCannotChange() {

        // the intent is to make sure the total number of bugs in the array does not change
        int[][] current_array = new int[15][15];
        App.initializeArray(current_array, 1);
        int[][] target_array = new int[15][15]; // starts with 0


        for (int i=1; i<=5; i++) {
            App.shuffle_items(current_array, target_array);
            current_array = Arrays.copyOf(target_array, target_array.length);
            target_array = new int[15][15];
        }

        Assertions.assertEquals(15*15, sum_elements(current_array), "The sum of elements in the array should be 225");

        for (int i=1; i<=20; i++) {
            App.shuffle_items(current_array, target_array);
            current_array = Arrays.copyOf(target_array, target_array.length);
            target_array = new int[15][15];
        }
        Assertions.assertEquals(15*15, sum_elements(current_array), "The sum of elements in the array should be 225");
    }


    @Test
    @DisplayName("Test if there are no negative numbers in array after shuffle")
    public void testNegativeNumbersNotPresent()
    {
        int[][] current_array = new int[3][3];
        App.initializeArray(current_array, 1);
        int[][] target_array = new int[3][3]; // starts with 0

        for (int i=1; i<=5; i++) {
            App.shuffle_items(current_array, target_array);
            current_array = Arrays.copyOf(target_array, target_array.length);
            target_array = new int[3][3];
        }

        for (int i = 0; i < current_array.length; i++) {
            for (int j = 0; j < current_array[i].length; j++) {
                assertTrue(current_array[i][j] >= 0, "There should be no negative numbers in the array");
            }
        }
    }


    private int sum_elements(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
}
