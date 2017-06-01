'''
策略的调用主要是调用这个方法,用成员变量传递所有返回值，具体看注释
:param
初始dataframe（已经过初步筛选）
'''

import math


class StrategyCalculator:
    def __init__(self, strategy):
        self.stra = strategy

    def count(self, oridf, isPla, plaName):
        startdate = oridf.index[0]
        enddate = oridf.index[len(oridf.index) - 1]
        rf = self._getrf(startdate, enddate)
        self.stra.count(oridf, isPla, plaName)
        self.annualPro = self.stra.getAnnualPro()  # 年化收益率
        self.basicPro = self.stra.getBasicAnnualPro()  # 基准年化收益率
        self.beta = self.stra.getBeta()  # β
        self.alpha = (self.annualPro - rf) - self.beta * (self.basicPro - rf)  # α
        self.sharpe = (self.stra.avr - rf) / math.sqrt(self.stra.getSquare(self.stra.select))  # 夏普比率
        self.maxDraw = self.stra.getMaxDraw()  # 最大回撤
        self.pros = self.stra.select  # 每个持有期的策略收益率(dict)
        self.basics = self.stra.basic  # 每个持有期的基准收益率(dict)
        self.candidates = self.stra.winner  # 每个持有期前百分之二十的股票及其收益率(dict,值为series)

    pass

    def _getrf(self, startdate, enddate): pass
