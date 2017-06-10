import pymysql
import tushare as ts
import pandas as pd
import sys


def getCodeBySection(plaName):
    if plaName == '深圳成指':
        sql = "select distinct code from basicinfo where section='%s'" % ('深市A股')
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                code = row[0]
                re.append(code)
            print(re)
        except:
            print('get data fail')
    elif plaName == '上证指数':
        sql = "select distinct code from basicinfo where section='%s'" % ('沪市A股')
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                code = row[0]
                re.append(code)
            print(re)
        except:
            print('get data fail')
    elif (plaName == '创业板' or plaName == '中小板'):
        sql = "select distinct code from basicinfo where section='%s'" % (plaName)
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            re = []
            for row in results:
                code = row[0]
                re.append(code)
            print(re)
        except:
            print('get data fail')
    elif plaName == '上证50':
        df = ts.get_sz50s()
        re = list(df['code'])
        print(re)
    elif plaName == '沪深300':
        df = ts.get_hs300s()
        re = list(df['code'])
        print(re)
    return re

def hh():
    endDate='2016-06-10'
    startDate='2016-01-01'
    code='000001'
    sectionName='sza'
    sql = "select distinct date,adjopen,adjhigh,adjlow,adjclose,volume from kdata_" + sectionName + " where date>='%s' and date<='%s' and code='%s' order by date" % (
        startDate, endDate, code)
    print(sql)
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
            resultList = [date, open, high, close, low, volume]
            re.append(resultList)
        df = pd.DataFrame(re, columns=['date', 'open', 'high', 'close', 'low', 'volume'])
        df = df.set_index('date')
    except:
        print('get data fail')
    return df

if __name__ == "__main__":
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()
    import predict
    # re = getCodeBySection('深圳成指')
    # getCodeBySection('上证50')
    # getCodeBySection('沪深300')
    df=hh()


    print(len(df))
    df=df[-82:]
    print(len(df))
    t=predict.svmPredict(df)
    print(t)


