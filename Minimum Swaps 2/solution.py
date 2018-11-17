#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    swaps = 0
    pos = [None] * (len(arr) + 1)
    for i, v in enumerate(arr):
        pos[v] = i
    for v in range(len(arr), 0, -1):
        p = pos[v]
        if p + 1 != v:
            swaps += 1
            pos[v] = v - 1
            pos[arr[v - 1]] = p
            tmp = arr[v - 1]
            arr[v - 1] = v
            arr[p] = tmp
    return swaps

if __name__ == '__main__':
    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = minimumSwaps(arr)

    print(res)
