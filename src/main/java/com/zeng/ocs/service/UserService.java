package com.zeng.ocs.service;

import java.util.List;

import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.po.UserVo;
import com.zeng.ocs.util.PageBean;
/**
 * 用户的service
 * @author cxs
 *
 */
public interface UserService {
	public User findUserByUsername(String username);

	public PageBean<UserVo> showUserByLimit(int pageNo, int pageSize, UserVo userVo);

	public UserVo getUserByUsername(String username);

	public void updateUser(UserVo userVo);

	public void addUser(UserVo userVo);

	public void deleteUserById(Integer id);

	public void deleteUserByIds(List<Integer> ids);

	public List<CustomerVo> findAllCustomer();

	public UserVo getUserById(Integer id);

	public User login(String username, String password);

	public void updatePermissionForUser(UserVo userVo);

}
