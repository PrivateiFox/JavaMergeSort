package q1;

import java.util.List;

/**
 * Published: 08/01/2021
 *
 * @author Saar
 */
public class MergeSortThread implements Runnable {
    private final Repository repository;
    private static final int FIRST_ITEM_IN_COLLECTION = 0;
    private static final int SECOND_ITEM_IN_COLLECTION = 1;
    private MergeSort mergeSort;

    /**
     * Implements a standalone merge-sort Thread, that reads and writes to/from a given Repository.
     * @param repository The repository to sort.
     */
    public MergeSortThread(Repository repository) {
        this.repository = repository;
    }

    /**
     * Implements a standalone java merge-sort thread that can read/write from a given Repository.
     */
    @Override
    public void run() {
        this.mergeSort = new MergeSort();
        List<int[]> collections;
        while (true) {
            collections = repository.getWork();
            if (collections == null)
                return;
            if (collections.isEmpty())
                continue;
            getAndSortItems(collections);
            repository.putWork(collections.get(0));
        }
    }

    /**
     * Reads all given items from collections and merge-sorts them until there's only one, merged, item left.
     * @param collections The collections to merge-sort.
     */
    private void getAndSortItems(List<int[]> collections) {
        while (collections.size() > 1) {
            collections.add(this.mergeSort.mergeSortTwoArrays(
                    collections.get(FIRST_ITEM_IN_COLLECTION),
                    collections.get(SECOND_ITEM_IN_COLLECTION))
            );
            collections.remove(FIRST_ITEM_IN_COLLECTION);
            collections.remove(FIRST_ITEM_IN_COLLECTION);
        }
    }
}
