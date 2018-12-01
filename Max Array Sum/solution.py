#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the maxSubsetSum function below.
def maxSubsetSum(arr):
    a = None
    b = None

    for _, v in enumerate(arr):
        if a == None:
            a = v
        elif b == None:
            b = a
            a = v
        else:
            new = max(b + v, b, a, v)
            if new == b + v:
                b = max(a, b)
                a = new
            elif new == b:
                a = max(b + v, v)
            elif new == a:
                temp = a
                a = max(b + v, v)
                b = temp
            else:
                b = a
                a = v

    if b == None:
        return a
    return max(a, b)


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = maxSubsetSum(arr)

    print(res)
    #fptr.write(str(res) + '\n')

    #fptr.close()

