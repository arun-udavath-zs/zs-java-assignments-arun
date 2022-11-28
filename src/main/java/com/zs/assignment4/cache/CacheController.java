package com.zs.assignment4.cache;

public class CacheController {
    public void cacheImpl(){
        CacheService cache= new CacheService();
        cache.putEntryIntoCache(1,"product-1-info");
        cache.putEntryIntoCache(2,"product-2-info");
        cache.putEntryIntoCache(3,"product-3-info");
        cache.putEntryIntoCache(4,"product-4-info");
        cache.putEntryIntoCache(5,"product-5-info");

        System.out.println(cache.getFromCache(4));
        System.out.println(cache.getFromCache(5));
    }
}
