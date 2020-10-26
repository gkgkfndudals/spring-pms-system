package hu.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.etc.EtcSvc;

@Controller
public class TaskCtr {

	@Autowired
	private ProjectSvc projectSvc;
	
	@Autowired
	private TaskSvc taskSvc;
	
	@Autowired
	private EtcSvc etcSvc;
	
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectCtr.class);
	
	
	/**
	 * 작업 중심
	 */
	@RequestMapping(value = "/task")
	public String task(HttpServletRequest request, ModelMap modelMap) {
		return task_do(request, modelMap);
	}
	
	public String task_do(HttpServletRequest request, ModelMap modelMap) {
		String userno = request.getSession().getAttribute("userno").toString();
		String prno = request.getParameter("prno");
		
		ProjectVO projectInfo = projectSvc.selectProjectOne(prno);
		if(projectInfo == null) {
			return "common/noAuth";
		}
		
		Integer alertcount = etcSvc.selectAlertCount(userno);
		modelMap.addAttribute("alertcount", alertcount);
		
		List<?> listview = taskSvc.selectTaskList(prno);
		
		modelMap.addAttribute("listview", listview);
		modelMap.addAttribute("prno", prno);
		
		return "project/Task";
	}
	
}