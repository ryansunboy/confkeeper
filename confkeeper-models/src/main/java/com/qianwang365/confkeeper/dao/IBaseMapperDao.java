// BaseDao
package com.qianwang365.confkeeper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.qianwang365.confkeeper.dao.mapper.IBaseMapper;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date 2013年9月23日
 * @description TODO
 * @version 1.0
 */
public interface IBaseMapperDao<E, PK> extends IBaseMapper {
	/**
	 * @description 详细说明
	 * @param e
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void add(final E e);

	/**
	 * @description 详细说明
	 * @param e
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void batchAdd(final List<E> list);

	/**
	 * @description 详细说明
	 * @param e
	 */
	void remove(@Param(value = "id") final PK e);

	void batchRemove(final PK[] ids);

	/**
	 * @description 详细说明
	 * @param e
	 */
	void update(final E e);

	/**
	 * @description 详细说明
	 * @param id
	 * @return
	 * @throws Exception
	 */
	E getEntity(@Param(value = "id") final PK id);

	/**
	 * @description 详细说明
	 * @return
	 */
	List<E> getEntities(Pagination<E> page, @Param(value = "e") final E e);
}
