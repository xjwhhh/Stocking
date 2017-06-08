import tushare as ts
import sys


# 历史价格
def getMinuteData(code, date):
    df = ts.get_tick_data(code, date)
    # df=ts.get_today_ticks('000001')
    d = {0: 0}
    time = list(df['time'])
    price = list(df['price'])
    for i in range(0, len(time)):
        minute = time[i][:5]
        # print(minute)
        pr = round(float(price[i]), 2)
        d[minute] = pr
    del (d[0])
    print(len(d))
    for k, v in d.items():
        print(k)
        print(v)
        # print(df)


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
    # print("\n")
    print(len(d))
    for k, v in d.items():
        print(k)
        print(v)


if __name__ == "__main__":
    code = sys.argv[1]
    date = sys.argv[2]
    getMinuteData(code, date)
    # getTodayData(code)
    # getMinuteData()
