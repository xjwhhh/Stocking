'''
计算最佳形成期，持有期
:param
dataframe,是否是板块，板块名，策略类型(1:M,2:A),仓内持有股票数，给出的是否是持有期，如果是则给出持有期，否则给出形成期
'''

from strategyAbs import Strategy
from momStrategy import MomentumStrategy
from averStrategy import AverageStrategy

class BGraph:
    def count(self, oriDf, isPla, plaName, type, isHold, interval):
        self.profits = []
        self.winChance = []
        self.strategy = Strategy()
        for i in range(2, 31):
            if i + interval > len(oriDf.index):
                break
            form = 0
            hold = 0
            if isHold:
                form = i
                hold = interval
            else:
                form = interval
                hold = i

            if type == 1:
                self.strategy = MomentumStrategy(form, hold)
            else:
                self.strategy = AverageStrategy(form, hold)

            self.strategy.count(oriDf, isPla, plaName)
            self.strategy.getAnnualPro()

            pro = self.strategy.select
            basic = self.strategy.basic

            over = list(pro[x] - basic[x] for x in basic)
            avr = sum(over) / len(over)
            total = 0
            for j in range(0, len(over)):
                if over[j] > 0:
                    total = total + 1

            self.profits.append(avr)
            self.winChance.append(total)

        pass