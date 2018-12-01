#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the luckBalance function below.
def luckBalance(k, contests):
    importants = []
    L = 0
    for i in range(len(contests)):
        c = contests[i]
        if c[1] == 1:
            importants.append((c[0], c[1]))
        else:
            L += c[0]
    j = 0

    importants.sort()
    for i in range(len(importants) - 1, -1, -1):
        if j < k:
            L += importants[i][0]
        else:
            L -= importants[i][0]
        j += 1

    return L


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nk = input().split()

    n = int(nk[0])

    k = int(nk[1])

    contests = []

    for _ in range(n):
        contests.append(list(map(int, input().rstrip().split())))

    result = luckBalance(k, contests)
    print(result)

    #fptr.write(str(result) + '\n')

    #fptr.close()
