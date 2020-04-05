package com.sinosoft.common.cache;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sinosoft.common.constant.MappingConstantConfig;


public class CacheOperation {
	private static CacheOperation singleton = null;

    private Hashtable cacheMap;//存放缓存数据
    private ArrayList threadKeys;//处于线程更新中的key值列表

    public static CacheOperation getInstance() {
        if (singleton == null) {
            singleton = new CacheOperation();
        }
        return singleton;
    }
    private CacheOperation() {
        cacheMap = new Hashtable();
        threadKeys = new ArrayList();
    }

    /** *//**
     * 添加数据缓存
     * 与方法getCacheData(String key, long intervalTime, int maxVisitCount)配合使用
     * @param key
     * @param data
     */
    public void addCacheData(String key, Object data) {
        addCacheData(key, data, true);
    }

    public void addCacheData(String key, Object data, boolean check){
        if(check){
        	if(!MappingConstantConfig.getValue("UseCache").toLowerCase().equals("true"))return;  //如果不启动CACHE，则不增加CACHE。
        }
        if (Runtime.getRuntime().freeMemory() < 2L*1024L*1024L) {//虚拟机内存小于5兆，则清除缓存
            System.out.println("WEB缓存：内存不足，开始清空缓存！如果您频繁看到本信息，表示您分配给JAVA的内存不足，应当适当增加。");
            System.out.println("空闲内存：" + Runtime.getRuntime().freeMemory());
            removeAllCacheData();
            return;
        }
        cacheMap.put(key, new CacheData(data));
    }

    /** *//**
     * 取得缓存中的数据
     * 与方法addCacheData(String key, Object data)配合使用
     * @param key
     * @param intervalTime 缓存的时间周期，小于等于0时不限制
     * @param maxVisitCount 访问累积次数，小于等于0时不限制
     * @return
     */
    public Object getCacheData(String key, long intervalTime, int maxVisitCount) {
        CacheData cacheData = (CacheData)cacheMap.get(key);
        if (cacheData == null){
            return null;
        }
        if (intervalTime > 0 && (System.currentTimeMillis() - cacheData.getTime()) > intervalTime) {
            removeCacheData(key);
            return null;
        }
        if (maxVisitCount > 0 && (maxVisitCount - cacheData.getCount()) <= 0){
            removeCacheData(key);
            return null;
        } else {
            cacheData.addCount();
        }         
        return cacheData.getData();
    }
    /** *//**
     * 移除缓存中的数据
     * @param key
     */
    public void removeCacheData(String key){    	
        cacheMap.remove(key);        
    }
    /** *//**
    * 移除所有缓存中的数据
    *
    */
   public void removeAllCacheData(){
       cacheMap.clear();

   }
   public String toString(){
       StringBuffer sb = new StringBuffer("************************ ");
       sb.append("当前缓存大小：").append(cacheMap.size()).append(" ");
       sb.append("************************");
       return sb.toString();
    }

}