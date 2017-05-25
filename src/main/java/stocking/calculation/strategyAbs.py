'''
策略的抽象类
:param
筛选得到的股票（index=日期，column=每个股票的代号），形成期，持有期，仓内持有股票数（只有均值策略需要）
:return
经过策略计算得到的股票（index=日期，column=赢家组合的代码、持有期收益率）
'''

from abc import abstractmethod


class Strategy(object):
    @abstractmethod
    def _get(self, oriDf, form, hold, num): pass
