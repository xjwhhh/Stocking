package stocking.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2017/5/23.
 */
public class ParseDate {
    private SimpleDateFormat sdf;

    ParseDate() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    ParseDate(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    Date parse(String date) throws ParseException {
        return sdf.parse(date);
    }
}
