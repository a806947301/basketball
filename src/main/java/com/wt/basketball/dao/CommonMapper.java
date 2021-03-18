package com.wt.basketball.dao;

import com.wt.basketball.model.Common;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Repository
public interface CommonMapper {
    boolean add(Common common);

    /**
     * 查询
     * @param cwho
     * @return
     */
    List<Common> selectAll(@Param("cwho") Integer cwho);

    /**
     * 查一个
     * @param id
     * @return
     */
    Common get(@Param("id") int id);

    /**
     * 更新
     * @param article
     * @return
     */
    boolean update(Common article);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(@Param("id") int id);
}
