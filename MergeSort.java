package q1;

/**
 * Published: 08/01/2021
 *
 * @author Saar
 */
public class MergeSort {
    public MergeSort() {

    }

    /**
     * Given two integer arrays, returns a merged array of the two, sorted by smallest->largest.
     * @param arrayOne The first integer array to join
     * @param arrayTwo The second integer array to join
     * @return A merged integer array.
     */
    public int[] mergeSortTwoArrays(int[] arrayOne, int[] arrayTwo){
        return mergeTwoIntArrays(mergeSortArray(arrayOne), mergeSortArray(arrayTwo));
    }

    /**
     * Given an integer array, uses recursion to merge-sort the items within from smallest->largest.
     * @param array The integer array to sort.
     * @return The sorted integer array.
     */
    public int[] mergeSortArray(int[] array) {
        if (array.length == 1)
            return array;

        int[] arrayOne = new int[array.length / 2];
        int[] arrayTwo = new int[array.length - arrayOne.length];

        System.arraycopy(array, 0, arrayOne, 0, arrayOne.length);
        System.arraycopy(array, arrayOne.length, arrayTwo, 0, arrayTwo.length);
        arrayOne = mergeSortArray(arrayOne);
        arrayTwo = mergeSortArray(arrayTwo);

        return mergeTwoIntArrays(arrayOne, arrayTwo);
    }

    /**
     * Implements the background array merging and sorting logic.
     * @param arrayOne The first integer array part to join
     * @param arrayTwo The second integer array part to join
     * @return A merged integer part of an array.
     */
    private int[] mergeTwoIntArrays(int[] arrayOne, int[] arrayTwo) {
        int[] mergedArray = new int[arrayOne.length + arrayTwo.length];

        int positionOne = 0, positionTwo = 0, mergedPosition = 0;
        while (positionOne < arrayOne.length && positionTwo < arrayTwo.length) {
            if (arrayOne[positionOne] < arrayTwo[positionTwo])
                mergedArray[mergedPosition++] = arrayOne[positionOne++];
            else
                mergedArray[mergedPosition++] = arrayTwo[positionTwo++];
        }

        if (positionOne < arrayOne.length) System.arraycopy(arrayOne, positionOne, mergedArray, mergedPosition,
                mergedArray.length - mergedPosition);
        else System.arraycopy(arrayTwo, positionTwo, mergedArray, mergedPosition,
                mergedArray.length - mergedPosition);

        return mergedArray;
    }
}
