package com.zs.assignment5.repository;

import com.zs.assignment5.service.FileProgram;
import com.zs.assignment5.utils.LogObject;
import com.zs.assignment5.utils.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Repository {
    /**
     *
     * @param file Log file of developers commit
     * @param list stores the developer information in object format in list
     * @throws IOException throws exception while commit id or date or author name are incomplete
     */
    public void readFile(File file, List<LogObject> list) throws IOException {
        Scanner bf = new Scanner(file);
        int count = 0;

        while (bf.hasNextLine()) {
            LogObject logObject = new LogObject();
            String s = bf.nextLine();
            String[] arr = s.trim().split("\\s");
            if (arr[0].equals("commit")) {
                logObject.commitId = arr[1];
            }
            s = bf.nextLine();
            String[] arr1 = s.trim().split("\\s");
            if (arr1[0].equals("Author:")) {
                logObject.authorName = arr1[1];
            }
            s = bf.nextLine();
            String[] arr2 = s.trim().split("\\s");
            if (arr2[0].equals("Date:")) {
                String date = arr2[5] + "-" + arr2[4] + "-" + arr2[7];
                logObject.date = date;
            }
            if (FileProgram.map.containsKey(logObject.authorName)) {
                List<Pair> obj = FileProgram.map.get(logObject.authorName);
                boolean flag = false;

                for (int i = 0; i < obj.size(); i++) {
                    if (obj.get(i).date.equals(logObject.date)) {
                        obj.get(i).todayCommits++;
                        obj.get(i).tillTodayCommits++;
                        FileProgram.map.put(logObject.authorName, obj);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    int prevCommit = obj.get(obj.size() - 1).tillTodayCommits;
                    Pair pair = new Pair(logObject.date, 1, prevCommit + 1);
                    obj.add(pair);
                    FileProgram.map.put(logObject.authorName, obj);
                }
            } else {
                List<Pair> list1 = Arrays.asList(new Pair(logObject.date, 1, 1));
                List<Pair> l = new ArrayList<>(list1);
                FileProgram.map.put(logObject.authorName, l);
            }

            list.add(logObject);
            count++;
            if (count == 12) break;
        }
    }
}
