#!bin/bash/env python

import sys


sum = 0
cnt = 0

for line in sys.stdin:

	data = line.split(",")
	
	sum += float(line.split(",")[-7])
	cnt += 1
	
mean = sum / cnt

print(" ".join(map(str, [mean, cnt])))
    
