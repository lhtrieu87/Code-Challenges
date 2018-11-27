#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minTime function below.
def minTime(machines, goal):
    l = 0
    r = sys.maxsize

    d = (l + r) // 2

    while l + 1 < r:
        total = 0
        for i in range(len(machines)):
            c = machines[i]
            total += d // c

        if total == goal:
            break
        elif total > goal:
            r = d
        else:
            l = d

        d = (l + r) // 2


    out = d + 1
    for k in range(d, 0, -1):
        total = 0
        for i in range(len(machines)):
            c = machines[i]
            total += k // c
        
        if total == goal:
            out = k
        else:
            break

    return out

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nGoal = input().split()

    n = int(nGoal[0])

    goal = int(nGoal[1])

    machines = list(map(int, input().rstrip().split()))

    ans = minTime(machines, goal)
    print(ans)
    #fptr.write(str(ans) + '\n')

    #fptr.close()
