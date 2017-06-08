package stocking.data_impl;

import net.sf.json.JSONArray;
import stocking.data_service.Strategy_Data_Service;
import stocking.po.StockWinnerSet;
import stocking.po.StrategyPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Strategy_Data_Impl implements Strategy_Data_Service {
    Tools tools = Tools.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Cache cache=Cache.getInstance();
    Hashtable<String,String> code_name=cache.getCode_Name();

    /**
     * 回测
     * @param type
     * @param start
     * @param end
     * @param form
     * @param hold
     * @param isPla
     * @param stocks
     * @return
     */
    public StrategyPO traceBack(String type, Date start, Date end, int form, int hold, String isPla, JSONArray stocks) {
        String startDate = formatter.format(start);
        String endDate = formatter.format(end);
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\TraceBack.py"));
            String value=type+"?"+startDate+"?"+endDate+"?"+String.valueOf(form)+"?"+String.valueOf(hold)+"?"+isPla+"?"+tools.jsonArrayToString(stocks);
            commands.add(value);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String[] data = new String[6];
            for (int i = 0; i < data.length; i++) {
                String line=in.readLine();
                if(line!="nan") {
                    data[i] = line;
                }
                else{
                    data[i]="0";
                }
            }
            double annualReturn = Double.parseDouble(data[0]);//年化收益率
            double basicAnnualReturn = Double.parseDouble(data[1]);//基准年化收益率
            double alpha = Double.parseDouble(data[2]);
            double beta = Double.parseDouble(data[3]);
            double sharpeRatio = Double.parseDouble(data[4]);
            double maxDrawDown = Double.parseDouble(data[5]);//最大回撤
            int num = Integer.parseInt(in.readLine());
            Date[] dates = new Date[num];
            List<Double> profits = new ArrayList<Double>();
            List<Double> basicProfits = new ArrayList<Double>();//基准收益
            List<StockWinnerSet> sets = new ArrayList<StockWinnerSet>();//获胜股票，mom为前百分之二十，avr为持股数
            for (int i = 0; i < num; i++) {
                dates[i] = formatter.parse(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                profits.add(Double.parseDouble(in.readLine()));
            }
            for (int i = 0; i < num; i++) {
                basicProfits.add(Double.parseDouble(in.readLine()));
            }
            int seriesNum = Integer.parseInt(in.readLine());
            sets = getStockWinnerSets(in, sets, num, seriesNum);
            in.close();
            StrategyPO strategyPO = new StrategyPO(annualReturn, basicAnnualReturn, alpha, beta, sharpeRatio, maxDrawDown, dates, profits, basicProfits, sets);
            return strategyPO;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<StockWinnerSet> getStockWinnerSets(BufferedReader in, List<StockWinnerSet> stockWinnerSets, int num,int seriesNum) {
        for(int j=0;j<num;j++) {
            String line = "";
            List<String> codes = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<Double> profits = new ArrayList<Double>();
            try {
                for (int i = 0; i < seriesNum; i++) {
                    line = in.readLine();
                    String[] t = line.split("\\s+");
                    String code = t[0];
                    //TODO multiple points异常
                    Double profit = Double.parseDouble(t[1]);
                    String name = code_name.get(code);
                    codes.add(code);
                    names.add(name);
                    profits.add(profit);
                }
                StockWinnerSet stockWinnerSet = new StockWinnerSet(codes, names, profits);
                stockWinnerSets.add(stockWinnerSet);
                in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stockWinnerSets;
    }

//    public static void main(String[] args) {
//        Strategy_Data_Impl strategy_data_ = new Strategy_Data_Impl();
//        strategy_data_.traceBack("", new Date(), new Date(), 1, 1, "", new JSONArray());
//    }


}
