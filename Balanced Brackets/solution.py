#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the isBalanced function below.
def isBalanced(s):
    stack = []
    for _, c in enumerate(s):
        if c == '(':
            stack.append(c)
        elif c == ')':
            if len(stack) == 0 or stack[len(stack) - 1] != '(':
                return 'NO'
            else:
                stack.pop()

            
        elif c == '[':
            stack.append(c)
        elif c == ']':
            if len(stack) == 0 or stack[len(stack) - 1] != '[':
                return 'NO'
            else:
                stack.pop()

        elif c == '{':
            stack.append(c)
        elif c == '}':
            if len(stack) == 0 or stack[len(stack) - 1] != '{':
                return 'NO'
            else:
                stack.pop()

    return 'YES' if len(stack) == 0 else 'NO'

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input())

    for t_itr in range(t):
        s = input()

        result = isBalanced(s)
        print(result)

        #fptr.write(result + '\n')

    #fptr.close()
