import pymysql
import sys
import pandas as pd
import random


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
        print('get data fail')
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
        section = "无"
    return section


def getCodeBySection(plaName):
    sql = "select distinct code from basicinfo where section='%s'" % (plaName)
    # print(sql)
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        re = []
        for row in results:
            code = row[0]
            re.append(code)
    except:
        print('get data fail')
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

    # 板块
    if isPla == 1:
        isPla = False
        plaName = stockLists[0]
        stockLists = getCodeBySection(plaName)
        if len(stockLists) > 200:
            # stockLists = stockLists[:int(len(stockLists) / 2)]
            stockLists = random.sample(stockLists, int(len(stockLists) / 2))

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

    bgraph = bGraph.BGraph()
    bgraph.count(finalDF, isPla, plaName, strategyType, isHold, interval)
    profits = bgraph.profits
    print(len(profits))
    for value in profits:
        print(value)
    winChance = bgraph.winChance
    for value in winChance:
        print(value)
