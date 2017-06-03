import tushare as ts
import sys


# 信息地雷
def getnews1(code):
    news = ts.get_notices(code=code)
    type = list(news['type'])
    title = list(news['title'])
    date = list(news['date'])
    url = list(news['url'])
    print(len(type))
    for i in type:
        print(i)
    for i in title:
        print(i)
    for i in date:
        print(i)
    for i in url:
        print(i)


if __name__ == "__main__":
    code = sys.argv[1]
    getnews1(code)
