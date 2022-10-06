#!bin/bash/env python

import sys


m_prev, v_prev, c_prev = 0, 0, 0

for line in sys.stdin:
	
	m, v, c = list(map(float, line.split()))
	
	var = (c_prev * v_prev + c * v) / (c_prev + c)
	var += c_prev * c * ((m_prev - m) / (c_prev + c))**2
	
	m_prev, v_prev, c_prev = m, v, c

print("var (map-reduce)", var)

