'''
策略的调用主要是调用这个方法,用成员变量传递所有返回值，具体看注释
:param
初始dataframe（已经过初步筛选）
'''

import math
import pandas as pd
import pymysql


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
        self.sharpe = (self.stra.avr - rf) / math.sqrt(self.stra.getDev(self.stra.select))  # 夏普比率
        self.maxDraw = self.stra.getMaxDraw()  # 最大回撤
        self.pros = self.stra.select  # 每个持有期的策略收益率(dict)
        self.basics = self.stra.basic  # 每个持有期的基准收益率(dict)
        self.candidates = self.stra.winner  # 每个持有期前百分之二十的股票及其收益率(dict,值为series)

    pass

    def _getrf(self, startdate, enddate):
        db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
        cursor = db.cursor()
        sql = "select rate from rates where date>='%s' and date<='%s'" % (startdate, enddate)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                re.append(float(row[0]))
            ss = pd.Series(re)
            rate = ss.mean()
        except:
            print('get data fail')
        return rate
