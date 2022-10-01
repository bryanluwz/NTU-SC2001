#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

int *createInputs(int length, int min, int max);
int isSorted(int *arr, int length);
int insertionSort(int *arr, int length);
int mergeSort(int *arr, int length);
int insertionMergeSort(int *arr, int length, int threshold);

int *createInputs(int length, int min, int max)
{
    int *arr = (int *)malloc(length * sizeof(int));
    int num;
    for (int i = 0; i < length; i++)
    {
        // rand() only goes from 0 to 0x7FFF, aka 15bit, we want 32bit, so we take 2 rand()
        num = rand() * rand();
        arr[i] = (num % (max - min)) + min;
    }
    return arr;
}

int isSorted(int *arr, int length)
{
    for (int i = 0; i < length - 1; i++)
    {
        if (arr[i] > arr[i + 1])
        {
            return 0;
        }
    }
    return 1;
}

int insertionSort(int *arr, int length)
{
    int comparisons = 0;
    int key;
    int i, j;

    for (i = 1; i < length; i++)
    {
        key = arr[i];
        j = i - 1;
        while ((j >= 0) && (key < arr[j]))
        {
            comparisons++;
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }

    return comparisons;
}

int mergeSort(int *arr, int length)
{
    if (length <= 1)
    {
        return 0;
    }

    int comparisons = 0;

    int mid = length / 2;

    int leftLength = mid;
    int rightLength = length - mid;

    // Copy array
    int *leftArray = (int *)malloc(leftLength * sizeof(int));
    int *rightArray = (int *)malloc(rightLength * sizeof(int));
    for (int i = 0; i < leftLength; i++)
    {
        leftArray[i] = arr[i];
    }
    for (int i = 0; i < rightLength; i++)
    {
        rightArray[i] = arr[mid + i];
    }

    comparisons += mergeSort(leftArray, leftLength);
    comparisons += mergeSort(rightArray, rightLength);

    int i, j, k;
    i = j = k = 0;

    while ((i < leftLength) && (j < rightLength))
    {
        if (leftArray[i] < rightArray[j])
        {
            arr[k++] = leftArray[i++];
        }
        else
        {
            arr[k++] = rightArray[j++];
        }
        comparisons++;
    }

    while (i < mid)
    {
        arr[k++] = leftArray[i++];
    }

    while (j < (length - mid))
    {
        arr[k++] = rightArray[j++];
    }

    return comparisons;
}

int insertionMergeSort(int *arr, int length, int threshold)
{
    if (length <= threshold)
    {
        return insertionSort(arr, length);
    }

    int comparisons = 0;
    int mid = length / 2;

    int leftLength = mid;
    int rightLength = length - mid;

    // Copy array
    int *leftArray = (int *)malloc(leftLength * sizeof(int));
    int *rightArray = (int *)malloc(rightLength * sizeof(int));
    for (int i = 0; i < leftLength; i++)
    {
        leftArray[i] = arr[i];
    }
    for (int i = 0; i < rightLength; i++)
    {
        rightArray[i] = arr[mid + i];
    }

    comparisons += insertionMergeSort(leftArray, mid, threshold);
    comparisons += insertionMergeSort(rightArray, (length - mid), threshold);

    int i, j, k;
    i = j = k = 0;

    while ((i < mid) && (j < (length - mid)))
    {
        if (leftArray[i] < rightArray[j])
        {
            arr[k++] = leftArray[i++];
        }
        else
        {
            arr[k++] = rightArray[j++];
        }
        comparisons++;
    }

    while (i < mid)
    {
        arr[k++] = leftArray[i++];
    }

    while (j < (length - mid))
    {
        arr[k++] = rightArray[j++];
    }

    return comparisons;
}

int main()
{
    // FILE *fptr;
    // fptr = fopen("different_n_1000000.csv", "w");
    // fprintf(fptr, "S value, N value, MergeSort, InsertionMergeSort, Difference\n");

    // Random seed
    srand(time(NULL));

    int length = 1;

    int S = 5; // S here

    // Change here
    for (int i = 1; length < 10000000; i++)
    {
        length = pow(2, i);
        if (length > 10000000)
            length = 10000000;
        printf("\nS value of: %d, N of value: %d\n", S, length);

        // int *arr1 = malloc(length * sizeof(int));
        int *arr1 = createInputs(length, 1, 10000000);
        int *arr2 = malloc(length * sizeof(int));
        for (int i = 0; i < length; i++)
        {
            arr2[i] = arr1[i];
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

        // fprintf(fptr, "%d, %d, %d, %d, %d\n", S, length, mergeSortComparisons, insertionMergeSortComparisons, insertionMergeSortComparisons - mergeSortComparisons);

        // Print out the sorted array in interval
        // int maxPrintCount = 100;
        // int interval = length / maxPrintCount < 1 ? 1 : length / maxPrintCount;

        // printf("Here are some of the values sorted: \n");
        // for (int i = 0; i < length; i += interval)
        //     printf("%d ", arr2[i]);
        printf("\n\n");
        free(arr1);
        free(arr2);
    }

    return 0;
}
