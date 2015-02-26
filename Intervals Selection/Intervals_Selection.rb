#Author: Le Hong Trieu
#Date: 20-May-2013

#You are given a list of N intervals.
#The challenge is to select the largest subset of intervals such that no three intervals in the subset share a common point.
#
#Input:
#The first line contains the number of cases T. T cases follow. Each case contains the number N on the first line followed by N lines containing integers ai and bi. The ith line denotes the starting and ending points of the ith interval.
#
#Output:
#Output T lines, one for each test case, containing the desired answer for the corresponding test case.
#
#Constraints:
#1 <= T <= 100
#2 <= N <= 1000
#1 <= ai <= bi <= 1000000000 (10^9)
#
#Sample Input:
#4
#3
#1 2
#2 3
#2 4
#3
#1 5
#1 5
#1 5
#4
#1 10
#1 3
#4 6
#7 10
#4
#1 10
#1 3
#3 6
#7 10
#
#Sample Output:
#2
#2
#4
#3
#
#Explanation:
#For the first case, the largest subset can contain any two intervals, but not all three.
#For the third case, the largest subset contains all the given intervals.
  
  
numCases = Integer(gets)
(0...numCases).each do |c|
  n = Integer(gets)
  iCol = []
  iCol2 = []
  bs = []
  
  result = n
  
  (0...n).each do |i|
    a, b = gets.split().map {|token| Integer(token)}
    interval = [a, b]
    bs.push(b)
    iCol.push(interval)
  end
  
  bs.sort!
  iCol.sort! {|a, b| a[1] <=> b[1]}
  
  bs.each do |b|
    overlapCnt = 0
    removeIntervals = []
    index = 0
    iCol.each do |i, j|
      if i != -1 && j != -1 && i <= b && b <= j # As those js < b has been handled in previous iterations.
        overlapCnt += 1
        if overlapCnt > 2
          removeIntervals.push(index)
          result -= 1
        else
          iCol2.push([i, j])
        end
      else
        iCol2.push([i, j])
      end
      
      index += 1
    end
    
    iCol = iCol2
    iCol2 = []
    
    
    
  end
  
  print "#{result}\n"
  
end
