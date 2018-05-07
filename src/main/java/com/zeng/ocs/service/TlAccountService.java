package com.zeng.ocs.service;


import java.util.List;

import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.util.PaginationUtil;

public interface TlAccountService {

	public List<ComputeMoney> getMonthTotal(PaginationUtil paginationUtil); 
}
