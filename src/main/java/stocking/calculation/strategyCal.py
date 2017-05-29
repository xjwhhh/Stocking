'''
策略的调用主要是调用这个方法,用成员变量传递所有返回值
:param
初始dataframe（已经过初步筛选）
'''

class StrategyCalculator:
    def __init__(self, strategy):
        self.stra = strategy

    def count(self, oridf):
        self.stra.count(oridf);
        self.annualPro = self.stra.getAnnualPro()
        self.basicPro = self.stra.getBasicAnnualPro()
        pass
