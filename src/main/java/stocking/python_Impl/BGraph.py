import pandas as pd
import pymysql
import sys
import tushare as ts


def getStockInfo(code, section, startDate, endDate):
    sql = "select distinct date,adjclose from kdata_" + section + " where date>='%s' and date<='%s' and code='%s' order by date " % (
        startDate, endDate, code)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            date = row[0]
            close = row[1]
            resultList = [date, close]
            re.append(resultList)
        df = pd.DataFrame(re, columns=['date', code])
        df = df.set_index('date')
    except:
        print('get data failll')
    return df


def getSectionByCode(code):
    subcode = code[:3]
    if subcode == "600":
        section = "sha0"
    elif subcode == "601":
        section = "sha1"
    elif subcode == "603":
        section = "sha3"
    elif subcode == "900":
        section = "shb"
    elif subcode == "000":
        section = "sza"
    elif subcode == "200":
        section = "szb"
    elif subcode == "300":
        section = "cyb"
    elif subcode == "002":
        section = "zxb"
    else:
        section = "szb"
    return section


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
                # print(re)
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
                # print(re)
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
                # print(re)
        except:
            print('get data fail')
    elif plaName == '上证50':
        df = ts.get_sz50s()
        re = list(df['code'])
        # print(re)
    elif plaName == '沪深300':
        df = ts.get_hs300s()
        re = list(df['code'])
        # print(re)
    return re


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()
    paths = sys.argv[0].split("/")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    sys.path.append(newPath)
    sys.path.append("C:\\Users\\朱晨乾\\IdeaProjects\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")
    import bGraph

    value = sys.argv[1]
    values = value.split("?")
    strategyType = int(values[0])  # 策略类型
    startDate = values[1]  # 开始日期
    endDate = values[2]  # 结束日期
    isHold = int(values[3])  # s是否是形成期
    interval = int(values[4])  # 已知时间
    isPla = int(values[5])  # 是否为板块
    stocks = values[6]
    stockLists = stocks.split("/")  # 股票代码列表

    # strategyType = 1  # 策略类型
    # startDate = '2017-05-10'  # 开始日期
    # endDate = '2017-05-28'  # 结束日期
    # isHold = 0  # s是否是形成期
    # interval = 4  # 已知时间
    # isPla = 0  # 是否为板块
    # stocks = '000001/000002'
    # stockLists = stocks.split("/")  # 股票代码列表
    # print(len(stockLists))

    # 板块
    if isPla == 1:
        isPla = True
        plaName = stockLists[0]
        stockLists = getCodeBySection(plaName)
        # if len(stockLists) > 200:
        #     # stockLists = stockLists[:int(len(stockLists) / 2)]
        #     stockLists = random.sample(stockLists, int(len(stockLists) / 2))

    # 非板块
    else:
        isPla = False
        plaName = " "

    code = stockLists[0]
    section = getSectionByCode(code)
    finalDF = getStockInfo(code=code, section=section, startDate=startDate, endDate=endDate)

    for i in range(1, len(stockLists)):
        code = stockLists[i]
        section = getSectionByCode(code)
        df = getStockInfo(code=code, section=section, startDate=startDate, endDate=endDate)
        finalDF = finalDF.join(df, how='outer')
    finalDF.dropna(axis=1, how="any", inplace=True)  # 去除有nan值的列

    # 持有期
    if isHold == 1:
        isHold = True
    # 形成期
    else:
        isHold = False

    bGraph1 = bGraph.BGraph()
    bGraph1.count(finalDF, isPla, plaName, strategyType, isHold, interval)
    profits = bGraph1.profits
    print(len(profits))
    for value in profits:
        print(value)
    winChance = bGraph1.winChance
    for value in winChance:
        print(value)
