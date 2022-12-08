package com.zs.assignment5.repository;

import com.zs.assignment5.utils.LogObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Repository {
    /**
     * @param file Log file of developers commit
     * @param list stores the developer information in object format in list
     * @throws IOException throws exception while commit id or date or author name are incomplete
     */
    public void readFile(File file, List<LogObject> list) throws IOException, ParseException {
        Scanner sc = new Scanner(file);
        int count = 0;

        while (sc.hasNextLine()) {
            LogObject logObject = new LogObject();
            String s = sc.nextLine();
            String[] arr = s.trim().split("\\s");
            if (arr[0].equals("commit")) {
                logObject.commitId = arr[1];
            }
            s = sc.nextLine();
            String[] arr1 = s.trim().split("\\s");
            if (arr1[0].equals("Author:")) {
                logObject.authorName = arr1[1];
            }
            s = sc.nextLine();
            String[] arr2 = s.trim().split("\\s");

            if (arr2[0].equals("Date:")) {
                String newDate = arr2[5] + '-' + arr2[4] + '-' + arr2[7];
                SimpleDateFormat formatter5 = new SimpleDateFormat("dd-MMM-yyyy");
                logObject.date = formatter5.parse(newDate);
            }
            list.add(logObject);
            count++;
            if (count == 12) break;
        }
    }
}
