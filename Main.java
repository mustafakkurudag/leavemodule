package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * https://www.baeldung.com/java-read-json-from-url
 */

public class Main {
    public static void main(String args[]) throws MalformedURLException {
        Date date = new Date();
        System.out.println(isWeekend(date));
        //readUrl();

        URL holidays = new URL("https://api.ubilisim.com/resmitatiller/");
        stream(holidays);

    }

    public static String isWeekend(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String dayNames[] = new DateFormatSymbols().getWeekdays();

        String day = dayNames[c.get(Calendar.DAY_OF_WEEK)];
        System.out.println("bugün: " + day);

        System.out.println(date.getDay() + ", " + date.getMonth());

        if(day.equals("Cumartesi") || day.equals("Pazar")) {
            return "Evet tatil";
        } else {
            return "Hayır haftasonu değil";
        }
    }

    public static String getJson(URL url) {
        String json = IOUtils.toString(url, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }

    public static String stream(URL url) {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readUrl() {
        try {
            URL holidays = new URL("https://api.ubilisim.com/resmitatiller/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(holidays.openStream())
            );

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
