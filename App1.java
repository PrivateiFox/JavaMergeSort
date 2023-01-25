package q1;

import java.util.Arrays;

/**
 * Published: 08/01/2021
 *
 * @author Saar
 */
public class App1 {
    private final static int COLLECTION_SIZE = 100;
    private final static int THREAD_COUNT = 10;

    /**
     * A generic main function to launch the app.
     * @param args Additional launch arguments - unused.
     */
    public static void main(String[] args) throws InterruptedException {
        Repository repository = new Repository(COLLECTION_SIZE);
        ConcurrentMergeSort concurrentMergeSort = new ConcurrentMergeSort(THREAD_COUNT);
        System.out.println(Arrays.toString(concurrentMergeSort.getSorted(repository))); // print the sorted array
    }
}
