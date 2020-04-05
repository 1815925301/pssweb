package com.sinosoft.job;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.common.util.DateTimeUtils;

/**
 * @Package com.sinosoft.job
 * @ClassName: BaseJobProcessor
 * @Description: 作业逻辑 抽象类
 * @author zzq
 * @Version V1.0
 * @date 2014-1-26 下午10:29:50
 */
public abstract class BaseJobProcessor<T> implements JobProcessor<T> {
	
	private final static Logger LOGGER =  LoggerFactory.getLogger(BaseJobProcessor.class);
	
	// 执行状态
	protected Integer executeState = 0;

	// 运行状态
	protected Boolean runStat = Boolean.TRUE;
	
	//job开始时间
	protected Timestamp operationDate;
	
	//job结束时间
	protected Timestamp completeDate;
	
	// 真实的任务数量，即查询到的任务数据量
	protected AtomicInteger realTaskCount = new AtomicInteger();
	
	protected Integer realTaskSize = 0;
	
	// 执行成功任务数量
	protected AtomicInteger successTaskCount = new AtomicInteger();

	// 本次作业完成任务数量
	protected AtomicInteger completeTaskCount = new AtomicInteger();
	
	/**
	 * 查询待处理数据的列表
	 * @return List<T>
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:33:02
	 * @version V1.0
	 */
	public abstract List<T> queryTaskData();

	/**
	 * 处理待处理数据
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:33:11
	 * @version V1.0
	 */
	public abstract void processTask(T obj);

	/**
	 * JOb数据发生异常时处理 
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:33:23
	 * @version V1.0
	 */
	public abstract void queryTaskDataError(Exception e);
	
	/**
	 * 处理数据时发生异常时的处理
	 * @param obj
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:33:36
	 * @version V1.0
	 */
	public abstract void processErrorTask(T obj);
	
	/**
	 * 任务处理完成执行的操作
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:34:11
	 * @version V1.0
	 */
	public abstract void completeTaskProcess();

	@Override
	public void execute() {
		// 用于控制作业的启停
		if (!this.runStat) {
			return;
		}
		LOGGER.debug("开始执行作业");
		executeJob();
	}

	@Override
	public void executeJob() {
		//记录任务开始时间
		operationDate = DateTimeUtils.getCurrentTime();
		
		// 批量执行作业
		List<T> list = null;
		
		try {
			list = queryTaskData();	
		} catch (Exception e) {
			//查询数据出现异常时执行
			queryTaskDataError(e);
		}
		
		realTaskSize = list == null ? 0 : list.size();
		if (list != null && list.size() != 0){
			realTaskCount.addAndGet(list.size());
			successTaskCount.addAndGet(list.size());
			concurrentProcess(list);
		}
		//记录任务结束时间
		completeDate = DateTimeUtils.getCurrentTime();
		completeTaskProcess();
	}
	
	/**
	 * 按顺序执行任务
	 * @param list
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-1-26 下午10:40:39
	 * @version V1.0
	 */
	private void concurrentProcess(List<T> list) {
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			try {
				processTask(t);
			} catch (Exception e) {
				LOGGER.error("处理任务出错:",e);
				successTaskCount.getAndDecrement();
				e.printStackTrace();
				try {
					processErrorTask(t);
				} catch (Exception e1) {
					LOGGER.error("处理错误任务出错:",e1);
					e1.printStackTrace();
					this.completeTaskCount.getAndDecrement();
					this.realTaskCount.getAndDecrement();
				}
			} finally {
				this.completeTaskCount.getAndIncrement();
			}
		}
	}

	@Override
	public String executeState() {
		switch(this.executeState){
		case 0: 
			return "未知";
		case 1:
			return "正常";
		default:
			return "未知";
		}
	}

	@Override
	public Boolean runState() {
		return this.runStat;
	}

	@Override
	public void startup() {
		this.runStat = Boolean.TRUE;
	}

	@Override
	public void shutdown() {
		this.runStat = Boolean.FALSE;
	}

}
