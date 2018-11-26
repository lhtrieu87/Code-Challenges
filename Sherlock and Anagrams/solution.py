#!/bin/python3

import math
import os
import random
import re
import sys

def index(c):
    return ord(c) - ord('a')

def diff_hists(h1, h2):
    d = [0] * 26
    for i in range(len(h1)):
        d[i] = h2[i] - h1[i]

    return d

# Complete the sherlockAndAnagrams function below.
def sherlockAndAnagrams(s):
    i2hist = [None] * len(s)
    hist2count = {}

    for i in range(len(s)):
        c = index(s[i])
        if i == 0:
            hist = [0] * 26
            hist[c] += 1
            i2hist[i] = hist
        else:
            hist = i2hist[i - 1].copy()
            hist[c] += 1
            i2hist[i] = hist

    out = 0
    for i in range(len(s)):
        for j in range(0, i + 1):
            start = [0] * 26
            if i - j - 1 >= 0:
                start = i2hist[i - j - 1]
            end = i2hist[i]

            d = tuple(diff_hists(start, end))
            cnt = hist2count.get(d, 0)

            out += cnt

            hist2count[d] = cnt + 1

    return out


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        s = input()

        result = sherlockAndAnagrams(s)
        print(result)
        #fptr.write(str(result) + '\n')

    #fptr.close()

