#!/bin/python3

import math
import os
import random
import re
import sys
from collections import deque

# Complete the largestRectangle function below.
def largestRectangle(h):
    largest = 0
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

    for i in range(len(h)):
        largest = max(largest, (right[i] - left[i] - 1) * h[i])

    return largest


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    h = list(map(int, input().rstrip().split()))

    result = largestRectangle(h)
    print(result)

    #fptr.write(str(result) + '\n')

    #fptr.close()
