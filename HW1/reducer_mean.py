#!bin/bash/env python

import sys


sum = 0
cnt = 0

for line in sys.stdin:
	
	m, c = list(map(float, line.split()))
	
	sum += c * m
	cnt += c
	
mean = sum / cnt

print("mean (map-reduce):", mean)

