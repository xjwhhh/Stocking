# 爬取股票相关新闻
import urllib.request
from bs4 import BeautifulSoup
import tushare as ts


#
# def getnews():
#     url = "http://money.163.com/"
#     html = urllib.request.urlopen(url)
#     bsObj = BeautifulSoup(html, 'lxml')
#     xx = bsObj.find_all("a")
#     m = 0
#     for i in range(len(xx) - 340, len(xx)):
#         tt = xx[i]
#         if (len(tt.text) > 10 and tt.get('href') != ""):
#             print(tt.get('href'))
#             print(tt.text.strip())
#             m = m + 1


# 实时更新的股票新闻
def getMarketNews():
    num = 50
    news = ts.get_latest_news(top=num)
    classify = list(news['classify'])
    title = list(news['title'])
    time = list(news['time'])
    url = list(news['url'])
    print(num)
    for i in classify:
        print(i)
    for i in title:
        print(i)
    for i in time:
        print(i)
    for i in url:
        print(i)


if __name__ == "__main__":
    getMarketNews()
