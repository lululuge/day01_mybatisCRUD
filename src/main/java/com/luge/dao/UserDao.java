package com.luge.dao;

import com.luge.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     */
    void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    User findOne(Integer id);

    /**
     * 根据用户名进行模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总记录数
     * @return
     */
    int findTotal();
}
