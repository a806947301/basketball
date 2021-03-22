package com.wt.basketball.service.impl;

import com.wt.basketball.common.AppException;
import com.wt.basketball.dao.UserMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.FriendVo;
import com.wt.basketball.model.vo.FriendYueballDto;
import com.wt.basketball.service.UserService;
import com.wt.basketball.service.YueballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private YueballService yueballService;

    @Override
    public User get(String username) {
        return userMapper.get(username);
    }

    @Override
    public User login(String username, String password) {
        User user = get(username);
        if (null != user && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        User existUser = userMapper.get(user.getUsername());
        if (null != existUser) {
            throw new AppException("该用户名已存在");
        }

        user.setIsadmin(0);
        return userMapper.add(user);
    }

    @Override
    public boolean add(User user) {
        return userMapper.add(user);
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean delete(String username) {
        return userMapper.delete(username);
    }

    @Override
    public List<User> search(Integer ballid) {
        return userMapper.selectByBall(ballid);
    }

    @Override
    public List<User> search() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> myFriend(String username) {
        List<FriendYueballDto> dtos = yueballService.selectByUser(username);

        HashMap<String, FriendVo> users = new HashMap<>();
        for (FriendYueballDto dto : dtos) {

            List<User> friends = search(dto.getId());

            friends.stream().forEach(friend ->
                    {
                        FriendVo exist = users.get(friend.getUsername());
                        if (null == exist) {
                            users.put(friend.getUsername(),
                                    new FriendVo(friend, dto));
                        } else {
                            exist.addBall(dto);
                        }

                    }
            );
        }

        users.remove(username);
        return new ArrayList<>(users.values());
    }
}
