from sqlalchemy import create_engine
import tushare as ts
import datetime

# 获取大盘指数k线数据
engine = create_engine('mysql+pymysql://root:123456@127.0.0.1/stock?charset=utf8')

startDate = datetime.datetime.now().strftime('%Y-%m-%d')
endDate = datetime.datetime.now().strftime('%Y-%m-%d')

# 上证指数
df = ts.get_h_data('000001', index=True, start=startDate, end=endDate)
df['code'] = '上证指数'
df.to_sql('market_index', engine, if_exists='append')

# 深圳成指
df = ts.get_h_data('399001', index=True, start=startDate, end=endDate)
df['code'] = '深圳成指'
df.to_sql('market_index', engine, if_exists='append')

# 沪深300
df = ts.get_h_data('000300', index=True, start=startDate, end=endDate)
df['code'] = '沪深300'
df.to_sql('market_index', engine, if_exists='append')

# 上证50
df = ts.get_h_data('000016', index=True, start=startDate, end=endDate)
df['code'] = '上证50'
df.to_sql('market_index', engine, if_exists='append')

# 中小板
df = ts.get_h_data('399005', index=True, start=startDate, end=endDate)
df['code'] = '中小板'
df.to_sql('market_index', engine, if_exists='append')

# 创业板
df = ts.get_h_data('399006', index=True, start=startDate, end=endDate)
df['code'] = '创业板'
df.to_sql('market_index', engine, if_exists='append')
