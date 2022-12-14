package com.zs.assignment5.service;

import com.zs.assignment5.exception.CustomException;
import com.zs.assignment5.repository.Repository;
import com.zs.assignment5.utils.LogObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileException {
    final static Logger logger = LoggerFactory.getLogger(FileException.class);

    /**
     * throws exception
     *
     * @return list of log data of developers
     */
    public List<LogObject> openFile(String filePath, List<String> authorList) {
        try {
            File file = new File(filePath);
            List<LogObject> list = new ArrayList<>();
            Repository repo = new Repository();
            repo.readFile(file, list);

            for (LogObject object : list) {
                if (object.commitId.length() != 40) {
                    logger.error("commitId is incomplete");
                    throw new CustomException("Commit id is incomplete");
                }
                if (!authorList.contains(object.authorName)) {
                    logger.error("Author name is incorrect");
                    throw new CustomException("Author name is incorrect");
                }
            }
            return list;
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
        }

        return null;
    }


}

