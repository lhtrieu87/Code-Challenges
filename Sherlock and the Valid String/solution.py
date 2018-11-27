#!/bin/python3

import math
import os
import random
import re
import sys

def index(c):
    return ord(c) - ord('a')

# Complete the isValid function below.
def isValid(s):
    hist = [0] * 26
    for i in range(len(s)):
        c = index(s[i])
        hist[c] += 1
    
    temp = {}
    cnt = 0
    for i in range(26):
        v = hist[i]
        if v == 0:
            continue
        if temp.get(v, None) == None:
            cnt += 1
            temp[v] = 1
        else:
            temp[v] += 1

        if cnt > 2:
            return 'NO'

    counts = list(temp.keys())
    cc = list(temp.values())
    if len(counts) == 1:
        return 'YES'

    if counts[0] == 1 and cc[0] == 1:
        return 'YES'
    elif counts[1] == 1 and cc[1] == 1:
        return 'YES'

    if abs(counts[0] - counts[1]) > 1:
        return 'NO'
    elif cc[0] > 1 and cc[1] > 1:
        return 'NO'
    return 'YES'

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = isValid(s)
    print(result)

    #fptr.write(result + '\n')

    #fptr.close()
