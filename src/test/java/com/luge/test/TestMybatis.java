package com.luge.test;

import com.luge.dao.UserDao;
import com.luge.domain.QueryVo;
import com.luge.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestMybatis {
    private InputStream is;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws Exception{
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        is.close();
        session.close();
    }

    @Test
    public void test1() throws Exception{
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() throws Exception {
        User user = new User();
        user.setUsername("陆昝");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("江苏南京");
        System.out.println("保存之前" + user);
        userDao.saveUser(user);
        System.out.println("保存之后" + user);
    }

    @Test
    public void test3() throws Exception {
        User user = new User();
        user.setId(52);
        user.setUsername("luge");
        userDao.updateUser(user);
    }

    @Test
    public void test4() throws Exception {
        userDao.deleteUser(52);
    }

    @Test
    public void test5() throws Exception {
        User user = userDao.findOne(41);
        System.out.println(user);
    }

    @Test
    public void test6() throws Exception {
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test7() throws Exception {
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void tset8() throws Exception {
        User user = new User();
        user.setUsername("%王%");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userDao.findByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
