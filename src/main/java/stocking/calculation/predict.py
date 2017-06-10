'''
svm预测方案，输入当天开始的前82天(数量上为82，而非日期相隔82天)数据进行训练，并预测后一天的股票上涨的概率
:param
oriDf为Dataframe类型只要open,close（adjclose）,volume,high,low,index为date,前60天的数据
'''

import pandas as pd
import numpy as np
from sklearn import svm
from sklearn import naive_bayes
from sklearn import ensemble


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

    # correct1 = 0
    # correct2 = 0
    # correct3 = 0
    # train_original = train
    # i = 0
    # L = len(oriDf)
    # while train < L:
    #     data_train = oriDf[train - train_original:train]
    #     value_train = value[train - train_original:train]
    #     data_predict = oriDf[train:train + 1]
    #     value_real = value[train:train + 1]
    #     # print(Data_train)
    #     # print(value_train)
    #     forest = ensemble.RandomForestClassifier(n_estimators=10)
    #     forest.fit(data_train,value_train)
    #     value_predict3=forest.predict(data_predict)
    #     classifier = svm.SVC(kernel='poly')
    #     classifier.fit(data_train, value_train)
    #     value_predict1 = classifier.predict(data_predict)
    #     bayes = naive_bayes.GaussianNB()
    #     bayes.fit(data_train,value_train)
    #     value_predict2=bayes.predict(data_predict)
    #     # print("value_real = ",value_real[0])
    #     # print("value_predict = ",value_predict)
    #     if (value_real[0] == int(value_predict3)):
    #         correct3 = correct3 + 1
    #     if(value_real[0] == int(value_predict2)):
    #         correct2=correct2+1
    #     if(value_real[0] == int(value_predict1)):
    #         correct1=correct1+1
    #     train = train + 1
    # print("Correct1 = ", correct1)
    # print("Correct2 = ", correct2)
    # print("Correct3 = ",correct3)
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
