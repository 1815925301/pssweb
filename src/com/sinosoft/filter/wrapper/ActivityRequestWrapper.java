package com.sinosoft.filter.wrapper;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Package com.sinosoft.filter.wrapper
 * @ClassName: ActivityRequestWrapper
 * @Description: HttpServletRequest包装类 装饰模式
 * @author zzq
 * @Version V1.0
 * @date 2013-9-19 上午09:58:01
 */
public class ActivityRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    private Map<String, String[]> externParametersMap;
  
    public ActivityRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        if (externParametersMap.containsKey(name)) {
            String[] values = externParametersMap.get(name);
            if (values == null || values.length == 0)
                return null;
            return values[0];
        }
        return this.request.getParameter(name);
    }

    // 扩展方法;原request不存在setParameter方法;不属于J2EE应用规范
    public void setParameter(String name, String value) {
        setParameter(name, new String[] { value });
    }

    // 扩展方法;原request不存在setParameter方法;不属于J2EE应用规范
    public void setParameter(String name, String[] values) {
        if (externParametersMap == null) {
            externParametersMap = new HashMap<String, String[]>();
        }
        externParametersMap.put(name, values);
    }

   

    @Override
    public String[] getParameterValues(String name) {
        if (externParametersMap.containsKey(name))
            return externParametersMap.get(name);
        return this.request.getParameterValues(name);

    }

    @Override
    public Map<String, String[]> getParameterMap() {
        @SuppressWarnings("unchecked")
		Map<String, String[]> sourceMap = this.request.getParameterMap();
        if (sourceMap == null)
            return externParametersMap;
        if (externParametersMap != null){
        	Map<String, String[]> returnMap = new HashMap<String, String[]>(sourceMap);
        	returnMap.putAll(externParametersMap);
        	return returnMap;
        }
        return sourceMap;
    }

    /**
     * @Package com.sinosoft.filter.wrapper
     * @ClassName: ActivityEnumeration
     * @Description: 自定义的枚举对象
     * @author zzq
     * @Version V1.0
     * @date 2013-9-19 上午10:36:09
     */
    public static class ActivityEnumeration implements Enumeration<Object> {

        private Enumeration<Object> source = null;

        private List<Object> addNames = null;

        private boolean endSource = false;

        private int index = 0;

        ActivityEnumeration(Enumeration<Object> source) {
            this.source = source;
        }

        @Override
        public boolean hasMoreElements() {
            if (source.hasMoreElements())
                return true;
            endSource = true;
            if (addNames == null)
                return false;
            return addNames.size() > index ? true : false;
        }

        @Override
        public Object nextElement() {
            if (!endSource) {
                return source.nextElement();
            }
            if (addNames == null) {
                throw new NoSuchElementException();
            }
            if (addNames.size() <= index) {
                throw new NoSuchElementException();
            }
            Object result = addNames.get(index++);
            return result;

        }

        public void addNames(Object o) {
            if (o == null)
                return;
            if (addNames == null)
                addNames = new ArrayList<Object>();
            addNames.add(o);
        }
    }

}
