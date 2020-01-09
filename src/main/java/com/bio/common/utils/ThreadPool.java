package com.bio.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *ThreadPool 工具类
 *
 * 
 * @author chenx
 *
 *
 *
 */
public class ThreadPool
{
    public final ExecutorService executorService = Executors
            .newCachedThreadPool();
    
    private static ThreadPool instance;
    
    public synchronized static ThreadPool getThreadPool() {
    		if(instance  == null ) {
    			instance = new ThreadPool();
    		}
    	     return instance;
    	
    }

    public void destory()
    {
    		this.instance.executorService.shutdownNow();
    }
    
    public  void addRunnable(java.lang.Runnable command)
    {
    		this.instance.executorService.execute(command);
    }
    
    public static class Runnable implements java.lang.Runnable
    {
    	Map<String, Object> params=new HashMap<String, Object>();
    	public Runnable set(String key,Object value)
    	{
    		this.params.put(key, value);
    		return this;
    	}
    	
    	public Object get(String key)
    	{
    		return this.params.get(key);
    	}
    	
		@Override
		public void run()
		{
		}
    	
    }
}
