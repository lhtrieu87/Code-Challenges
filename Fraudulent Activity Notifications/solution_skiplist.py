#!/bin/python3

import math
import os
import random
import re
import sys

class Node(object):
    def __init__(self, key, level):
        self.key = key
        self.forward = [None] * (level + 1)
        self.predecessor = None

    def next(self):
        return self.forward[0]

    def prev(self):
        return self.predecessor

    def __str__(self):
        return f"{self.key}"

class SkipList(object):
    def __init__(self, max_level, P):
        self.MAX_LEVEL = max_level
        self.P = P
        self.header = self.create_node(self.MAX_LEVEL, -1)
        self.level = 0
        self.size = 0

    def create_node(self, level, key):
        n = Node(key, level)
        return n

    def random_level(self):
        level = 0
        while random.random() < self.P and level < self.MAX_LEVEL:
            level += 1
        return level

    def insert(self, key):
        update = [None] * (self.MAX_LEVEL + 1)
        current = self.header

        for i in range(self.level, -1, -1):
            while current.forward[i] and current.forward[i].key < key:
                current = current.forward[i]
            update[i] = current
        
        rlevel = self.random_level()

        if rlevel > self.level:
            for i in range(self.level + 1, rlevel + 1):
                update[i] = self.header
            self.level = rlevel

        n = self.create_node(rlevel, key)

        if update[0] != self.header:
            n.predecessor = update[0]

        if update[0].forward[0]:
            update[0].forward[0].predecessor = n

        for i in range(rlevel + 1):
            n.forward[i] = update[i].forward[i]
            update[i].forward[i] = n

        self.size += 1

    def delete(self, key):
        update = [None] * (self.MAX_LEVEL + 1)
        current = self.header

        for i in range(self.level, -1, -1):
            while current.forward[i] and current.forward[i].key < key:
                current = current.forward[i]
            update[i] = current

        current = current.forward[0]

        if current and current.key == key:
            if current.forward[0]:
                if update[0] != self.header:
                    current.forward[0].predecessor = update[0]
                else:
                    current.forward[0].predecessor = None

            for i in range(self.level + 1):
                if update[i].forward[i] != current:
                    break
                update[i].forward[i] = current.forward[i]

            while self.level > 0 and self.header.forward[self.level] == None:
                self.level -= 1

            self.size -= 1

            return current

    def search(self, key):
        current = self.header

        for i in range(self.level, -1, -1):
            while current.forward[i] and current.forward[i].key < key:
                current = current.forward[i]

        current = current.forward[0]

        if current and current.key == key:
            return current

    def display(self):
        head = self.header
        for level in range(self.level + 1):
            print(f"Level {level}:", end=" ")
            node = head.forward[level]
            while(node != None):
                print(node.key, end=" ")
                node = node.forward[level]
            print("")

    def display_level_0(self):
        node = self.header.forward[0]
        while(node != None):
            print(node.key, end=" ")
            node = node.forward[0]
        print("")

class Expense(object):
    def __init__(self, expense, index):
        self.expense = expense
        self.index = index

    def __lt__(self, other):
        if self.expense < other.expense:
            return True
        elif self.expense == other.expense and self.index < other.index:
            return True
        return False

    def __eq__(self, other):
        return self.expense == other.expense and self.index == other.index

    def __str__(self):
        return f"{self.expense}***{self.index}"

def insert_notif(ls, median, left, right, e):
    ls.insert(e)
    if ls.size % 2 == 0:
        if median.key < e:
            left = median
            right = left.next()
        else:
            right = median
            left = right.prev()
        median = None
    else:
        if e < left.key:
            median = left
        elif right.key < e:
            median = right
        else: 
            median = ls.search(e)
        left = median.prev()
        right = median.next()

    return median, left, right

def delete_notif(ls, median, left, right, e):
    ls.delete(e)
    if ls.size % 2 == 0:
        if e < median.key:
            left = median
        elif median.key < e:
            right = median
        median = None
    else:
        if e < left.key:
            median = right
        elif e == left.key:
            median = right
        else:
            median = left
        left = median.prev()
        right = median.next()
    return median, left, right

# Complete the activityNotifications function below.
def activityNotifications(expenditure, d):
    ls = SkipList(int(math.log(d, 2)), 0.5)
    ls.insert(Expense(expenditure[0], 0))
    
    median = ls.search(Expense(expenditure[0], 0))
    left = median.prev()
    right = median.next()

    for i in range(1, d):
        e = expenditure[i]
        median, left, right = insert_notif(ls, median, left, right, Expense(e, i))

    count = 0
    for i in range(d, len(expenditure)):
        e = expenditure[i]
   
        
        #print("Median:", median, "Left:", left, "Right:", right)
        #ls.display_level_0()
        #print("Delete", expenditure[i - d])
        #print("Insert:", e)
        print(left.key.expense + right.key.expense)
        if median and e >= median.key.expense * 2:
            count += 1
        elif median == None and e >= left.key.expense + right.key.expense:
            count += 1
        median, left, right = delete_notif(ls, median, left, right, Expense(expenditure[i - d], i - d))
        median, left, right = insert_notif(ls, median, left, right, Expense(e, i))
    
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
