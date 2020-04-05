package com.sinosoft.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ThreadContainer
 * @Description: 每个线程对系统的调用都会生成序列号，该序列号保存到了当前线程的ThreadLocalMap中，而ThreadLocalMap需要用ThreadLocal获取
 * @author zzq
 * @Version V1.0
 * @date 2013-9-10 下午08:42:38
 */
public class ThreadContainer {
	
	private static Map<String, ThreadLocal<String>> threadParams = new ConcurrentHashMap<String, ThreadLocal<String>>(0);
	
	public static ThreadLocal<String> getMyThreadLocal(String index) {
        return threadParams.get(index);
    }
	
	public static boolean addMyThreadLocal(String index, ThreadLocal<String> threadLocal) {
        if (threadLocal == null)
            return false;
        if (getMyThreadLocal(index) != null) {
            if (threadLocal.equals(getMyThreadLocal(index)))
                return true;
            else
                return false;
        }
        threadParams.put(index, threadLocal);
        return true;
    }
	
	public static Map<String, Object> getAllThreadLocalSnapshot() {
        if (threadParams == null)
            return null;
        Map<String,Object> snapshot = new HashMap<String,Object>();
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            if (threadParams.get(key) != null && threadParams.get(key).get() != null)
                snapshot.put(key, threadParams.get(key).get());
        }
        return snapshot;
    }
	
	public static void cleanAllThreadLocal() {
        if (threadParams == null)
            return;
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            cleanThreadLocal(key);
        }
    }
	
	public static void cleanThreadLocal(String index) {
        ThreadLocal<String> local = getMyThreadLocal(index);
        if (local == null)
            return;
        local.remove();
    }
	
	public static void destoryAllThreadLocal() {
        if (threadParams == null)
            return;
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            destoryThreadLocal(key);
        }
        threadParams = null;
    }
	
	public static void destoryThreadLocal(String index) {
        ThreadLocal<String> local = getMyThreadLocal(index);
        if (local != null && threadParams != null)
            threadParams.remove(index);

    }

}
