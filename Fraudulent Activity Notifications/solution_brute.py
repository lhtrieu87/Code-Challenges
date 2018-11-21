#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the activityNotifications function below.
def activityNotifications(expenditure, d):
    count = 0

    for i in range(d, len(expenditure)):
        history = sorted(expenditure[i - d:i])
        # if i - d + 1 == 27280:
        #     for j, h in enumerate(history):
        #         print(h)
        
        median = None
        if d % 2 == 0:
            median = (history[d // 2] + history[d // 2 - 1]) / 2.0
        else:
            median = history[d // 2]

        # print(median)

        if expenditure[i] >= median * 2:
            count += 1

    return count


if __name__ == '__main__':
    nd = input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    print(result)
