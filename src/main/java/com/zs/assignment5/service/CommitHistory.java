package com.zs.assignment5.service;

import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

import static com.zs.assignment5.utils.DateConversion.dateConverter;

public class CommitHistory {
    Logger logger = LoggerFactory.getLogger(CommitHistory.class);

    /**
     * shows developer commit history
     * @param logData log data of commits
     * @throws ParseException throws exception if conversion of date fails
     */
    public void commitDetails(List<LogObject> logData) throws ParseException {
        Scanner sc = new Scanner(System.in);
        logger.info("Enter date");
        String inputDate = sc.next();
        Date date = dateConverter(inputDate);
        Map<String, Date> mapData = new HashMap<>();
        List<String> user = new ArrayList<>();

        for (LogObject object : logData) {
            if (object.date.before(date)) {
                break;
            } else {
                if (mapData.containsKey(object.authorName)) {
                    long timeDiff = object.date.getTime() - date.getTime();
                    long diff = (timeDiff / (1000 * 60 * 60 * 24)) % 365;
                    if (diff > 2) {
                        user.add(object.authorName);
                    }
                } else {
                    mapData.put(object.authorName, date);
                }
            }
        }
        if (user.size() == 0)
            logger.info("No author found");
        for (int i = 0; i < user.size(); i++) {
            logger.info("Author Name: " + user.get(i));
        }
    }

}
