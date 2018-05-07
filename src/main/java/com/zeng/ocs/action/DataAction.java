package com.zeng.ocs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.service.DataService;

/**
 * @date:2017年12月17日 上午12:41:40
 * @author Jianghai Yang
 * @version :
 */
@Controller
@RequestMapping("/db")
public class DataAction
{
	@Autowired
	private DataService dataService;
	
	@RequestMapping("/backup")
	public ModelAndView backup()
	{
		ModelAndView mv = new ModelAndView("/jsp/databackupJsp.action");
		mv.addObject("back",dataService.backup());
		return mv;
	}

	@RequestMapping("/recovery")
	public ModelAndView recovery()
	{
		ModelAndView mv = new ModelAndView("/jsp/databackupJsp.action");
		mv.addObject("rec",dataService.recorvery());
		return mv;
	}
	@RequestMapping("/download")
	public void download(HttpServletResponse response) throws IOException
	{
		dataService.download(response);
	}
}
