'''
计算均值
:param
adjClose(series)，interval
'''

def smaCal(tsPrice, k):
    import pandas as pd
    sma = pd.Series(0.0, index=tsPrice.index)
    for i in range(k - 1, len(tsPrice)):
        sma[i] = sum(tsPrice[(i - k + 1):(i + 1)]) / k
    return (sma)