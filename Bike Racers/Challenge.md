There are N bikers present in a city (shaped as a grid) having M bikes. All the bikers want to participate in the HackerRace competition, but unfortunately only K bikers can be accommodated in the race. Jack is organizing the HackerRace and wants to start the race as soon as possible. He can instruct any biker to move towards any bike in the city. In order to minimize the time to start the race, Jack instructs the bikers in such a way that first K bikes are acquired in the minimum time.

Every biker moves with a unit speed and one bike can be acquired by only one biker. A biker can proceed in any direction. Consider distance between bike and bikers as euclidean distance.

Jack would like to know the square of required time to start the race as soon as possible.

Input Format 
The first line contains three integers - N,M,K separated by a single space. 
Following N lines will contain N pairs of integers denoting the co-ordinates of N bikers. Each pair of integers is separated by a single space. The next M pairs of lines will denote the co-ordinates of M bikes.

Output Format 
A single line containing the square of required time.

Constraints 
1 <= N <= 250 
1 <= M <= 250 
1 <= K <= min(N,M) 
0 <= xi,yi <= 107

Sample Input #00:

3 3 2
0 1
0 2
0 3
100 1
200 2 
300 3

Sample Output #00:

40000