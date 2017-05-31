import sys
import pymysql
import pandas as pd


def getStockInfo(code, startDate, endDate):
    section = getSectionByCode(code)
    sql = "select distinct date,adjopen,adjhigh,adjclose,adjopen ,volume, code from kdata_" + section + " where code='%s' and date>='%s' and date<='%s' order by date" % (
        code, startDate, endDate)
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
            code = row[6]
            resultlist = [date, open, high, close, low, volume, code]
            re.append(resultlist)
        df = pd.DataFrame(re, columns=['date', 'open', 'high', 'close', 'low', 'volume', 'code'])
    except:
        print('get data fail')
    return df


def getSectionByCode(code):
    subcode = code[:3]
    if (subcode == "600"):
        section = "sha0"
    elif (subcode == "601"):
        section = "sha1"
    elif (subcode == "603"):
        section = "sha3"
    elif (subcode == "900"):
        section = "shb"
    elif (subcode == "000"):
        section = "sza"
    elif (subcode == "200"):
        section = "szb"
    elif (subcode == "300"):
        section = "cyb"
    elif (subcode == "002"):
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

    strategyType = sys.argv[1]  # 策略类型
    startDate = sys.argv[2]  # 开始日期
    endDate = sys.argv[3]  # 结束日期
    form = int(sys.argv[4])  # 形成期
    hold = int(sys.argv[5])  # 持有期
    isPla = sys.argv[6]  # 是否为板块
    stocks = sys.argv[7]
    stockLists = stocks.split("/")  # 股票代码列表
