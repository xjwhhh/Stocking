'''
计算跌幅超过多少的股票数
:param
当日的adjClose(series)，上一个交易日的adjClose(series),跌幅
两个series对应位置必须是同一股票（sort一下即可），且已经剔除了两日中任一一日停牌的股票
'''

def decBelow(previous, present, percent):
    import pandas as pd
    result = list(map(lambda x, y: (y - x) / x, previous, present))
    count = len(list(filter(lambda x: x <= percent, result)))
    return count
