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
            resultList = [date, open, close, high, low, volume, code]
            re.append(resultList)
        df = pd.DataFrame(re, columns=['date', 'open', 'close', 'high', 'low', 'volume', 'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()

    paths = sys.argv[0].split("\\")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    # newPath='C:\\Users\\朱晨乾\\IdeaProjects\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation'
    sys.path.append(newPath)
    # print(newPath)
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
        todayDF = getallkdata(i, sys.argv[1])
        yesterdayDF = getallkdata(i, sys.argv[2])
        # todayDF = getallkdata(i, '2017-05-24')
        # yesterdayDF = getallkdata(i, '2017-05-23')
        newDF = pd.merge(todayDF, yesterdayDF, on=['code'], how='inner')  # 两天都有的股票
        closeX = newDF['close_x']  # 当天close
        highX = newDF['high_x']  # 当天high
        lowX = newDF['low_x']  # 当天low
        volumeX = newDF['volume_x']  # 当天交易量
        closeY = newDF['close_y']  # 前一天close
        todayNewDF = newDF.iloc[:, 0:3]  # 当天date,open与close
        todayNewDF.rename(columns={'open_x': 'open', 'close_x': 'close', 'date_x': 'date'}, inplace=True)  # 更改列名
        todayNewDF = todayNewDF.set_index('date')
        totalDeal += volumeX.sum()
        limitUpNum += incAbove.incAbove(closeY, highX, 0.1)
        limitDownNum += decBelow.decBelow(closeY, lowX, -0.1)
        overFivePerNum += incAbove.incAbove(closeY, closeX, 0.05)
        belowFivePerNum += decBelow.decBelow(closeY, closeX, -0.05)
        oc_overPFivePerNum += incGre.incGre(closeY, todayNewDF, 0.05)
        oc_belowMFivePerNum += decGre.decGre(closeY, todayNewDF, -0.05)
        numOfStocks += len(todayNewDF)
    print(totalDeal)
    print(limitUpNum)
    print(limitDownNum)
    print(overFivePerNum)
    print(belowFivePerNum)
    print(oc_overPFivePerNum)
    print(oc_belowMFivePerNum)
    print(numOfStocks)
