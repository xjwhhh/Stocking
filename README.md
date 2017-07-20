#软工三大作业迭代三——量化投资系统Stocking
##文档
所有文档均在document文件夹里
##技术
前端采用bootstrap框架，通信使用servlet，用json作为传送方式，后端java与Python并用，Java程序使用processbuilder调用Python程序，数据库使用mysql与mongodb
需要安装python环境，最好下载anaconda，引入第三方包tushare,pymysql
实现了个股，市场实时，历史数据查看，个股k线图，股票比较，使用动量策略，均值回归策略进行股票回测，实时新闻爬取
使用机器学习实现对个股未来上涨可能性的预测，个股情况与它所属行业情况的相关性计算 
