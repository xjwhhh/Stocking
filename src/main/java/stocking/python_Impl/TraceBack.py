import sys
import pymysql
import pandas as pd


def getStockInfo(code, startDate, endDate):
    section = getSectionByCode(code)
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


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor();
    paths = sys.argv[0].split("/")
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
    import strategyAbs

    # strategyType = sys.argv[1]  # 策略类型
    # startDate = sys.argv[2]  # 开始日期
    # endDate = sys.argv[3]  # 结束日期
    # form = int(sys.argv[4])  # 形成期
    # hold = int(sys.argv[5])  # 持有期
    # isPla = sys.argv[6]  # 是否为板块
    # stocks = sys.argv[7]
    # stockLists = stocks.split("/")  # 股票代码列表


    strategyType = 1  # 策略类型
    startDate = "2016-03-01"  # 开始日期
    endDate = "2016-05-01"  # 结束日期
    form = 10  # 形成期
    hold = 20  # 持有期
    isPla = False  # 是否为板块

    # stocks = sys.argv[7]
    stockLists = ["000001",
                  "000002",
                  "000004",
                  "000005",
                  "000006",
                  "000007",
                  "000008",
                  "000009",
                  "000010",
                  "000011",
                  "000012",
                  "000014",
                  "000016",
                  "000017",
                  "000018",
                  "000019",
                  "000020",
                  "000021",
                  "000022",
                  "000023",
                  "000025",
                  "000026"]  # 股票代码列表

    if isPla:
        plaName = stockLists[0]
    else:
        plaName = " "

    code = stockLists[0]
    finalDF = getStockInfo(code=code, startDate=startDate, endDate=endDate)

    for i in range(1, len(stockLists)):
        code = stockLists[i]
        df = getStockInfo(code=code, startDate=startDate, endDate=endDate)
        finalDF = finalDF.join(df)
    finalDF.dropna(axis=1, how="any", inplace=True)  # 去除有nan值的列

    if strategyType == 1:
        strategy = momStrategy.MomentumStrategy(form, hold)
    else:
        strategy = averStrategy.AverageStrategy(form, hold)

    strategyCalculator = strategyCal.StrategyCalculator(strategy)
    strategyCalculator.count(finalDF, isPla, plaName)
    annualReturns = strategyCalculator.annualPro  # 年化收益率
    print(annualReturns)
    basicAnnualReturn = strategyCalculator.basicPro  # 基准年化收益率
    print(basicAnnualReturn)
    alpha = strategyCalculator.alpha
    print(alpha)
    beta = strategyCalculator.beta
    print(beta)
    sharpeRatio = strategyCalculator.sharpe  # 夏普比率
    print(sharpeRatio)

    maxDrawDown = strategyCalculator.maxDraw
    print(maxDrawDown)  # 最大回撤

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
        print(v)
