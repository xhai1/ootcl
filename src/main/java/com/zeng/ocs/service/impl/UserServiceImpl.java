package com.zeng.ocs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.UserCustomerDao;
import com.zeng.ocs.dao.UserMapper;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.po.UserExample;
import com.zeng.ocs.po.UserVo;
import com.zeng.ocs.service.UserService;
import com.zeng.ocs.util.MD5;
import com.zeng.ocs.util.PageBean;
/**
 * 用户的service实现类
 * @author cxs
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserCustomerDao userCustomerDao;
	
	@Override
	public User findUserByUsername(String username) {
		UserExample userExample=new UserExample();
		UserExample.Criteria criteria=userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<User> userList=userMapper.selectByExample(userExample);
		if(userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public PageBean<UserVo> showUserByLimit(int pageNo, int pageSize,UserVo userVo) {
		
		PageBean<UserVo> pageBean = new PageBean<UserVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userCustomerDao.findCount(userVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		PageHelper.startPage(pageNo, pageSize);
		
		List<UserVo> list=userCustomerDao.findAllUser(userVo);
		
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public UserVo getUserByUsername(String username) {
		return userCustomerDao.getUserByUsername(username);
	}

	@Override
	public void updateUser(UserVo userVo) {
		userVo.setPassword(new MD5().getMD5ofStr(userVo.getPassword()));
		userCustomerDao.updateUser(userVo);
	}

	@Override
	public void addUser(UserVo userVo) {
		userVo.setPassword(new MD5().getMD5ofStr(userVo.getPassword()));
		userCustomerDao.addUser(userVo);
	}

	@Override
	public void deleteUserById(Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteUserByIds(List<Integer> ids) {
		userCustomerDao.deleteUsers(ids);
	}

	@Override
	public List<CustomerVo> findAllCustomer() {
		return userCustomerDao.findAllCustomer();
	}

	@Override
	public UserVo getUserById(Integer id) {
		return userCustomerDao.getUserById(id);
	}

	@Override
	public User login(String username, String password) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(new MD5().getMD5ofStr(password));
		return userCustomerDao.login(user);
	}

	@Override
	public void updatePermissionForUser(UserVo userVo) {
		userCustomerDao.updatePermissionForUser(userVo);
	}

	
}
