import pymysql
import tushare as ts


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


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()
    re = getCodeBySection('深圳成指')
    getCodeBySection('上证50')
    getCodeBySection('沪深300')
