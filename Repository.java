package q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Published: 08/01/2021
 *
 * @author Saar
 */
public class Repository {
    private final static int MAXIMUM_INT_SIZE = 100;
    private final static Random random = new Random();
    private final int collectionSize;
    private final List<int[]> collection;
    private static final int EMPTY_SIZE = 0;
    private static final int SORTED_COLLECTION_SIZE = 1;
    private static final int FIRST_ITEM_IN_COLLECTION = 0;
    private int threadCount = 0;

    /**
     * Wraps the process of generating a random list of integers, splitting them into separate arrays for merge-sorting
     * and implements synchronized read/write.
     * @param collectionSize The size of the collection to generate.
     */
    public Repository(int collectionSize) {
        this.collectionSize = collectionSize;
        this.collection = Collections.synchronizedList(new ArrayList<>());
        populateRepository();
    }

    /**
     * Generates random integers to add to the list.
     */
    private void populateRepository() {
        if (this.collectionSize == EMPTY_SIZE)
            this.collection.add(new int[EMPTY_SIZE]);
        else
            for (int position = 0; position < this.collectionSize; position++)
                this.collection.add(new int[]{random.nextInt(MAXIMUM_INT_SIZE)});
    }

    /**
     * Gets items to merge-sort(in a synchronized fashion)
     * @return A list of integer array to merge-sort
     */
    public List<int[]> getWork() {
        synchronized (this.collection) {
            if (this.collection.size() > SORTED_COLLECTION_SIZE) {
                this.threadCount++; // log new worker getting work to do
                List<int[]> returnedList = new ArrayList<>();
                returnedList.add(this.collection.get(FIRST_ITEM_IN_COLLECTION));
                this.collection.remove(FIRST_ITEM_IN_COLLECTION);
                returnedList.add(this.collection.get(FIRST_ITEM_IN_COLLECTION));
                this.collection.remove(FIRST_ITEM_IN_COLLECTION);
                return returnedList;
            }
            if (this.threadCount == EMPTY_SIZE)
                return null; // There's no more work to do
            else
                return new ArrayList<>(); // There might still be work to do, tell the thread it should busy wait.
        }
    }

    /**
     * Adds items back to the merging list(in a synchronized fashion)
     * @param mergedCollections The integer array to add back.
     */
    public void putWork(int[] mergedCollections) {
        synchronized (this.collection) {
            this.threadCount--; // log worker is done
            this.collection.add(mergedCollections);
        }
    }

    /**
     * Gets the first collection from the Repository.
     * it should hopefully be the whole, merged, repository.
     * @return An array of sorted-merged integers.
     */
    public int[] getCollection() {
        synchronized (this.collection) {
            if (this.collection.size() == EMPTY_SIZE) {
                return new int[EMPTY_SIZE];
            }
            return this.collection.get(0);
        }
    }
}
