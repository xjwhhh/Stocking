'''
计算开盘‐收盘小于percent*上一个交易日收盘价的股票个数
:param
当日的open+adjClose(dataframe)，上一个交易日的adjClose(series),跌幅
两个series对应位置必须是同一股票（sort一下即可），且已经剔除了两日中任一一日停牌的股票
'''


def decGre(previous, present, percent):
    import pandas as pd
    difference = pd.Series(present['open'] - present['close'])
    count = 0
    for i in range(0, len(previous)):
        if difference[i] <= previous[i] * percent:
            count = count + 1
    return count
