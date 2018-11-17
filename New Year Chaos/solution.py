#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumBribes function below.
def minimumBribes(q):
    brides = 0
    pos = [None] * len(q)
    for i, tag in enumerate(q):
        pos[tag - 1] = i
    for tag in range(len(q) - 1, 0, -1):
        p = pos[tag]
        if p - tag < -2:
            print("Too chaotic")
            return
        for j in range(p, tag):
            tmp = q[j]
            q[j] = q[j + 1]
            pos[q[j + 1] - 1] = j
            q[j + 1] = tmp
            pos[tmp - 1] = j + 1
            brides += 1

    print(brides)

if __name__ == '__main__':
    t = int(input())

    for t_itr in range(t):
        n = int(input())

        q = list(map(int, input().rstrip().split()))

        minimumBribes(q)
