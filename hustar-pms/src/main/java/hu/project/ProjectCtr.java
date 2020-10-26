package hu.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.common.SearchVO;
import hu.common.Util4calen;
import hu.etc.EtcSvc;

@Controller
public class ProjectCtr {

	@Autowired
	private ProjectSvc projectSvc;
	
	@Autowired
	private EtcSvc etcSvc;
	
	/**
	 * �봽濡쒖젥�듃 由ъ뒪�듃
	 */
	@RequestMapping(value = "/projectList")
	public String projectList(HttpServletRequest request, SearchVO searchVO, ModelMap modelMap) {
		String userno = request.getSession().getAttribute("userno").toString();
		
		Integer alertcount = etcSvc.selectAlertCount(userno);
		modelMap.addAttribute("alertcount", alertcount);
		
		searchVO.pageCalculate(projectSvc.selectProjectCount(searchVO) ); //startRow, endRow
		List<?> listview = projectSvc.selectProjectList(searchVO);
		
		modelMap.addAttribute("searchVO", searchVO);
		modelMap.addAttribute("listview", listview);
		
		return "project/ProjectList";
	}
	
	
	/**
	 * �벐湲�
	 */
	@RequestMapping(value = "/projectForm")
	public String projectForm(HttpServletRequest request, ModelMap modelMap) {
		String userno = request.getSession().getAttribute("userno").toString();
		
		Integer alertcount = etcSvc.selectAlertCount(userno);
		modelMap.addAttribute("alertcount", alertcount);
		
		String prno = request.getParameter("prno");
		
		ProjectVO projectInfo;
		
		if(prno != null) {
			projectInfo = projectSvc.selectProjectOne(prno);
		} else {
			String today = Util4calen.date2Str(Util4calen.getToday());
			projectInfo = new ProjectVO();
			projectInfo.setPrstartdate(today);
			projectInfo.setPrenddate(today);
			projectInfo.setPrstatus("0");
		}
		
		modelMap.addAttribute("projectInfo", projectInfo);
		modelMap.addAttribute("prno", prno);
		
		return "project/ProjectForm";	
	}
	
	
	/**
	 * ���옣 
	 */
	@RequestMapping(value = "/projectSave")
    public String projectSave(HttpServletRequest request, ProjectVO projectInfo) {
        String userno = request.getSession().getAttribute("userno").toString();
        projectInfo.setUserno(userno);

        if (projectInfo.getPrno() != null && !"".equals(projectInfo.getPrno())) {    // check auth for update
            String chk = projectSvc.selectProjectAuthChk(projectInfo);
            if (chk == null) {
                return "common/noAuth";
            }
        }

        projectSvc.insertProject(projectInfo);
        
        return "redirect:projectList";
    }
}