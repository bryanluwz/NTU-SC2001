#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "L1.h"

int main()
{
    FILE *fptr;
    fptr = fopen("different_n_1000000.csv", "w");
    fprintf(fptr, "S value, MergeSort, InsertionMergeSort, Difference\n");

    // Random seed
    srand(time(NULL));

    int length = 1000;

    int S = 5; // S here

    // Create arrays for sorting
    int *arr0 = createInputs(length, -0x3FFFFFFF / 2, 0x3FFFFFFF / 2);

    // Change here
    // for (length=1000000; length <= 10000000; length += 1000000)
    // {
    printf("\nS value of: %d, N of value: %d\n", S, length);

    int *arr1 = malloc(length * sizeof(int));
    int *arr2 = malloc(length * sizeof(int));
    for (int i = 0; i < length; i++)
    {
        arr1[i] = arr0[i];
        arr2[i] = arr0[i];
    }

    // No insertion sort cause it slow
    printf("Starting...\n");

    clock_t t0 = clock();

    // Merge sort
    int mergeSortComparisons = mergeSort(arr1, length);
    clock_t t1 = clock();
    printf("MergeSort took %d comparisons in %.2fs :>\n", mergeSortComparisons, ((double)(t1 - t0)) / CLOCKS_PER_SEC);

    // Insertion merge sort
    int insertionMergeSortComparisons = insertionMergeSort(arr2, length, S);
    clock_t t2 = clock();
    printf("InsertionMergeSort took %d comparisons in %.2fs :3 \n", insertionMergeSortComparisons, ((double)(t2 - t1)) / CLOCKS_PER_SEC);

    // Final comment
    if (isSorted(arr1, length) && isSorted(arr2, length))
    {
        printf("All is sorted yay :D\n");
    }
    else
    {
        printf("Oh noes some are not sorted :(\n");
    }

    fprintf(fptr, "%d, %d, %d, %d\n", S, mergeSortComparisons, insertionMergeSortComparisons, insertionMergeSortComparisons - mergeSortComparisons);

    // Print out the sorted array in interval
    int maxPrintCount = 500;
    int interval = length / maxPrintCount < 1 ? 1 : length / maxPrintCount;

    for (int i = 0; i < length; i += interval)
        printf("%d ", arr2[i]);

    return 0;
}
