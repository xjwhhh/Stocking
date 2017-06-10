'''
策略的抽象类
:param
筛选得到的股票（index=日期，column=每个股票的代号），形成期，持有期，仓内持有股票数（只有均值策略需要）
:return
经过策略计算得到的股票（index=日期，column=赢家组合的代码、持有期收益率）
其它：若使用到了某个版块的指数，则具体由调用者进行更改（getBasic方法）
'''

import pandas as pd
import pymysql
from abc import abstractmethod


class Strategy(object):
    @abstractmethod
    def count(self, oriDf, isPla, plaName):
        pass

    def getPlaIndex(self, startdate, enddate, plaName):
        # print(str(startdate)+str(enddate)+plaName)
        db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
        cursor = db.cursor()
        sql = "select distinct close from market_index where date>='%s' and date<='%s' and code='%s'" % (
        startdate, enddate, plaName)
        # print(sql)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                re.append(float(row[0]))
            ss = pd.Series(re)
            index = ss.mean()
            length = len(ss)
            index = (ss[ss.index[length - 1]] - ss[ss.index[0]]) / ss[ss.index[length - 1]]
        except:
            print('get data fails')
        return index

    def getAnnualPro(self):
        # length = int(self.selectLen / 5)
        length = 1
        # 每个日期的平均策略收益
        self.select = dict(zip(self.winner.keys(),
                               list(map(lambda x: self.winner[x].iloc[0:length].mean(), self.winner))))
        self.avr = sum(self.select.values()) / len(self.select)  # 每个持有期的平均收益率
        return (self.avr / self.hold) * 250

    pass

    def getBasicAnnualPro(self):
        self.basicAvr = sum(self.basic.values()) / len(self.basic)
        return (self.basicAvr / self.hold) * 250

    pass

    def getBeta(self):
        total = sum(self.select[x] * self.basic[x] for x in self.select)
        cov = total / len(self.select) - self.avr * self.basicAvr
        basicDev = self.getDev(self.basic) - self.basicAvr * self.basicAvr
        return cov / basicDev

    pass

    def getDev(self, dictionary):
        dev = sum(list(map(lambda x: x * x, dictionary.values()))) / len(dictionary)
        return dev

    pass

    def getMaxDraw(self):
        lst = []
        temp = pd.Series(self.select)
        for i in range(0, len(self.select)):
            lst.append(temp[i] - min(temp[i:len(self.select)]))
        return max(lst)

    pass
