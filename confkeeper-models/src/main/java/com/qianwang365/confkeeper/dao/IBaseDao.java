// BaseDao
package com.qianwang365.confkeeper.dao;

import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date 2013年9月23日
 * @description TODO
 * @version 1.0
 */
public interface IBaseDao<E, PK> {
	/**
	 * @description 详细说明
	 * @param e
	 */
	void add(final E e) throws Exception;

	/**
	 * @description 详细说明
	 * @param e
	 */
	void remove(final E e) throws Exception;

	/**
	 * @description 详细说明
	 * @param e
	 */
	void update(final E e) throws Exception;

	/**
	 * @description 详细说明
	 * @return
	 */
	Pagination<E> getEntities(Pagination<E> page) throws Exception;

	/**
	 * @description 详细说明
	 * @param id
	 * @return
	 * @throws Exception
	 */
	E getEntity(final PK id) throws Exception;
}
