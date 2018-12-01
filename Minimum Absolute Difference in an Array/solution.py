#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumAbsoluteDifference function below.
def minimumAbsoluteDifference(arr):
    arr.sort()
    out = arr[1] - arr[0]
    for i in range(2, len(arr)):
        temp = arr[i] - arr[i - 1]
        if temp < out:
            out = temp
    return out

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    result = minimumAbsoluteDifference(arr)
    print(result)
    #fptr.write(str(result) + '\n')

    #fptr.close()
