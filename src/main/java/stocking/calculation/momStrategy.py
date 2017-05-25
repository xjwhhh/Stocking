'''
动量策略
'''

from calculation.strategyAbs import Strategy
import pandas as pd


class MomentumStrategy(Strategy):
    def __init__(self):
        self.winner = {}
        self.basic = {}  # 市场基本收益率

    pass

    def _get(self, oriDf, form, hold, num):
        length = len(oriDf)
        selectLen = int(len(oriDf.columns) * 0.2)
        i = form - 1
        while i < length:
            tempRes = (oriDf.iloc[i] - oriDf.iloc[i - form + 1]) / oriDf.iloc[i - form + 1]  # 计算形成期
            tempRes = tempRes.sort_values(axis=0, ascending=False)[0:selectLen]  # 筛选前20%的股票
            candidate = tempRes.index
            if i + hold >= length and length - 1 > i + 1:
                basicPro = (oriDf.iloc[length - 1] - oriDf.iloc[i + 1]) / oriDf.iloc[i + 1]
                holdingPro = basicPro[candidate]
                self.basic[oriDf.index[length - 1]] = basicPro.mean()
                self.winner[oriDf.index[length - 1]] = holdingPro
            elif length - 1 <= i + 1:
                break
            else:
                basicPro = (oriDf.iloc[i + hold] - oriDf.iloc[i + 1]) / oriDf.iloc[i + 1]
                holdingPro = basicPro[candidate]
                self.basic[oriDf.index[i + hold]] = basicPro.mean()
                self.winner[oriDf.index[i + hold]] = holdingPro

            i = i + hold

        pass
