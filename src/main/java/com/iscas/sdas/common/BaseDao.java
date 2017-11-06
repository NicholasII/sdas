package com.iscas.sdas.common;

import java.util.List;
/**
 * 通用DAO
 * @author dongqun
 * 2017年10月23日下午4:29:00
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * 查询
	 * @return
	 */
	public abstract List<T> getPageList(T t);
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public abstract int update(T t);
	/**
	 * 删除
	 * @param t
	 * @return
	 */
	public abstract int delete(T t);
	/**
	 * 插入
	 * @param t
	 * @return
	 */
	public abstract int insert(T t);
}
