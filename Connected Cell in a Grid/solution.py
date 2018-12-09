#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the maxRegion function below.


def maxRegion(grid):
    out = 0
    tovisit = []
    visited = [[False] * len(grid[i]) for i in range(len(grid))]
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == 1:
                tovisit.append((i, j))

    while len(tovisit) > 0:
        curr_i, curr_j = tovisit.pop()

        if visited[curr_i][curr_j] == True:
            continue

        visited[curr_i][curr_j] = True

        stack = [(curr_i, curr_j)]
        cluster_size = 1
        while len(stack) > 0:
            curr_i, curr_j = stack.pop()

            for i in range(-1, 2, 1):
                for j in range(-1, 2, 1):
                    if curr_i + i < len(grid) and curr_i + i >= 0 \
                            and curr_j + j < len(grid[i]) and curr_j + j >= 0 \
                            and visited[curr_i + i][curr_j + j] == False and grid[curr_i + i][curr_j + j] == 1:
                        visited[curr_i + i][curr_j + j] = True
                        stack.append((curr_i + i, curr_j + j))
                        cluster_size += 1

        out = max(out, cluster_size)

    return out


if __name__ == '__main__':
    # fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    m = int(input())

    grid = []

    for _ in range(n):
        grid.append(list(map(int, input().rstrip().split())))

    res = maxRegion(grid)
    print(res)
    # fptr.write(str(res) + '\n')

    # fptr.close()
