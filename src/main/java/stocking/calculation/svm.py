'''
svm预测方案，输入当天开始的前81天(数量上为81，而非日期相隔81天)数据进行训练，并预测后一天的涨跌情况
:param
oriDf为Dataframe类型只要open,close（adjclose）,volume,high,low,index为date,前60天的数据
'''

import pandas as pd
import numpy as np
from sklearn import svm


def svmPredict(oriDf):
    train = 80
    value = pd.Series(oriDf['close'].shift(-1) - oriDf['close'], index=oriDf.index)  # 后一天减前一天的

    oriDf['high-low'] = oriDf['high'] - oriDf['low']  # Difference between High and Low
    oriDf['profit'] = (oriDf['close'].shift(-1) - oriDf['close']) / oriDf['close']  # Next Day is rise or fall
    oriDf['close-open'] = oriDf['close'] - oriDf['open']  # today's Close - Open
    oriDf['nopen-close'] = oriDf['open'].shift(-1) - oriDf['close']  # Next Day's Open-today's Close
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

    classifier = svm.SVC(kernel='poly')
    classifier.fit(data_train, value_train)
    value_predict = classifier.predict(oriDf.iloc[[train - 1]])

    return value_predict