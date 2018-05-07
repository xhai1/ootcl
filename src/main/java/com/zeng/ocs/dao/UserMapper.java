package com.zeng.ocs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zeng.ocs.po.User;
import com.zeng.ocs.po.UserExample;
/**
 * 逆向工程用户管理的dao层接口
 * @author cxs
 *
 */
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}