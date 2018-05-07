package com.zeng.ocs.service;

import java.util.List;

import com.zeng.ocs.po.Para;

public interface SysParaService {
	
	
	public void updateSystemParam(List<Para> paras);
	
	public List<Para> showSystemParam();
}
