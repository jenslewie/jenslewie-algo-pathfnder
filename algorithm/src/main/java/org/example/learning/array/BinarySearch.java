package org.example.learning.array;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8, 9};

        int result = binarySearch(arr, 5);
        System.out.println("Target 5 is located at: " + result);
        result = binarySearch(arr, 10);
        System.out.println("Target 10 is located at: " + result);

        result = binarySearchForLeftBoundary(arr, 5);
        System.out.println("Left boundary of target 5 is located at: " + result);
        result = binarySearchForLeftBoundary(arr, 10);
        System.out.println("Left boundary of target 5 is located at: " + result);

        result = binarySearchForRightBoundary(arr, 5);
        System.out.println("Right boundary of target 5 is located at: " + result);
        result = binarySearchForRightBoundary(arr, 10);
        System.out.println("Right boundary of target 5 is located at: " + result);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearchForLeftBoundary(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (left < 0 || left >= arr.length) {
            return -1;
        }
        return arr[left] == target ? left : -1;
    }

    public static int binarySearchForRightBoundary(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right < 0 || right >= arr.length) {
            return -1;
        }
        return arr[right] == target ? right : -1;
    }

}
