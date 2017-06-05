import pymysql
import pandas as pd


class cache(object):
    def __init__(self, ):
        self.data = []

    def getStockInfo(self, code, section, startDate, endDate):
        db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
        cursor = db.cursor()
        sql = "select distinct date,adjclose from kdata_" + section + " where date>='%s' and date<='%s' and code='%s' order by date" % (
            startDate, endDate, code)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                date = row[0]
                close = row[1]
                resultlist = [date, close]
                re.append(resultlist)
        except:
            print('get data fail')
