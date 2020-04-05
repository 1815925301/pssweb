package com.sinosoft.job;

/**
 * @Package com.sinosoft.job
 * @ClassName: JobProcessor
 * @Description: 系统作业 接口定义
 * @author zzq
 * @Version V1.0
 * @date 2014-1-26 下午10:26:04
 */
public interface JobProcessor<T> {
	
	/**
	 * 作业执行方法
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:26:41
	 * @version V1.0
	 */
	public void execute();
	
	/**
	 * 作业执行方法
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:26:50
	 * @version V1.0
	 */
	public void executeJob();
	
	/**
	 * 作业执行状态  
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:27:01
	 * @version V1.0
	 */
	public String executeState();
	
	/**
	 * 作业运行状态  
	 * @return Boolean
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:27:10
	 * @version V1.0
	 */
	public Boolean runState();
	
	/**
	 * 启动作业
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:28:20
	 * @version V1.0
	 */
	public void startup();
	
	/**
	 * 停止作业，不会停止作业中执行的任务
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:28:34
	 * @version V1.0
	 */
	public void shutdown();
	
}
