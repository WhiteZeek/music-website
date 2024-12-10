package com.dao;
//实现对用户进行修改的接口
import com.domain.consumer;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface consumerMapper {
    public int insert(consumer c);//增加

    public int update(consumer c);//修改

    public int delete(Integer id);//删除

    public consumer selectByPrimaryKey(Integer id);//根据id查找

    public List<consumer> allConsumer();//查询所有用户

    public int verifyPassword(String username, String password);//验证密码

    public consumer getByUsername(String username);//根据username查找
}