import tushare as ts
import sys


def getData(date):
    df = ts.top_list(date)
    df = df.sort(columns='reason')
    code = list(df['code'])
    name = list(df['name'])
    reason = list(df['reason'])
    print(len(code))
    for i in code:
        print(i)
    for i in name:
        print(i)
    for i in reason:
        print(i)


if __name__ == "__main__":
    date = sys.argv[1]
    getData('2017-05-11')
