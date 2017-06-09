import tushare as ts


def getMarketIndex():
    df = ts.get_index()

    print(df)


if __name__ == "__main__":
    getMarketIndex()
