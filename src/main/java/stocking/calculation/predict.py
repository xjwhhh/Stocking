'''
svm预测方案，输入当天开始的前82天(数量上为82，而非日期相隔82天)数据进行训练，并预测后一天的股票上涨的概率
:param
oriDf为Dataframe类型只要open,close（adjclose）,volume,high,low,index为date,前60天的数据
'''

import pandas as pd
from sklearn import ensemble
from sklearn import naive_bayes
from sklearn import svm


def svmPredict(oriDf):
    train = 80
    value = pd.Series(oriDf['close'].shift(-1) - oriDf['close'], index=oriDf.index)  # 后一天减前一天的
    oriDf['high-low'] = oriDf['high'] - oriDf['low']  # Difference between High and Low
    oriDf['profit'] = (oriDf['close'] - oriDf['close'].shift(1)) / oriDf['close'].shift(1)  # 今日的收益率
    oriDf['close-open'] = oriDf['close'] - oriDf['open']  # today's Close - Open
    value[value >= 0] = 1  # 0 means rise
    value[value < 0] = 0  # 1 means fall
    oriDf = oriDf.dropna(how='any')
    value = value[~value.isnull()]

    del (oriDf['open'])
    del (oriDf['close'])
    del (oriDf['high'])
    del (oriDf['low'])

    data_train = oriDf[0:train - 1]
    value_train = value[0:train - 1]
    # svm
    classifier = svm.SVC(kernel='poly')
    classifier.fit(data_train, value_train)
    value_predict1 = classifier.predict(oriDf.iloc[[train - 1]])
    # naive_bayes
    bayes = naive_bayes.GaussianNB()
    bayes.fit(data_train, value_train)
    value_predict2 = bayes.predict(oriDf.iloc[[train - 1]])
    # forest
    forest = ensemble.RandomForestClassifier(n_estimators=10)
    forest.fit(data_train, value_train)
    value_predict3 = forest.predict(oriDf.iloc[[train - 1]])
    return 0.1 * int(value_predict1) + 0.2 * int(value_predict2) + 0.7 * int(value_predict3)
