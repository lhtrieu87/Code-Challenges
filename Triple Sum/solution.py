#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the triplets function below.
def triplets(a, b, c):
    a.sort()
    b.sort()
    c.sort()

    count = 0

    i = 0
    k = 0
    left = 0
    right = 0
    for j in range(len(b)):
        if j > 0 and b[j] == b[j - 1]:
            continue

        while i < len(a) and a[i] <= b[j]:
            if left == 0 or a[i] > a[i - 1]:
                left += 1
            i += 1
        while k < len(c) and c[k] <= b[j]:
            if right == 0 or c[k] > c[k - 1]:
                right += 1
            k += 1

        count += left * right

    return count

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    lenaLenbLenc = input().split()

    lena = int(lenaLenbLenc[0])

    lenb = int(lenaLenbLenc[1])

    lenc = int(lenaLenbLenc[2])

    arra = list(map(int, input().rstrip().split()))

    arrb = list(map(int, input().rstrip().split()))

    arrc = list(map(int, input().rstrip().split()))

    ans = triplets(arra, arrb, arrc)
    print(ans)
    #fptr.write(str(ans) + '\n')

    #fptr.close()

