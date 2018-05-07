package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.po.UserVo;
/**
 * 用户管理的dao层接口
 * @author cxs
 *
 */
public interface UserCustomerDao {

	public List<UserVo> findAllUser(UserVo userVo);

	public int findCount(UserVo userVo);

	public UserVo getUserByUsername(String username);

	public void updateUser(UserVo userVo);

	public void addUser(UserVo userVo);

	public void deleteUsers(List<Integer> ids);

	public List<CustomerVo> findAllCustomer();

	public UserVo getUserById(Integer id);

	public User login(User user);

	public void updatePermissionForUser(UserVo userVo);
}