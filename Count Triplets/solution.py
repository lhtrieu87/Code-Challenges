#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the countTriplets function below.
def countTriplets(arr, r):
    indices = {}
    counts = []
    c = 0

    cache2 = {}
    out = 0

    for j, v in enumerate(arr):
        if v % r == 0:
            p1 = v // r
            k1 = indices.get(p1, None)
            if k1 == None:
                cache2[v] = 0
            else:
                out += cache2.get(p1, 0)
                cache2[v] = cache2.get(v, 0) + counts[k1]

        i = indices.get(v, None)
        if i == None:
            indices[v] = c
            counts.append(1)
            c += 1
        else:
            counts[i] += 1

    return out


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))

    ans = countTriplets(arr, r)

    #fptr.write(str(ans) + '\n')

    #fptr.close()

    print(ans)

