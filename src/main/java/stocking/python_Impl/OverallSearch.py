# coding=utf8
import sys

import pandas as pd
import pymysql


def getallkdata(sectioname, date):
    sql = "select distinct date,adjopen,adjclose,adjhigh,adjlow,volume,code from kdata_" + sectioname + " where date='%s' " % (
        date)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            date = row[0]
            open = row[1]
            close = row[2]
            high = row[3]
            low = row[4]
            volume = row[5]
            code = row[6]
            resultlist = [date, open, close, high, low, volume, code]
            re.append(resultlist)
        df = pd.DataFrame(re, columns=['date', 'open', 'close', 'high', 'low', 'volume', 'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()

    paths = sys.argv[0].split("\\")
    newpath = ""
    for i in range(0, len(paths) - 2):
        newpath += (paths[i] + "\\")
    newpath += "calculation"
    sys.path.append(newpath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    import decBelow
    import decGre
    import incAbove
    import incGre

    totalDeal = 0  # 当日总交易量
    limitUpNum = 0  # 当日涨停股票数
    limitDownNum = 0  # 当日跌停股票数
    overFivePerNum = 0  # 涨幅超过5%的股票数
    belowFivePerNum = 0  # 跌幅超过5%的股票数
    oc_overPFivePerNum = 0  # 开盘-收盘大于5%*上一个交易日收盘价的股票个数
    oc_belowMFivePerNum = 0  # 开盘-收盘小于-5%*上一个交易日收盘价的股票个数
    numOfStocks = 0
    for i in {"sha0", "sha1", "sha3", "shb", "sza", "szb", "cyb", "zxb"}:
        todaydf = getallkdata(i, sys.argv[1])
        yesterdaydf = getallkdata(i, sys.argv[2])
        newdf = pd.merge(todaydf, yesterdaydf, on=['code'], how='inner')  # 两天都有的股票
        closex = newdf['close_x']  # 当天close
        highx = newdf['high_x']  # 当天high
        lowx = newdf['low_x']  # 当天low
        volumex = newdf['volume_x']  # 当天交易量
        closey = newdf['close_y']  # 前一天close
        todaynewdf = newdf.iloc[:, 0:3]  # 当天date,open与close
        todaynewdf.rename(columns={'open_x': 'open', 'close_x': 'close', 'date_x': 'date'}, inplace=True)  # 更改列名
        todaynewdf = todaynewdf.set_index('date')
        totalDeal += volumex.sum()
        limitUpNum += incAbove.incAbove(closey, highx, 0.1)
        limitDownNum += decBelow.decBelow(closey, lowx, -0.1)
        overFivePerNum += incAbove.incAbove(closey, closex, 0.05)
        belowFivePerNum += decBelow.decBelow(closey, closex, -0.05)
        oc_overPFivePerNum += incGre.incGre(closey, todaynewdf, 0.05)
        oc_belowMFivePerNum += decGre.decGre(closey, todaynewdf, -0.05)
        numOfStocks += len(todaynewdf)
    print(totalDeal)
    print(limitUpNum)
    print(limitDownNum)
    print(overFivePerNum)
    print(belowFivePerNum)
    print(oc_overPFivePerNum)
    print(oc_belowMFivePerNum)
    print(numOfStocks)
