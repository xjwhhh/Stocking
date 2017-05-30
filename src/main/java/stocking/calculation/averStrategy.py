'''
均值策略
'''

from calculation.strategyAbs import Strategy
import pandas as pd


class AverageStrategy(Strategy):
    def __init__(self, form, hold, num):
        self.winner = {}
        self.basic = {}  # 市场基本收益率
        self.form = form
        self.hold = hold
        self.num = num

    pass

    def count(self, oriDf, isPla, plaName):
        length = len(oriDf)
        i = self.form - 1
        while i < length:
            average = oriDf[i - self.form + 1:i + 1].sum() / self.form
            tempRes = abs(average - oriDf.iloc[i])
            tempRes = tempRes.sort_values(axis=0, ascending=False)[0:self.num]  # 筛选要持有的股票数
            candidate = tempRes.index
            if i + self.hold >= length and length - 1 > i + 1:
                basicPro = (oriDf.iloc[length - 1] - oriDf.iloc[i + 1]) / oriDf.iloc[i + 1]
                holdingPro = basicPro[candidate]
                if isPla:
                    self.basic[oriDf.index[length - 1]] = self.getPlaIndex(oriDf.index[i + 1],
                                                                           oriDf.index[length - 1], plaName)
                else:
                    self.basic[oriDf.index[length - 1]] = basicPro.mean()
                self.winner[oriDf.index[length - 1]] = holdingPro
            elif length - 1 <= i + 1:
                break
            else:
                basicPro = (oriDf.iloc[i + self.hold] - oriDf.iloc[i + 1]) / oriDf.iloc[i + 1]
                holdingPro = basicPro[candidate]
                # print(holdingPro)
                if isPla:
                    self.basic[oriDf.index[i + self.hold]] = self.getPlaIndex(oriDf.index[i + 1],
                                                                              oriDf.index[i + self.hold], plaName)
                else:
                    self.basic[oriDf.index[i + self.hold]] = basicPro.mean()
                self.winner[oriDf.index[i + self.hold]] = holdingPro

            i = i + self.hold

    pass
