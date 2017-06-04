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
    cursor = db.cursor()
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

    ff = open("C:\\Users\\xjwhh\\Desktop\\t.txt", 'a')
    # value = sys.argv[1]
    # # ff.write(value)
    #
    # values = value.split("?")
    #
    # strategyType = int(values[0])  # 策略类型
    # startDate = values[1]  # 开始日期
    # endDate = values[2]  # 结束日期
    # form = int(values[3])  # 形成期
    # hold = int(values[4])  # 持有期
    # isPla = int(values[5])  # 是否为板块
    # stocks = values[6]
    # stockLists = stocks.split("/")  # 股票代码列表

    strategyType = 0  # 策略类型
    startDate = "2016-03-01"  # 开始日期
    endDate = "2016-06-01"  # 结束日期
    form = 10  # 形成期
    hold = 20  # 持有期
    isPla = 1  # 是否为板块
    stocks = "沪深300"
    stockLists = stocks.split("/")

    # stockLists = ["000001",
    #               "000002",
    #               "000004",
    #               "000005",
    #               "000006",
    #               "000007",
    #               "000008",
    #               "000009",
    #               "000010",
    #               "000011",
    #               "000012",
    #               "000014",
    #               "000016",
    #               "000017",
    #               "000018",
    #               "000019",
    #               "000020",
    #               "000021",
    #               "000022",
    #               "000023",
    #               "000025",
    #               "000026"]  # 股票代码列表

    # stockLists=["000001", "000002", "000004","300001","300002","300003"]

    #板块
    if isPla == 1:
        isPla = True
        plaName = stockLists[0]

    #非板块
    else:
        isPla = False
        plaName = " "
        code = stockLists[0]
        finalDF = getStockInfo(code=code, startDate=startDate, endDate=endDate)

        for i in range(1, len(stockLists)):
            code = stockLists[i]
            df = getStockInfo(code=code, startDate=startDate, endDate=endDate)
            finalDF = finalDF.join(df)
        finalDF.dropna(axis=1, how="any", inplace=True)  # 去除有nan值的列


    # ff.write("\n")
    # ff.write(str(strategyType))
    # ff.write(startDate)
    # ff.write(endDate)
    # ff.write(str(form))
    # ff.write(str(hold))
    # ff.write(str(isPla))
    # ff.write(stocks)
    # ff.write("\n")
    #
    # ff.write("a")
    # ff.write("\n")
    #
    #
    # ff.write("b")
    # ff.write("\n")
    if strategyType == 1:
        strategy = momStrategy.MomentumStrategy(form, hold)
        ff.write("mom")
    else:
        strategy = averStrategy.AverageStrategy(form, hold)
        ff.write("aver")

    ff.write("c")
    ff.write("\n")

    strategyCalculator = strategyCal.StrategyCalculator(strategy)
    ff.write(str(finalDF))
    ff.write(str(isPla))
    ff.write(plaName)
    ff.write("\n")
    strategyCalculator.count(finalDF, isPla, plaName)
    annualReturns = strategyCalculator.annualPro  # 年化收益率
    print(str(annualReturns))
    ff.write(str(annualReturns))
    basicAnnualReturn = strategyCalculator.basicPro  # 基准年化收益率
    print(str(basicAnnualReturn))
    ff.write(str(basicAnnualReturn))
    alpha = strategyCalculator.alpha
    print(str(alpha))
    ff.write(str(alpha))
    beta = strategyCalculator.beta
    print(str(beta))
    ff.write(str(beta))
    sharpeRatio = strategyCalculator.sharpe  # 夏普比率
    print(str(sharpeRatio))
    ff.write(str(sharpeRatio))

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
        print(v)
