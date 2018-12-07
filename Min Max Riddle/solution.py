#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the riddle function below.
def riddle(h):
    stack = []
    right = [None] * len(h)
    left = [None] * len(h)

    for i, v in enumerate(h):
        while len(stack) > 0 and stack[len(stack) - 1][0] > v:
            p = stack.pop()
            right[p[1]] = i

        stack.append((v, i))

    while len(stack) > 0:
        p = stack.pop()
        right[p[1]] = len(h)

    for i in range(len(h) - 1, -1, -1):
        v = h[i]
        while len(stack) > 0 and stack[len(stack) - 1][0] > v:
            p = stack.pop()
            left[p[1]] = i

        stack.append((v, i))

    while len(stack) > 0:
        p = stack.pop()
        left[p[1]] = -1

    w2max = {}
    for i in range(len(h)):
        w = right[i] - left[i] - 1
        w2max[w] = max(w2max.get(w, -1), h[i])

    out = [None] * len(h)
    for i in range(len(h), 0, -1):
        out[i - 1] = out[i] if w2max.get(i, None) == None else w2max.get(i)
        out[i - 1] = max(out[i - 1], out[i]) if i < len(h) else out[i - 1]

    return out

    

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = riddle(arr)

    print(' '.join(map(str, res)))
    #fptr.write('\n')

    #fptr.close()
