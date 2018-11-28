#!/bin/python3

import math
import os
import random
import re
import sys

def index(c):
    return ord(c) - ord('A')

# Complete the commonChild function below.
def commonChild(s1, s2):
    mem = [None] * (len(s2) + 1)
    mem2 = [None] * (len(s2) + 1)

    for i in range(len(s2) + 1):
        mem[i] = len(s2) - i

    for col in range(len(s1) - 1, -1, -1):
        mem2[len(s2)] = len(s1) - col
        for row in range(len(s2) - 1, -1, -1):
            if s1[col] == s2[row]:
                mem2[row] = mem[row + 1]
            else:
                mem2[row] = min(1 + mem[row], 1 + mem2[row + 1])
        mem = mem2.copy()
    
    return len(s1) - mem[0] // 2


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s1 = input()

    s2 = input()

    result = commonChild(s1, s2)
    print(result)

    #fptr.write(str(result) + '\n')

    #fptr.close()
