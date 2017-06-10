import pandas as pd
import pymysql
import sys
import tushare as ts


def getStockInfo(code, section, startDate, endDate):
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
        df = pd.DataFrame(re, columns=['date', code])
        df = df.set_index('date')
    except:
        print('get data fail')
    return df


def getSectionByCode(code):
    subCode = code[:3]
    if subCode == "600":
        section = "sha0"
    elif subCode == "601":
        section = "sha1"
    elif subCode == "603":
        section = "sha3"
    elif subCode == "900":
        section = "shb"
    elif subCode == "000":
        section = "sza"
    elif subCode == "200":
        section = "szb"
    elif subCode == "300":
        section = "cyb"
    elif subCode == "002":
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
    paths = sys.argv[0].split("\\")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    sys.path.append(newPath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")

    import averStrategy
    import momStrategy
    import strategyCal

    value = sys.argv[1]
    # value="2?2016-03-01?2016-06-01?10?20?1?深市A股"
    values = value.split("?")
    strategyType = int(values[0])  # 策略类型
    startDate = values[1]  # 开始日期
    endDate = values[2]  # 结束日期
    form = int(values[3])  # 形成期
    hold = int(values[4])  # 持有期
    isPla = int(values[5])  # 是否为板块
    stocks = values[6]
    stockLists = stocks.split("/")  # 股票代码列表

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

    if strategyType == 1:
        strategy = momStrategy.MomentumStrategy(form, hold)
    else:
        strategy = averStrategy.AverageStrategy(form, hold)

    strategyCalculator = strategyCal.StrategyCalculator(strategy)
    strategyCalculator.count(finalDF, isPla, plaName)

    annualReturns = strategyCalculator.annualPro  # 年化收益率
    print(str(annualReturns))

    basicAnnualReturn = strategyCalculator.basicPro  # 基准年化收益率
    print(str(basicAnnualReturn))

    alpha = strategyCalculator.alpha
    print(str(alpha))

    beta = strategyCalculator.beta
    print(str(beta))

    sharpeRatio = strategyCalculator.sharpe  # 夏普比率
    print(str(sharpeRatio))

    maxDrawDown = strategyCalculator.maxDraw
    print(str(maxDrawDown))  # 最大回撤

    profits = strategyCalculator.pros
    print(len(profits))

    for k, v in profits.items():
        key = k
        date = str(k).split(" ")[0]
        print(date)  # dates

    for k, v in profits.items():
        print(v)  # profits

    basicProfits = strategyCalculator.basics
    for k, v in basicProfits.items():
        print(v)

    sets = strategyCalculator.candidates
    print(len(sets.get(key)))
    for k, v in sets.items():
        index = v.index
        value = list(v)
        for i in index:
            print(i)
        for i in value:
            print(i)
