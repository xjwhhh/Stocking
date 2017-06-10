import datetime
import tushare as ts
from sqlalchemy import create_engine

engine = create_engine('mysql+pymysql://root:123456@127.0.0.1/stock?charset=utf8')

startdate = datetime.datetime.now().strftime('%Y-%m-%d')
enddate = datetime.datetime.now().strftime('%Y-%m-%d')
# startdate = '2017-06-06'
# enddate = '2017-06-07'

ff1 = open("C:\\Users\\xjwhh\\Desktop\\stock1\沪市A股0-855.txt")
ff2 = open("C:\\Users\\xjwhh\\Desktop\\stock1\沪市A股1-167.txt")
ff3 = open("C:\\Users\\xjwhh\\Desktop\\stock1\沪市A股3-315.txt")
ff4 = open("C:\\Users\\xjwhh\\Desktop\\stock1\沪市B股-55.txt")
ff5 = open("C:\\Users\\xjwhh\\Desktop\\stock1\深市A股-507.txt")
ff6 = open("C:\\Users\\xjwhh\\Desktop\\stock1\深市B股-59.txt")
ff7 = open("C:\\Users\\xjwhh\\Desktop\\stock1\创业板-649.txt")
ff8 = open("C:\\Users\\xjwhh\\Desktop\\stock1\中小板-865.txt")

# df=ts.get_stock_basics()

list_of_all_the_lines = ff6.readlines()
# 深市B股
for i in range(0, len(list_of_all_the_lines)):
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_szb', engine, if_exists='append')
        print(j)
print('深市B股成功')

list_of_all_the_lines = ff4.readlines()
# 沪市B股
for i in range(0, len(list_of_all_the_lines)):
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_shb', engine, if_exists='append')
        print(j)
print('沪市B股成功')

list_of_all_the_lines = ff1.readlines()
# 沪市A股0
for i in range(0, len(list_of_all_the_lines)):
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_sha0', engine, if_exists='append')
        print(j)
print('沪市A股0成功')

list_of_all_the_lines = ff2.readlines()
# 沪市A股1
for i in range(0, len(list_of_all_the_lines)):
    # startdate1 = df.ix[j]['timeToMarket']
    # date = str(startdate1)
    # newdate = date[0:4] + "-" + date[4:6] + "-" + date[6:8]
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_sha1', engine, if_exists='append')
        print(j)
print('沪市A股1成功')

list_of_all_the_lines = ff3.readlines()
# 沪市A股3
for i in range(0, len(list_of_all_the_lines)):
    # startdate1 = df.ix[j]['timeToMarket']
    # date = str(startdate1)
    # newdate = date[0:4] + "-" + date[4:6] + "-" + date[6:8]
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_sha3', engine, if_exists='append')
        print(j)
print('沪市A股3成功')
#
list_of_all_the_lines = ff5.readlines()
# 深市A股
for i in range(0, len(list_of_all_the_lines)):
    # startdate1 = df.ix[j]['timeToMarket']
    # date = str(startdate1)
    # newdate = date[0:4] + "-" + date[4:6] + "-" + date[6:8]
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_sza', engine, if_exists='append')
        print(j)
print('深市A股成功')

list_of_all_the_lines = ff7.readlines()
# 创业板
for i in range(0, len(list_of_all_the_lines)):
    # startdate1 = df.ix[j]['timeToMarket']
    # date = str(startdate1)
    # newdate = date[0:4] + "-" + date[4:6] + "-" + date[6:8]
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_cyb', engine, if_exists='append')
        print(j)
print('创业板成功')

list_of_all_the_lines = ff8.readlines()
# 中小板
for i in range(0, len(list_of_all_the_lines)):
    # startdate1 = df.ix[j]['timeToMarket']
    # date = str(startdate1)
    # newdate = date[0:4] + "-" + date[4:6] + "-" + date[6:8]
    j = list_of_all_the_lines[i]
    j = j[0:6]
    newdf2 = ts.get_h_data(j, start=startdate, end=enddate)
    if (str(newdf2) != 'None'):
        newdf2.rename(columns={'close': "adjclose", 'open': 'adjopen', 'high': 'adjhigh', 'low': 'adjlow'},
                      inplace=True)
        newdf2['code'] = j
        newdf2.to_sql('kdata_zxb', engine, if_exists='append')
        print(j)
print('中小板成功')
