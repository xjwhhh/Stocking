import tushare as ts
import sys
import pymysql
import pandas as pd

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

def getstockinfo(code,startDate,endDate,sectionName):
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
        df=df[-82:]
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
    before=sys.argv[3]

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
    sectionName=getSectionByCode(code)
    df=getstockinfo(code,before,date,sectionName)
    # print(len(df))
    prediction=predict.svmPredict(df)
    print(prediction)








