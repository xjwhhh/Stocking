# coding=utf8
import sys

import pandas as pd
import pymysql

def getkdata(sectionName, code, startDate, endDate):
    sql = "select distinct date,adjopen,adjhigh,adjlow,adjclose,volume,code from kdata_" + sectionName + " where date>='%s' and date<='%s' and code='%s' order by date" % (
         startDate, endDate, code)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            date = row[0]
            open = row[1]
            high = row[2]
            low = row[3]
            close = row[4]
            volume = row[5]
            code = row[6]
            resultList = [date, open, high, close, low, volume, code]
            re.append(resultList)
        df = pd.DataFrame(re, columns=['date', 'open', 'high', 'close', 'low', 'volume', 'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()
    paths = sys.argv[0].split("/")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    sys.path.append(newPath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    import smaCal
    import relDev

    df = getkdata(sectionName=sys.argv[1], code=sys.argv[2], startDate=sys.argv[3], endDate=sys.argv[4])

    print(len(df))

    openList = list(df['open'])
    highList = list(df['high'])
    lowList = list(df['low'])
    volumeList = list(df['volume'])
    closeList = list(df['close'])
    dateList = list(df['date'])

    for i in openList:
        print(i)
    for i in highList:
        print(i)
    for i in lowList:
        print(i)
    for i in volumeList:
        print(i)
    for i in closeList:
        print(i)
    for i in dateList:
        print(i)

    df = df.set_index('date')
    ss = df['close']

    # 平均值
    for i in {5, 10, 20, 30, 60}:
        tt = smaCal.smaCal(ss, i)
        averageList = list(tt)
        for t in averageList:
            print(t)

    # 收益率
    profit = []
    ttt = list(ss)
    profit.append(0)
    for i in range(1, len(ttt)):
        t = (ttt[i] - ttt[i - 1]) / ttt[i - 1]
        profit.append(t)
    for i in profit:
        print(i)

    # 相对方差
    variance = relDev.relDev(ss)
    print(variance)
