#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the abbreviation function below.
def abbreviation(a, b):
    mem = [None] * (len(b) + 1)
    mem2 = [None] * (len(b) + 1)
    
    mem[0] = True
    for i in range(1, len(b) + 1):
        mem[i] = False
    mem2 = mem.copy()

    for i in range(1, len(a) + 1):
        av = a[i - 1]
        
        if ord(av) >= 97:
            mem[0] = True
        else:
            mem[0] = False

        for j in range(1, len(b) + 1):
            bv = b[j - 1]
            if ord(av) >= 97:
                if ord(av) - 32 == ord(bv):
                    mem[j] = mem2[j] | mem2[j - 1]
                else:
                    mem[j] = mem2[j]
            else:
                if av != bv:
                    mem[j] = False
                else:
                    mem[j] = mem2[j - 1]

        mem2 = mem.copy()

    return 'YES' if mem2[len(b)] else 'NO'
        

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        a = input()

        b = input()

        result = abbreviation(a, b)
        print(result)
        #fptr.write(result + '\n')

    #fptr.close()

