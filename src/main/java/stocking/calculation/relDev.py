'''
计算相对方差
:param
adjClose(series)
'''

def relDev(tsPrice):
    import pandas as pd
    import math

    lagtsPrice = tsPrice.shift(1)
    rate = tsPrice / lagtsPrice
    rate = rate[~rate.isnull()]
    for i in range(0, len(rate)):
        rate[i] = math.log(rate[i], math.e)
    avr = sum(rate) / len(rate)
    result = map(lambda x: math.pow(x - avr, 2), rate)
    deviation = sum(result) / (len(rate) - 1)
    return deviation
