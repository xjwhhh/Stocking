# coding=utf8
import sys

import pandas as pd
import pymysql


# def getstockinfobycode(code):
#     sql = "select * from basicinfo where code='%s'" % code
#     try:
#         cursor.execute(sql)
#         results = cursor.fetchall()
#         re = []
#         for row in results:
#             name = row[0]
#             code = row[1]
#             industry = row[2]
#             section = row[3]
#             resultlist = [name, code, industry, section]
#             re.append(resultlist)
#         print(re)
#         df = pd.DataFrame(re, columns=['name', 'code', 'industry', 'section'])
#     except:
#         print("get data fail")
#     return df


def getkdata(sectionName, code, startDate, endDate):
    sql = "select distinct date,adjopen,adjhigh,adjlow,adjclose,volume,code from kdata_" + sectionName + " where code='%s' and date>='%s' and date<='%s' order by date" % (
        code, startDate, endDate)
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
            resultlist = [date, open, high, close, low, volume, code]
            re.append(resultlist)
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

    print(df['open'])
    print(df['high'])
    print(df['low'])
    print(df['volume'])
    print(df['close'])
    print(df['date'])

    df = df.set_index('date')
    ss = df['close']

    # 平均值
    for i in {5, 10, 20, 30, 60}:
        tt = smaCal.smaCal(ss, i)
        print(tt)

    # 收益率
    profit = []
    ttt = list(ss)
    profit.append(0)
    for i in range(1, len(ttt)):
        t = (ttt[i] - ttt[i - 1]) / ttt[i - 1]
        profit.append(t)
    profitSeries = pd.Series(profit)
    print(profitSeries)

    # 相对方差
    variance = relDev.relDev(ss)
    print(variance)
