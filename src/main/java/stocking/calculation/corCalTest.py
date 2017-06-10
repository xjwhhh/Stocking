import pandas as pd
from _datetime import datetime

from calculation.corCal import smCorCal

dates1 = [datetime(2016, 1, 1), datetime(2016, 1, 2), datetime(2016, 1, 3), datetime(2016, 1, 5)]
s1 = pd.DataFrame([20, 21, 19, 18], index=dates1)

dates2 = [datetime(2016, 1, 1), datetime(2016, 1, 2), datetime(2016, 1, 3), datetime(2016, 1, 5)]
s2 = pd.DataFrame([0.4, -0.1, 0.2, 0.2], index=dates2)

print(smCorCal(s1, s2))
