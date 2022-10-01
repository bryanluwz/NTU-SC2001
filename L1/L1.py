import random
import time


def create_inputs(length, min_num=0, max_num=1000000):
    """Create inputs of length length between min_num and max_num"""
    return [random.randint(min_num, max_num) for _ in range(length)]


def is_sorted(arr):
    """Returns True if array is sorted in ascending order"""
    for i in range(len(arr) - 1):
        if arr[i] > arr[i + 1]:
            return False
    return True


def insertion_sort(arr):
    """Insertion sort returns total key comparisons"""
    # Iterate through array elements, compare with whatever before
    comparisons = 0
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and key < arr[j]:
            comparisons += 1
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key

    return comparisons


def merge_sort(arr):
    """Merge sort returns total key comparisons"""
    if len(arr) <= 1:
        return 0

    comparisons = 0

    # Split into left and right
    mid = len(arr) // 2
    l_arr = arr[:mid]
    r_arr = arr[mid:]

    # Merge sort left and right
    comparisons += merge_sort(l_arr)
    comparisons += merge_sort(r_arr)

    # Three pointers for l_arr, r_arr, and sorted_arr
    i = j = k = 0

    # Compare left and right
    while i < len(l_arr) and j < len(r_arr):
        if l_arr[i] < r_arr[j]:
            arr[k] = l_arr[i]
            i += 1
        else:
            arr[k] = r_arr[j]
            j += 1
        comparisons += 1
        k += 1

    # Throw in whatever that's left, should only have one array left
    while i < len(l_arr):
        arr[k] = l_arr[i]
        i += 1
        k += 1

    while j < len(r_arr):
        arr[k] = r_arr[j]
        j += 1
        k += 1

    return comparisons


def insertion_merge_sort(arr, threshold=10):
    """Merge sort but when subarray is length smoler than threshold, use insertion sort.
    Returns total comparisons"""
    # When array is smol enough use insertion sort instead
    if len(arr) <= threshold:
        return insertion_sort(arr)

    comparisons = 0

    # Split into left and right
    mid = len(arr) // 2
    l_arr = arr[:mid]
    r_arr = arr[mid:]

    # Merge sort left and right
    comparisons += insertion_merge_sort(l_arr, threshold)
    comparisons += insertion_merge_sort(r_arr, threshold)

    # Three pointers for l_arr, r_arr, and sorted_arr
    i = j = k = 0

    # Compare left and right
    while i < len(l_arr) and j < len(r_arr):
        if l_arr[i] < r_arr[j]:
            arr[k] = l_arr[i]
            i += 1
        else:
            arr[k] = r_arr[j]
            j += 1
        comparisons += 1
        k += 1

    # Throw in whatever that's left, should only have one array left
    while i < len(l_arr):
        arr[k] = l_arr[i]
        i += 1
        k += 1

    while j < len(r_arr):
        arr[k] = r_arr[j]
        j += 1
        k += 1

    return comparisons


if __name__ == "__main__":
    THRESHOLD = 5
    INPUTS = 10000
    MIN_INPUT_NUMBER = 0
    MAX_INPUT_NUMBER = 10000

    # Create arrays for sorting
    arr1 = create_inputs(length=INPUTS, min_num=MIN_INPUT_NUMBER, max_num=MAX_INPUT_NUMBER)
    arr2 = arr1.copy()
    arr3 = arr1.copy()

    # Start testing the algorithms
    t0 = time.time()

    merge_sort_comparisons = merge_sort(arr1)
    t1 = time.time()

    insertion_sort_comparisons = insertion_sort(arr2)
    t2 = time.time()

    insertion_merge_sort_comparisons = insertion_merge_sort(arr3, threshold=THRESHOLD)
    t3 = time.time()

    print()
    print(f"Merge sort needs {merge_sort_comparisons} comparisons, took {t1 - t0:.4f}s.")
    print(f"Insertion sort needs {insertion_sort_comparisons} comparisons, took {t2 - t1:.4f}s.")
    print(f"Hyrbid sort thingamajic needs {insertion_merge_sort_comparisons} comparisons, took {t3 - t2:.4f}s.")

    print("All are sorted :>" if is_sorted(arr1) and is_sorted(arr2) and is_sorted(arr3) else "Oh noes 0.o")
    print()
