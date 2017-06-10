import tushare as ts
import sys
import pymysql
import pandas as pd

def svmPredict(oriDf):
    sys.path.append("C:\\Users\\xjwhh\\Anaconda3\\Lib\\site-packages")
    # import svm
    # import naive_bayes
    # import ensemble
    from sklearn import svm
    from sklearn import naive_bayes
    from sklearn import ensemble

    train = 80
    value = pd.Series(oriDf['close'].shift(-1) - oriDf['close'], index=oriDf.index)  # 后一天减前一天的
    oriDf['high-low'] = oriDf['high'] - oriDf['low']  # Difference between High and Low
    oriDf['profit'] = (oriDf['close'] - oriDf['close'].shift(1)) / oriDf['close'].shift(1)  # 今日的收益率
    oriDf['close-open'] = oriDf['close'] - oriDf['open']  # today's Close - Open
    value[value >= 0] = 1  # 0 means rise
    value[value < 0] = 0  # 1 means fall
    oriDf = oriDf.dropna(how='any')
    value = value[~value.isnull()]

    del (oriDf['open'])
    del (oriDf['close'])
    del (oriDf['high'])
    del (oriDf['low'])

    data_train = oriDf[0:train - 1]
    value_train = value[0:train - 1]
    # svm
    classifier = svm.SVC(kernel='poly')
    classifier.fit(data_train, value_train)
    value_predict1 = classifier.predict(oriDf.iloc[[train - 1]])
    # naive_bayes
    bayes = naive_bayes.GaussianNB()
    bayes.fit(data_train, value_train)
    value_predict2 = bayes.predict(oriDf.iloc[[train - 1]])
    # forest
    forest = ensemble.RandomForestClassifier(n_estimators=10)
    forest.fit(data_train, value_train)
    value_predict3 = forest.predict(oriDf.iloc[[train - 1]])
    return 0.1 * int(value_predict1) + 0.2 * int(value_predict2) + 0.7 * int(value_predict3)


def smCorCal(stock, market):
    rate = pd.DataFrame(stock[0] - stock[0].shift(1) / stock[0].shift(1))
    df = pd.concat([rate[0], market[0]], axis=1, keys=['closeS', 'closeM'])
    df = df.dropna(how='any')
    result = df.corr(method='pearson', min_periods=1)
    return result.at['closeS', 'closeM']


# 当日已发生价格
def getTodayData(code):
    df = ts.get_today_ticks(code)
    d = {0: 0}
    time = list(df['time'])
    price = list(df['price'])
    for i in range(0, len(time)):
        minute = time[i][:5]
        # print(minute)
        pr = round(float(price[i]), 2)
        d[minute] = pr
    del (d[0])
    print("#")
    print(len(d))
    for k, v in d.items():
        print(k)
        print(v)


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


def getstockinfo(code, startDate, endDate, sectionName):
    sql = "select distinct date,adjopen,adjhigh,adjlow,adjclose,volume from kdata_" + sectionName + " where date>='%s' and date<='%s' and code='%s' order by date" % (
        startDate, endDate, code)
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
        df = df[-82:]
    except:
        print('get data fail')
    return df

def getStockInfo2(code, section, startDate, endDate):
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
        df = pd.DataFrame(re, columns=['date', 'close'])
        df = df.set_index('date')
    except:
        print('get data fail')
    return df


if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor()
    paths = sys.argv[0].split("\\")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    # newPath="C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation"
    sys.path.append(newPath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")

    # print(newPath)


    #
    import corCal

    code = sys.argv[1]
    date = sys.argv[2]
    before = sys.argv[3]

    # getMinuteData(code, date)
    # getMinuteData('000001', '2017-04-05')
    # getTodayData('000001')
    # getMinuteData()
    getTodayData(code)
    import predict

    # date='2016-06-10'
    # before='2016-01-01'
    # code='000001'
    # sectionName='sza'
    sectionName = getSectionByCode(code)
    df = getstockinfo(code, before, date, sectionName)
    # print(len(df))
    prediction = predict.svmPredict(df)
    print(prediction)
