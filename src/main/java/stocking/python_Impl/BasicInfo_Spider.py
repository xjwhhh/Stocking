# coding=utf-8
import pymysql
import urllib.request
from bs4 import BeautifulSoup

# 通过股票代码爬取股票名称，证监会行业分类，沪深等板块分类
db = pymysql.connect("localhost", "root", "123456", "stock", use_unicode=True, charset="utf8")
cursor = db.cursor()
ff = open("C:\\Users\\xjwhh\\Desktop\\newstock2.txt")
f = open("C:\\Users\\xjwhh\\Desktop\\failstock.txt", 'a')
list_of_all_the_lines = ff.readlines()
for i in range(0, len(list_of_all_the_lines)):
    j = list_of_all_the_lines[i]
    j = j[0:6]
    t = j[0:3]
    if t == "600":
        section = "沪市A股"
    elif t == "601":
        section = "沪市A股"
    elif t == "603":
        section = "沪市A股"
    elif t == "900":
        section = "沪市B股"
    elif t == "000":
        section = "深市A股"
    elif t == "200":
        section = "深市B股"
    elif t == "300":
        section = "创业板"
    elif t == "002":
        section = "中小板"
    else:
        section = "无"
    url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/" + j + "/menu_num/5.phtml"
    html = urllib.request.urlopen(url)
    bsObj = BeautifulSoup(html, 'lxml')
    aClass = bsObj.select("#stockName")
    aClass1 = bsObj.select(".ct")
    # 有板块分类
    if aClass1[3].contents:
        name = aClass[0].contents[0]  # 股票名
        code = aClass[0].contents[1].contents[0][1:-4]  # 股票代码
        industry = aClass1[3].contents[0]  # 证监会板块
    # 无板块分类
    else:
        name = aClass[0].contents[0]
        code = aClass[0].contents[1].contents[0][1:-4]
        industry = '无'
    sql = "insert into basicinfo(name,code,industry,section)values('%s','%s','%s','%s')" % (
        name, code, industry, section)
    try:
        cursor.execute(sql)
        db.commit()
        print(code + " success")
    except:
        db.rollback()
        f.write(code)
        print(code + " fail")
db.close()
