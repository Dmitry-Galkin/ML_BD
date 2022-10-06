#!bin/bash/env python

import sys


sum = 0
sum2 = 0
cnt = 0

for line in sys.stdin:

	data = line.split(",")
	
	price = float(line.split(",")[-7])
	sum += price
	sum2 += price**2
	cnt += 1
	
mean = sum / cnt
var = (sum2 - 2 * mean * sum + cnt * mean**2) / cnt

print(" ".join(map(str, [mean, var, cnt])))
    
