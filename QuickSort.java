package QuickSort_Java;

import java.util.*;

class Main {
    static int pivot;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.println("Select the basis of picking the pivot:\n1.first element\n2.random\n3.median of 3");
        pivot = read.nextInt();

        System.out.println("Select the partitionning method:\n1.Lumoto\n2.Hoare");
        int partitionning = read.nextInt();

        System.out.print("Enter Size of Array: ");
        int num = read.nextInt();
        int[] arraytest = new int[num];

        for (int i = 0; i < arraytest.length; i++) {
            System.out.print("Enter Element Number " + i + " in the Array: ");
            arraytest[i] = read.nextInt();
        }
        System.out.println();

        System.out.printf("Array before quicksort: ");
        for (int i : arraytest) {
            System.out.printf("%d ", i);
        }
        System.out.println("");

        quicksort(arraytest, 0, arraytest.length - 1, pivot, partitionning);

        System.out.printf("Array after quicksort:  ");
        for (int i : arraytest) {
            System.out.printf("%d ", i);
        }
    }

    public static void quicksort(int[] array, int left, int right, int pivot, int partitionning) {
        int p;
        switch (pivot) {
            case 1:
                p = left;
                break;
            case 2:
                p = getRandomNumber(left, right);
                break;
            case 3:
                p = median(array, left, right);
                break;
            default:
                p = left;
        }
        switch (partitionning) {
            case 1:
                Lomuto(array, left, right, p);
                break;
            case 2:
                sort(array, left, right, p);
                break;
            default:
                Lomuto(array, left, right, p);
        }
    }

    public static void sort(int[] arr, int l, int r, int piv/* , int par */) {
        int s = 0;
        if (l < r) {
            s = Hoare(arr, l, r, piv);
            quicksort(arr, l, s - 1, pivot, 2);
            quicksort(arr, s + 1, r, pivot, 2);

        }
    }

    public static int Lomuto(int[] arr, int l, int r, int piv) {
        swap(arr, l, piv);
        int p = arr[l];
        int s = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < p) {
                s = s + 1;
                swap(arr, i, s);
            }
        }
        swap(arr, s, l);
        if (unsorted(arr, l, s - 1))
            quicksort(arr, l, s - 1, pivot, 1);
        if (unsorted(arr, s + 1, r))
            quicksort(arr, s + 1, r, pivot, 1);
        return s;
    }

    public static int Hoare(int[] arr, int l, int r, int piv) {
        swap(arr, l, piv);
        int p = arr[l];
        int i = l + 1;
        int j = r;

        do {
            while (arr[i] < p && i + 1 != arr.length) {
                i++;
            }
            while (arr[j] > p) {
                j--;
            }
            swap(arr, i, j);

        } while (i < j);
        swap(arr, i, j);
        swap(arr, l, j);

        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int median(int[] arr, int l, int r) {
        int max = Math.max(Math.max(arr[l], arr[r / 2]), Math.max(arr[r / 2], arr[r]));
        int min = Math.min(Math.min(arr[l], arr[r / 2]), Math.min(arr[r / 2], arr[r]));

        if (arr[l] != min && arr[l] != max)
            return l;
        if (arr[r] != min && arr[r] != max)
            return r;
        return r / 2;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static boolean unsorted(int[] arr, int l, int r) {
        for (int i = l; i < r; i++) {
            if (arr[i] > arr[i + 1])
                return true;
        }
        return false;
    }
}