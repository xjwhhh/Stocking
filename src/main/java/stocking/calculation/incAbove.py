'''
计算涨幅超过多少的股票数
:param
当日的adjClose(series)，上一个交易日的adjClose(series),涨幅
两个series对应位置必须是同一股票（sort一下即可），且已经剔除了两日中任一一日停牌的股票
'''


def incAbove(previous, present, percent):
    result = list(map(lambda x, y: (y - x) * 100 / x + 0.01 * 100 / x, previous, present))
    count = len(list(filter(lambda x: x >= percent * 100, result)))
    return count
