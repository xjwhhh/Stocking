# coding=utf8
import sys

import pandas as pd
import pymysql
import smaCal
import relDev

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
    sql = "select * from kdata_" + sectionname + " where code='%s' and date>='%s' and date<='%s' order by date" % (
        code, startdate, enddate)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            date = row[0]
            open = row[1]
            high = row[2]
            low = row[4]
            volume = row[5]
            amount = row[6]
            adjclose = row[7]
            code = row[8]
            resultlist = [date, open, high, adjclose, low, volume, amount, code]
            re.append(resultlist)
        df = pd.DataFrame(re, columns=['date', 'open', 'high', 'close', 'low', 'volumn', 'amount', 'code'])
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    # df = getkdata_(sectionname=sys.argv[1], code=sys.argv[2], startdate=sys.argv[3], enddate=sys.argv[4])
    df = getkdata(sectionname="shb", code="900932", startdate="2017-04-12", enddate="2017-05-19")
    ss=df['close']
    #平均值
    for i in {5,10,20,30,60}:
        tt=smaCal.smaCal(ss,i)
        print(tt)
    print(ss)
    #相对方差
    # oo=relDev.relDev(ss)
    # print(oo)
    # 调用方法获得平均值,每日收益率，相对方差，并返回
    #相对方差计算似乎有问题
    #平均值要传之前的数据吗
