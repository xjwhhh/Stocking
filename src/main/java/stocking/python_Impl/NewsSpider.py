# 爬取股票相关新闻
import urllib.request
from bs4 import BeautifulSoup


def getnews():
    url = "http://money.163.com/"
    html = urllib.request.urlopen(url)
    bsObj = BeautifulSoup(html, 'lxml')
    xx = bsObj.find_all("a")
    m = 0
    for i in range(len(xx) - 340, len(xx)):
        tt = xx[i]
        if (len(tt.text) > 10 and tt.get('href') != ""):
            print(tt.get('href'))
            print(tt.text.strip())
            m = m + 1


if __name__ == "__main__":
    getnews()
