import tushare as ts
import sys


def getData_huanshoulv(date, reasonson):
    # print(111)
    reasons = [
        # '无价格涨跌幅限制的证券',
        '日换手率达到20%的前五只证券',
        # '日涨幅偏离值达到7%的前五只证券',
        # '日跌幅偏离值达到7%的前五只证券',
        # '有价格涨跌幅限制的日价格振幅达到15%的前三只证券',
        # '有价格涨跌幅限制的日换手率达到20%的前三只证券',
        # '有价格涨跌幅限制的日收盘价格涨幅偏离值达到7%的前三只证券',
        # '连续三个交易日内，涨幅偏离值累计达到12%的ST证券、*ST证券和未完成股改证券',
        # '连续三个交易日内，涨幅偏离值累计达到20%的证券',
        # '退市整理期',
        # '非ST、*ST和S证券连续三个交易日内收盘价格涨幅偏离值累计达到20%的证券'
    ]

    rea = '日换手率达到20%的前五只证券'
    df = ts.top_list(date)
    # df = df.sort(columns='reason')
    name = list(df['name'])
    change = list(df['pchange'])
    reason = list(df['reason'])
    # print(len(code))
    # for i in code:
    #     print(i)
    # for i in name:
    #     print(i)
    # for i in reason:
    #     print(i)
    names = []
    changes = []
    for i in range(0, len(name)):
        if reason[i] == rea:
            names.append(name[i])
            changes.append(change[i])
    print(len(names))
    for i in names:
        print(i)
    for i in changes:
        print(i)


if __name__ == "__main__":
    date = sys.argv[1]
    # print(date)
    reason = sys.argv[2]
    # print(reason)
    getData_huanshoulv(date, '')
