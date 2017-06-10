'''
计算某只股票和市场整体情况的相关性
:param
stock为单支股票adjClose(df),market为市场收益率(df),只要这两列，index为date
'''
import pandas as pd


def smCorCal(stock, market):
    rate = pd.DataFrame(stock[0] - stock[0].shift(1) / stock[0].shift(1))
    df = pd.concat([rate[0], market[0]], axis=1, keys=['closeS', 'closeM'])
    df = df.dropna(how='any')
    result = df.corr(method='pearson', min_periods=1)
    return result.at['closeS', 'closeM']
