package com.zeng.ocs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.po.Para;
import com.zeng.ocs.service.SysParaService;

@Controller
@RequestMapping("/syspParam")
public class SysParamAction {
	@Autowired
	SysParaService sysParaService;
	/**
	 * 更新控制器
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateSysParam")
	public ModelAndView updateSysParam(HttpServletRequest request){
		List<Para> paras = getParaList( request);
		
		sysParaService.updateSystemParam(paras);
		
		ModelAndView mv  = new ModelAndView("/WEB-INF/jsp/sysparam.jsp");
		
		for(Para para : paras){
			mv.addObject(""+para.getPkey()+"", para.getPvalue());
		}
		
		mv.addObject("flag", 1);
		return mv;
		
	}
	
	public List<Para> getParaList(HttpServletRequest request){
		
		String companyname  = request.getParameter("companyname");
		String comacount = request.getParameter("comacount");
		String compopacbank = request.getParameter("compopacbank");
		String compaddress = request.getParameter("compaddress");
		String asynctime = request.getParameter("asynctime");
		String asyncinterface = request.getParameter("asyncinterface");
		String errurl  = request.getParameter("errurl");
		String chargeman  = request.getParameter("chargeman");
		List<Para> paras = new ArrayList<Para>();
		
		Para para = new Para();
		para.setPkey("companyname");
		para.setPvalue(companyname);
		paras.add(para);
		
		 para = new Para();
		para.setPkey("comacount");
		para.setPvalue(comacount);
		paras.add(para);
		
		 para = new Para();
		para.setPkey("compopacbank");
		para.setPvalue(compopacbank);
		paras.add(para);
		
		 para = new Para();
		para.setPkey("compaddress");
		para.setPvalue(compaddress);
		paras.add(para);
		
		 para = new Para();
		para.setPkey("asynctime");
		para.setPvalue(asynctime);
		paras.add(para);
		
		 para = new Para();
		para.setPkey("asyncinterface");
		para.setPvalue(asyncinterface);
		paras.add(para);
		
		para = new Para();
		para.setPkey("errurl");
		para.setPvalue(errurl);
		paras.add(para);
		
		para = new Para();
		para.setPkey("chargeman");
		para.setPvalue(chargeman);
		paras.add(para);
		
		return paras;
	}
	
	
	@RequestMapping("/showSysParam")
	public ModelAndView showSysParam(){
		List<Para> paras = sysParaService.showSystemParam();
		
		ModelAndView mv  = new ModelAndView("/WEB-INF/jsp/sysparam.jsp");
		
		for(Para para : paras){
			mv.addObject(""+para.getPkey()+"", para.getPvalue());
		}
		
		return mv;
		
	}
	
}
