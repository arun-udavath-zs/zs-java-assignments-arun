package com.zs.assignment4.cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class CacheEntry{
    int key;
    String value;
    CacheEntry(int key,String value){
        this.key = key;
        this.value = value;
    }
}
public class CacheService {
        Deque<CacheEntry> q = new LinkedList<>();
        Map<Integer, CacheEntry> map = new HashMap<>();
        int CACHE_SIZE=3;

        public String getFromCache(int key){
            if (map.containsKey(key)){
                CacheEntry entry = map.get(key);
                q.remove(entry);
                q.addFirst(entry);
                return entry.value;
            }
            return null;
        }
        public void putEntryIntoCache(int key,String value){
            if(map.containsKey(key)){
                CacheEntry entry = map.get(key);
                q.remove(entry);
            }
            else {
                if (q.size() == CACHE_SIZE){
                    CacheEntry removeEle = q.removeLast();
                    map.remove(removeEle.key);
                }
            }
            CacheEntry newEntry = new CacheEntry(key, value);
            q.addFirst(newEntry);
            map.put(key,newEntry);
        }
    }

