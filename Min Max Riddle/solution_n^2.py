#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the riddle function below.
def riddle(arr):
    out = [-1] * len(arr)

    mem = arr.copy()
    for _, v in enumerate(arr):
        out[0] = max(out[0], v)

    for i in range(1, len(arr)):
        for j in range(0, len(arr) - i):
            temp = min(mem[j], mem[j + 1])
            out[i] = max(out[i], temp)
            mem[j] = temp

    return out

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = riddle(arr)

    print(' '.join(map(str, res)))
    #fptr.write('\n')

    #fptr.close()
