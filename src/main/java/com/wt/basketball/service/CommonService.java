package com.wt.basketball.service;

import com.wt.basketball.model.Common;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.CommonVo;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
public interface CommonService {
    /**
     * 文章的评论
     * @param articleId
     * @return
     */
    List<CommonVo> articleCommon(Integer articleId);

    /**
     * 写评论
     *
     * @param common
     * @return
     */
    boolean writeCommon(Common common);

    /**
     * 删除评论
     * @param id
     * @return
     */
    boolean deleteCommon(int id);

    /**
     * 点赞
     * @param id
     * @return
     */
    boolean addGood(int id, User currentUser);

    /**
     * 喜欢
     *
     * @param id
     * @return
     */
    boolean addLike(int id, User currentUser);

    /**
     * 不喜欢
     * @param id
     * @return
     */
    boolean addUnlike(int id, User currentUser);
}
