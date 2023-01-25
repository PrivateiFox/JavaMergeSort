package q1;

import java.util.ArrayList;
import java.util.List;

/**
 * Published: 08/01/2021
 *
 * @author Saar
 */
public class ConcurrentMergeSort {
    private final int thread_count;
    private final List<Thread> threads;
    private static final int WAIT_FOREVER = 0;

    /**
     * Merge sorts concurrently using multiple threads at once.
     * @param thread_count The amount of threads to utilise for the operation.
     */
    public ConcurrentMergeSort(int thread_count) {
        this.thread_count = thread_count;
        this.threads = new ArrayList<>();
    }

    /**
     * Sorts the given repository and returns an array of sorted integers from it.
     * @param repository The repository to sort.
     * @return An array of sorted integers.
     * @throws InterruptedException If a thread gets interrupted unexpectedly and cannot recover.
     */
    public int[] getSorted(Repository repository) throws InterruptedException {
        createAndStartMergeThreads(repository);
        waitForThreadsToFinish();
        return repository.getCollection();
    }

    /**
     * Busy waits for all threads to finish merging.
     * @throws InterruptedException If a thread gets interrupted unexpectedly and cannot recover.
     */
    private void waitForThreadsToFinish() throws InterruptedException {
        for (Thread thread : this.threads) {
            thread.join(WAIT_FOREVER);
        }
    }

    /**
     * Creates all threads and starts their merge-sort operation.
     * @param repository The repository to sort.
     */
    private void createAndStartMergeThreads(Repository repository) {
        for (int count=0; count < this.thread_count; count++){
            Thread thread = new Thread(new MergeSortThread(repository));
            this.threads.add(thread);
            thread.start();
        }
    }
}
