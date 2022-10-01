#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "L1.h"

int compare(int length, int S, int out)
{
    // Create arrays for sorting
    int *arr0 = createInputs(length, -0x3FFFFFFF / 2, 0x3FFFFFFF / 2);

    printf("\nS value of: %d, N of value: %d\n", S, length);

    int *arr1 = malloc(length * sizeof(int));
    for (int i = 0; i < length; i++)
    {
        arr1[i] = arr0[i];
    }

    printf("Starting...\n");

    clock_t t0 = clock();

    // Merge sort
    int mergeSortComparisons = mergeSort(arr1, length);
    clock_t t1 = clock();
    printf("MergeSort took %d comparisons in %.2fs :>\n", mergeSortComparisons, ((double)(t1 - t0)) / CLOCKS_PER_SEC);

    // Insertion merge sort
    int insertionMergeSortComparisons = insertionMergeSort(arr0, length, S);
    clock_t t2 = clock();
    printf("InsertionMergeSort took %d comparisons in %.2fs :3 \n", insertionMergeSortComparisons, ((double)(t2 - t1)) / CLOCKS_PER_SEC);

    // Final comment
    if (isSorted(arr1, length) && isSorted(arr0, length))
    {
        printf("All is sorted yay :D\n");
        return 1;
    }
    else
    {
        printf("Oh noes some are not sorted :(\n");
        return 0;
    }
}

// DEMO TIME :3
int main(int argc, char const *argv[])
{
    int length;
    int S;

    // // Demo same n = 1_000_000, different S from 1, 2, 3, 4, 5, 10, 15, ... 100
    // length = 1000000;

    // for (S = 1; S <= 5; S++)
    // {
    //     compare(length, S, 0);
    // }
    // for (S = 5; S <= 100; S += 5)
    // {
    //     compare(length, S, 0);
    // }

    // // Demo same S = 5, n = 2 ^ i, for i in range(1, 23), will go to 8 mil
    // S = 5;
    // for (int i = 1, length = 2; length <= 10000000; i++, length = pow(2, i)) {
        
    // }

    compare(10000000, 5, 1);

    return 0;
}
