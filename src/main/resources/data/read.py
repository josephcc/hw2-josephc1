# coding: utf-8

samples = open('sample.in').readlines()
outputs = open('sample.out').readlines()
samples[0]
samples[0].split(' ', 2)
samples[0].split(' ', 1)
samples = [x.strip().split(' ', 1) for x in samples]
sameples[0]
samples[0]
samples = dict(samples)
samples['P00001606T0076']
len(samples)
outputs[0]
[x.split('|', 2) for x in outputs][0]
[x.strip().split('|', 2) for x in outputs][0]
outputs = [x.strip().split('|', 2) for x in outputs]
outputs[0]
outputs[0]
[[x[0], map(int, x[1].split()), x[2] for x in outputs][0]
]
[[x[0], map(int, x[1].split()), x[2]] for x in outputs][0]
[[x[0], map(int, x[1].split()) + x[2]] for x in outputs][0]
[[x[0], map(int, x[1].split()) + [x[2]]] for x in outputs][0]
outputs = [[x[0], map(int, x[1].split()) + [x[2]]] for x in outputs]
get_ipython().magic(u'save read.py 0-23')
from collection import default_dict
from collections import default_dict
from collection import defaultdict
from collections import defaultdict
genes = defaultdict(list)
outputs[0]
for output in outputs:
    defaultdict[output[0]].append(output[1])
    
outputs[0]
outputs[2]
for output in outputs:
    genes[output[0]].append(output[1])
    
genes
get_ipython().magic(u'save read.py 0-35')
