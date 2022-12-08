package com.zs.assignment5.service;

import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.Date;


import static com.zs.assignment5.utils.DateConversion.dateConverter;

public class TotalCommitService {
    Logger logger = LoggerFactory.getLogger(TotalCommitService.class);
    Map<String, Integer> mapData = new HashMap<>();

    /**
     * gives information of total commits of each developer
     *
     * @param logData log data of commits
     * @throws ParseException throws exception if conversion of date fails
     */
    public void totalCommitsByDev(List<LogObject> logData) throws ParseException {
        Scanner sc = new Scanner(System.in);
        logger.info("Enter date in yyy-mm-dd format");
        String inputDate = sc.next();
        Date date = dateConverter(inputDate);

        for (LogObject object : logData) {
            if (object.date.after(date) || object.date.equals(date)) {
                if (mapData.containsKey(object.authorName)) {
                    mapData.put(object.authorName, mapData.get(object.authorName) + 1);
                } else {
                    mapData.put(object.authorName, 1);
                }
            }
        }
        for (String name : mapData.keySet()) {
            System.out.println("Total commits of " + name + " :  " + mapData.get(name));
        }
    }
}
