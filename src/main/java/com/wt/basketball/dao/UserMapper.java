package com.wt.basketball.dao;

import com.wt.basketball.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {

    List<User> selectAll();

    /**
     * 根据username获取
     * @param username
     * @return
     */
    User get(@Param("username") String username);

    /**
     * 更新
     * @param user
     * @return
     */
    boolean update(User user);
}
