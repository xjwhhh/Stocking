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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Strategy_Data_Impl implements Strategy_Data_Service {
    Tools tools = new Tools();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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
//            commands.add(type);//策略类型
//            commands.add(startDate);//开始日期
//            commands.add(endDate);//结束日期
//            commands.add(String.valueOf(form));//形成期
//            commands.add(String.valueOf(hold));//持有期
//            commands.add(isPla);//是否为板块
//            commands.add(tools.jsonArrayToString(stocks));//股票列表转成的string
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
                System.out.print(data[i]);
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
            sets = tools.getStockWinnerSets(in, sets, num, seriesNum);
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

//    public static void main(String[] args) {
//        Strategy_Data_Impl strategy_data_ = new Strategy_Data_Impl();
//        strategy_data_.traceBack("", new Date(), new Date(), 1, 1, "", new JSONArray());
//    }


}
