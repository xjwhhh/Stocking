import pymysql
import sys



if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "123456", "stock", charset="utf8")
    cursor = db.cursor();
    paths = sys.argv[0].split("/")
    newPath = ""
    for i in range(0, len(paths) - 2):
        newPath += (paths[i] + "\\")
    newPath += "calculation"
    sys.path.append(newPath)
    sys.path.append(
        "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation")

    strategyType = sys.argv[1]  # 策略类型
    startDate = sys.argv[2]  # 开始日期
    endDate = sys.argv[3]  # 结束日期
    isHold = sys.argv[4]
    interval = sys.argv[5]
    isPla = sys.argv[6]  # 是否为板块
    stocks = sys.argv[7]
    stockLists = stocks.split("/")  # 股票代码列表
