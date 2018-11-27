#!/bin/python3

import math
import os
import random
import re
import sys

def index(c):
    return ord(c) - ord('a')

# Complete the makeAnagram function below.
def makeAnagram(a, b):
    hista = [0] * 26
    histb = [0] * 26

    for i in range(len(a)):
        c = index(a[i])
        hista[c] += 1

    for i in range(len(b)):
        c = index(b[i])
        histb[c] += 1

    deletes = 0
    for i in range(26):
        deletes += abs(hista[i] - histb[i])

    return deletes

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    a = input()

    b = input()

    res = makeAnagram(a, b)
    print(res)

    #fptr.write(str(res) + '\n')

    #fptr.close()

