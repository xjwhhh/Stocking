# coding=utf8
import sys

import pandas as pd
import pymysql
# import ffn

# mysql操作
db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
cursor = db.cursor();


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


def getkdata(sectionname, code, startdate, enddate):
    sql = "select distinct date,adjopen,adjhigh,adjlow,adjclose,volume,code from kdata_" + sectionname + " where code='%s' and date>='%s' and date<='%s' order by date" % (
        code, startdate, enddate)
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
        df = pd.DataFrame(re, columns=['date', 'open', 'high', 'close', 'low', 'volume',  'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    paths = sys.argv[0].split("/")
    newpath = ""
    for i in range(0, len(paths) - 2):
        newpath += (paths[i] + "\\")
    newpath += "calculation"
    sys.path.append(newpath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    import smaCal
    import relDev

    # df = getkdata_(sectionname=sys.argv[1], code=sys.argv[2], startdate=sys.argv[3], enddate=sys.argv[4])
    df = getkdata(sectionname="szb", code="200012", startdate="2017-04-12", enddate="2017-05-19")

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

    #每日收益率


    # 相对方差
    oo = relDev.relDev(ss)
    print(oo)
