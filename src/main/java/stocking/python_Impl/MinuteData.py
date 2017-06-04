import tushare as ts
import sys


def getMinuteData(code, date):
    df = ts.get_tick_data('600848', date='2017-06-02')
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


if __name__ == "__main__":
    # code=sys.argv[1]
    # date=sys.argv[2]
    getMinuteData('0', 'p')
