package com.sinosoft.business.dao;
import java.util.List;
import com.sinosoft.business.po.Content;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.ContentQuery;
import com.sinosoft.business.po.query.SystemConfigQuery;


public interface ContentDao {
	/**
     *查询全部数据
     */
    public List<Content> getContent();
    /**
	 * 增加某一条记录
	 */
    public Integer saveContent(Content content);
    
    /**
	 * 删除某一条记录
	 */
	public Integer removeContent(Long id);

    /**
	 * 修改方法
	 */
	public  Integer updateContent(Content content);
	/**
	 * 通过一条id查询到详情
	 */
    public Content getContentById(Long id);
    /**
     * 查询一共数据多少数量
     */
     public Integer getCountByQuery(ContentQuery contentQuery);
     
     /**
      * 分页查询不含有查询条件
      */
     public List<Content> getContentList(ContentQuery contentQuery);
     
     /**
 	 * 通过一个字段查询到列表
 	 * 
 	 */
     public Content getContentByName(String name);
     /***
      * 查询出所有的类型
      * @param contentQuery
      * @return
      */
     public List getContentType();
     /***
      * 查询出是否
      * @param contentQuery
      * @return
      */
     public List  getisrelease();
}
