#!/bin/python3

import math
import os
import random
import re
import sys
import collections

# Complete the findShortest function below.

#
# For the weighted graph, <name>:
#
# 1. The number of nodes is <name>_nodes.
# 2. The number of edges is <name>_edges.
# 3. An edge exists between <name>_from[i] to <name>_to[i].
#
#

def bfs_step(curr, edges, visited, leafs, sv, ds):
    visited[curr] = True
    for _, n in enumerate(edges[curr]):
        if visited[n] == False:
            leafs.append(n)
            if sv[n]:
                return ds[curr] + 1 + ds[n] 
            sv[n] = True 
            ds[n] = ds[curr] + 1
    return -1

def findShortest(graph_nodes, graph_from, graph_to, ids, val):
    edges = [None] * graph_nodes
    for i in range(len(graph_from)):
        s = graph_from[i] - 1
        e = graph_to[i] - 1
        if edges[s] == None:
            edges[s] = []
        edges[s].append(e)
        if edges[e] == None:
            edges[e] = []
        edges[e].append(s)

    visited = []
    leafs = []
    val_count = 0
    shared_visited = [False] * graph_nodes
    distances = [0] * graph_nodes
    for i in range(len(ids)):
        c = ids[i]
        if c == val:
            v = [False] * graph_nodes
            visited.append(v)

            q = collections.deque()
            q.append(i)
            leafs.append(q)
            shared_visited[i] = True
            val_count += 1

    if val_count <= 1:
        return -1


    while True:
        for i, vs in enumerate(visited):
            q = leafs[i]
            curr = q.popleft()
            found = bfs_step(curr, edges, vs, q, shared_visited, distances)
            if found > 0:
                return found
            
    

if __name__ == '__main__':
    graph_nodes, graph_edges = map(int, input().split())

    graph_from = [0] * graph_edges
    graph_to = [0] * graph_edges

    for i in range(graph_edges):
        graph_from[i], graph_to[i] = map(int, input().split())

    ids = list(map(int, input().rstrip().split()))

    val = int(input())

    ans = findShortest(graph_nodes, graph_from, graph_to, ids, val)

    print(ans)
