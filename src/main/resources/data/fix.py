# coding: utf-8

from collections import defaultdict

samples = open('sample.in').readlines()
outputs = open('sample.out').readlines()
samples = [x.strip().split(' ', 1) for x in samples]

outputs = [x.strip().split('|', 2) for x in outputs]
outputs = [[x[0], map(int, x[1].split()) + [x[2]]] for x in outputs]

answers = defaultdict(list)
for output in outputs:
    answers[output[0]].append(output[1])

for sid, sent in samples:
    genes = answers[sid]
    genes.sort()

    
    spans = [sid, str(len(sent))]

    start = 0
    for s, e, gene in genes:
        start = sent.index(gene, start)
        #print "%s|%d %d|%s" % (sid, start, start + len(gene), gene)
        spans.append("%d:%d" %(start, start + len(gene)))

    if len(spans) > 2:
        print ' '.join(spans)



    
