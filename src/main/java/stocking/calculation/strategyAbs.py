'''
策略的抽象类
:param
筛选得到的股票（index=日期，column=每个股票的代号），形成期，持有期，仓内持有股票数（只有均值策略需要）
:return
经过策略计算得到的股票（index=日期，column=赢家组合的代码、持有期收益率）
其它：若使用到了某个版块的指数，则具体由调用者进行更改（getBasic方法）
'''

from abc import abstractmethod
import pymysql
import pandas as pd


class Strategy(object):
    @abstractmethod
    def count(self, oriDf, isPla, plaName):
        pass

    def getPlaIndex(self, startdate, enddate, plaName):
        db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
        cursor = db.cursor();
        sql = "select close from market_index where date>='%s' and date<='%s' and code='%s' " % (
            startdate, enddate, plaName)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                re.append(row[0])
            se = pd.Series(re)
            index = se.mean()
        except:
            print('get data fail')
        return index

    def getAnnualPro(self):
        length = int(self.selectLen / 5)
        select = list(map(lambda x: self.winner[x].iloc[0:length].mean(), self.winner))
        avr = sum(select) / len(select)  # 每个持有期的平均收益率
        return (avr / self.hold) * 250

    pass

    def getBasicAnnualPro(self):
        basic = sum(self.basic.values()) / len(self.basic)
        return (basic / self.hold) * 250

    pass
