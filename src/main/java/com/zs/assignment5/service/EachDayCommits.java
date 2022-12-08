package com.zs.assignment5.service;

import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.zs.assignment5.utils.DateConversion.dateConverter;

public class EachDayCommits {
    Logger logger = LoggerFactory.getLogger(EachDayCommits.class);

    /**
     * gives details of each day commits of developers
     *
     * @param logData log data of developer commits
     * @throws ParseException exception throws if date conversion fails
     */
    public void commitsByDevEachDay(List<LogObject> logData) throws ParseException {
        Scanner sc = new Scanner(System.in);
        logger.info("Enter date in yyy-mm-dd format");
        String inputDate = sc.next();
        Date date = dateConverter(inputDate);
        HashMap<Date, Boolean> map = new HashMap<>();

        for (LogObject object : logData) {
            if (date.equals(object.date) || (date.before(object.date))) {
                if (!map.containsKey(object.date)) {
                    map.put(object.date, true);
                    CommitsOnDate(object.date, logData);
                }
            }
        }
    }

    public static void CommitsOnDate(Date date, List<LogObject> logData) {
        HashMap<String, Integer> map = new HashMap<>();

        for (LogObject object : logData) {
            if (object.date.equals(date)) {
                if (map.containsKey(object.authorName)) {
                    map.put(object.authorName, map.get(object.authorName) + 1);
                } else {
                    map.put(object.authorName, 1);
                }
            }
        }
        for (String name : map.keySet()) {
            System.out.println("commits by " + name + " on " + date + " are :" + map.get(name));
        }

    }
}
