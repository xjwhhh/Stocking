package stocking.servlet;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2017/5/22.
 */
class ToJSON {
    private StringBuffer json;

    ToJSON() {
        json = new StringBuffer();
    }

    JSONObject toJSONObject(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        String line;
        try {
            BufferedReader br = request.getReader();
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        System.out.print(json.toString());
        return JSONObject.fromObject(json.toString());
    }
}
