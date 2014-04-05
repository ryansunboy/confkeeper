// IBaseService
package com.qianwang365.confkeeper.service;

import java.util.List;

import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date 2013年9月23日
 * @description TODO
 * @version 1.0
 */
public interface IBaseService<E, PK> {
	/**
	 * @description 详细说明
	 * @param e
	 */
	void add(final E e);

	void batchAdd(final List<E> e);

	/**
	 * @description 详细说明
	 * @param e
	 */
	void remove(final PK id);

	void batchRemove(final PK[] ids);

	/**
	 * @description 详细说明
	 * @param e
	 */
	void update(final E e);

	/**
	 * @param idx
	 * @param size
	 * @param orderField
	 * @param orderMode
	 * @return
	 */
	Pagination<E> getEntities(final int idx, final int size, final E e);

<<<<<<< HEAD
	Pagination<E> getEntities(final E e);

=======
>>>>>>> 0ed04841feb04ddb0dfb8f44d2a00f68437ce55b
	/**
	 * @description 详细说明
	 * @param id
	 * @return
	 * @throws Exception
	 */
	E getEntity(final PK id);

}
