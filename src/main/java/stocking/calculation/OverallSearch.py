# coding=utf8
import sys

import pandas as pd
import pymysql
import decBelow
import decGre
import incAbove
import incGre

# mysql操作
db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
cursor = db.cursor();


def getallkdata(sectioname, date):
    sql = "select date,open,adjclose,code from kdata_" + sectioname + " where date='%s' " % (
        date)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            date = row[0]
            open = row[1]
            adjclose = row[2]
            code = row[3]
            resultlist = [date, open, adjclose, code]
            re.append(resultlist)
        df = pd.DataFrame(re, columns=['date', 'open', 'close', 'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    overFivePerNum = 0  # 涨幅超过5%的股票数
    belowFivePerNum = 0  # 跌幅超过5%的股票数
    oc_overPFivePerNum = 0  # 开盘-收盘大于5%*上一个交易日收盘价的股票个数
    oc_belowMFivePerNum = 0  # 开盘-收盘小于-5%*上一个交易日收盘价的股票个数
    for i in {"sha0", "sha1", "sha3", "shb", "sza", "szb", "cyb", "zxb"}:
        # df = getallkdata(i, sys.argv[1])
        todaydf = getallkdata('szb', "2017-05-17")
        yesterdaydf = getallkdata('szb', "2017-05-16")
        newdf = pd.merge(todaydf, yesterdaydf, on=['code'])  # 两天都有的股票
        closex = newdf['close_x']  # 当天adjclose
        closey = newdf['close_y']  # 前一天adjclose
        todaynewdf = newdf.iloc[:, 1:3]  # 当天open与adjclose
        todaynewdf.rename(columns={'open_x': 'open', 'close_x': 'close'}, inplace=True)  # 更改列名
        todaynewdf['close'] = todaynewdf['close'].astype('int')  # 转换为int
        todaynewdf['open'] = todaynewdf['open'].astype('int')
        overFivePerNum += incAbove.incAbove(closex, closey, 0.05)
        belowFivePerNum += decBelow.decBelow(closex, closey, 0.05)
        # oc_overPFivePerNum += incGre.incGre(todaynewdf, closey, 0.05)
        # print(3)
        # oc_belowMFivePerNum += decGre.decGre(todaynewdf, closey, 0.05)
        # print(4)
        # print("vnirdkc")
    # Traceback (most recent call last):
# File "pandas\index.pyx", line 154, in pandas.index.IndexEngine.get_loc (pandas\index.c:4279)
# File "pandas\src\hashtable_class_helper.pxi", line 404, in pandas.hashtable.Int64HashTable.get_item (pandas\hashtable.c:8543)
# TypeError: an integer is required
