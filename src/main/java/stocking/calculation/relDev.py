'''
计算相对方差
:param
adjClose(series)
'''


def relDev(tsPrice):
    import pandas as pd
    import math

    lagtsPrice = tsPrice.shift(1)
    result = tsPrice / lagtsPrice
    result = result[~result.isnull()]
    for i in range(0, len(result)):
        result[i] = math.log(result[i], math.e)
    avr = sum(result) / len(result)
    result = result * result
    avrSq = sum(result) / len(result)
    return (avrSq - avr * avr)
