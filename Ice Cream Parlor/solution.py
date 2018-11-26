#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the whatFlavors function below.
def whatFlavors(cost, money):
    costsIndices = [(c, i + 1) for i, c in enumerate(cost)]

    costsIndices.sort()
    left = 0
    right = len(costsIndices) - 1
    while left <= right:
        if costsIndices[left][0] + costsIndices[right][0] == money:
            if costsIndices[left][1] < costsIndices[right][1]:
                print(costsIndices[left][1], costsIndices[right][1])
            else:
                print(costsIndices[right][1], costsIndices[left][1])
            return
        elif costsIndices[left][0] + costsIndices[right][0] < money:
            left += 1
        else:
            right -= 1

if __name__ == '__main__':
    t = int(input())

    for t_itr in range(t):
        money = int(input())

        n = int(input())

        cost = list(map(int, input().rstrip().split()))

        whatFlavors(cost, money)

