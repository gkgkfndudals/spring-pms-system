package hu.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.common.Field3VO;
import hu.common.UtilEtc;
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
	 * 작업 진행률 색깔 구분
	 * grey : tsstartdate > today 아직 시작안함
	 * red : tsenddate <= today 종료 일자랑 같거나 기간이 지났을때
	 * green : TIMESTAMPDIFF(DAY, tsstartdate, today) < TIMESTAMPDIFF(DAY, today, tsenddate) 작업 종료 기간이 더 많이 남았을때
	 * yellow : TIMESTAMPDIFF(DAY, tsstartdate, today) >= TIMESTAMPDIFF(DAY, today, tsenddate) 작업 종료 기간이 얼마 안 남았을때
	 * 
	 * green : tsendreal_ <= tsenddate_  설정해놓은 종료일자보다 빨리 작업이 끝났을 때
	 * red : tsendreal_ > tsenddate_    설정해놓은 종료일자보다 더 늦게 작업이 끝났을 때
	 */
	
	/**
	 * 작업 중심
	 */
	@RequestMapping(value = "/task")
	public String task(HttpServletRequest request, ModelMap modelMap) {
		return task_do(request, modelMap);
	}
	
	/**
	 * 일정중심. 
	 */
	@RequestMapping(value = "/taskCalendar")
	public String taskCalendar(HttpServletRequest request, ModelMap modelMap) {
		String ret = task_do(request, modelMap);
		
		if(ret.indexOf("Task") > -1) {
			ret += "Calendar"; // return "project/TaskCalendar"
		}
		return ret;
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
		modelMap.addAttribute("projectInfo", projectInfo);
		
		return "project/Task";
	}
	
	
	/**
	 * 작업자 중심.
	 */
	@RequestMapping(value = "/taskWorker")
	public String taskWorker(HttpServletRequest request, ModelMap modelMap) {
		String userno = request.getSession().getAttribute("userno").toString();
		String prno = request.getParameter("prno");
		
		ProjectVO projectInfo = projectSvc.selectProjectOne(prno);
		if(projectInfo == null) {
			return "common/noAuth";
		}
		
		Integer alertcount = etcSvc.selectAlertCount(userno);
		modelMap.addAttribute("alertcount", alertcount);
		
		List<?> listview = taskSvc.selectTaskWorkerList(prno);
		
		modelMap.addAttribute("listview", listview);
		modelMap.addAttribute("prno", prno);
		modelMap.addAttribute("projectInfo", projectInfo);
		
		return "project/TaskWorker";
	}
	

	/**
	 * Task 저장
	 * Task.jsp 에서 save() ajax로 처리 => TaskVO의 tsid맵핑
	 */
	@RequestMapping(value = "/taskSave")
	public void taskSave(HttpServletRequest request, HttpServletResponse response, TaskVO taskInfo) {
		taskSvc.insertTask(taskInfo);
		
		System.out.println("====================>" + taskInfo.getTsno());
		UtilEtc.responseJsonValue(response, taskInfo.getTsno());
	}
	
	/**
	 *  Task 삭제
	 */
	@RequestMapping(value = "/taskDelete")
	public void taskDelete(HttpServletRequest request, HttpServletResponse response) {
		String tsno = request.getParameter("tsno");
		taskSvc.deleteTaskOne(tsno);
		UtilEtc.responseJsonValue(response, "OK");
	}
	
	/**
	 * 일정 중심에서 작업을 클릭 했을때
	 */
	@RequestMapping(value = "/taskCalenPopup")
	public String taskCalenPopup(HttpServletRequest request, ModelMap modelMap) {
		String tsno = request.getParameter("tsno");
		TaskVO taskInfo = taskSvc.selectTaskOne(tsno);
		modelMap.addAttribute("taskInfo", taskInfo);
		
		return "project/TaskCalenPopup";
	}
	
	/**
	 * Task 복사.
	 */
	@RequestMapping(value = "/taskCopy")
	public String taskCopy(HttpServletRequest request) {
		String prno = request.getParameter("prno");
		String srcno = request.getParameter("srcno");
		
		taskSvc.taskCopy(new Field3VO(srcno, prno, null));
		
		return "redirect:/task?prno=" + prno;
	}
	
}
