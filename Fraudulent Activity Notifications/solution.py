#!/bin/python3

import math
import os
import random
import re
import sys
from collections import deque

# Complete the activityNotifications function below.
def activityNotifications(expenditure, d):
    cs = [0] * 201
    q = deque([])

    for i in range(d):
        e = expenditure[i]
        q.append(e)
        cs[e] += 1

    count = 0

    for i in range(d, len(expenditure)):
        e = expenditure[i]
        median = 0

        curr = 0
        cum = 0
        if d % 2 == 1:
            index = d // 2
            while cum + cs[curr] - 1 < index:
                cum += cs[curr]
                curr += 1

            median = 2 * curr
        else:
            right = d // 2
            left = right - 1

            while cum + cs[curr] - 1 < left:
                cum += cs[curr]
                curr += 1

            if cum + cs[curr] - 1 == left:
                median = curr
                curr += 1
                while cs[curr] == 0:
                    curr += 1
                median = median + curr
            else:
                median = 2 * curr

        if e >= median:
            count += 1

        to_delete = q.popleft()
        q.append(e)

        cs[to_delete] -= 1
        cs[e] += 1

    return count


if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')
    
    nd = input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    #fptr.write(str(result) + '\n')

    #fptr.close()
    print(result)

