package hu.admin.organ;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.common.FileUtil;
import hu.common.FileVO;
import hu.common.TreeMaker;
import hu.common.UtilEtc;
import hu.etc.EtcSvc;
import hu.member.UserVO;

@Controller
public class UserCtr {

	@Autowired
	private DeptSvc deptSvc;
	
	@Autowired
	private UserSvc userSvc;
	
	@Autowired
	private EtcSvc etcSvc;
	
	/**
	 * 리스트
	 */
	@RequestMapping(value = "/adUser")
	public String user(HttpServletRequest request, ModelMap modelMap) {
		String userno = request.getSession().getAttribute("userno").toString();
		
		Integer alertcount = etcSvc.selectAlertCount(userno);
		modelMap.addAttribute("alertcount", alertcount);
		
		List<?> listview = deptSvc.selectDepartment();
		
		TreeMaker tm = new TreeMaker();
		String treeStr = tm.makeTreeByHierarchy(listview);
		
		modelMap.addAttribute("treeStr", treeStr);
		
		return "admin/organ/User";
	}
	
	/**
	 * 지정된 부서의 사용자 리스트
	 */
	public String common_UserList(ModelMap modelMap, String deptno) {
		List<?> listview = userSvc.selectUserList(deptno);
		modelMap.addAttribute("listview", listview);
		return "admin/organ/UserList";
	}
	
	/**
	 * User 리스트
	 */
	@RequestMapping(value = "/adUserList")
	public String userList(HttpServletRequest request, ModelMap modelMap) {
		String deptno = request.getParameter("deptno");
		
		return common_UserList(modelMap, deptno);
	}
	
	
	/**
	 * 사용자 저장, 신규 사용자는 저장 전에 중복 확인
	 * 사용자 수정
	 */
	/**
     * @RequestMapping으로 String type을 반환해주면, ViewResolver에서 정의한 prefix와 suffix가 return값에 추가되어 view로 이동이 된다.
		@ResponseBody를 사용해주면 view를 생성해주는것이 아니라, JSON 혹은 Object 형태로 데이터를 넘겨준다.
		https://myjamong.tistory.com/17
		그래서 ajax에서 fn_addUsersave함수에서 return을 해준 formData가 내부적으로 userInfo랑 맵핑이 되어진다.
     */
	@RequestMapping(value = "/adUserSave")
	public String userSave(HttpServletResponse response, ModelMap modelMap, UserVO userInfo) {
		if(userInfo.getUserno() ==null || "".equals(userInfo.getUserno())) {
			System.out.println(" ================> adUserSave null!!!");
			String userid = userSvc.selectUserID(userInfo.getUserid()); //userid가 null로 나온다 -> 아직 신규 사용자를 등록하지 않았으니까
			System.out.println("================> " + userid);
			if(userid !=null) {
				return "common/blank";
			}
		}
		FileUtil fs = new FileUtil();
		
		FileVO fileInfo = fs.saveFile(userInfo.getPhotofile());
		if(fileInfo != null) {
			userInfo.setPhoto(fileInfo.getRealname());
		}
		System.out.println("=========> insertUser전 :" + userInfo.getUserid() + userInfo.getUsernm());
		userSvc.insertUser(userInfo);
		System.out.println("=========> insertUser후 :" + userInfo.getUserid() + userInfo.getUsernm() + userInfo.getUserpw() + userInfo.getDeptno());
		return common_UserList(modelMap, userInfo.getDeptno());
	}
	
	/**
     * ID 중복 확인.
     */
    @RequestMapping(value = "/chkUserid")
    public void chkUserid(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");

        userid = userSvc.selectUserID(userid);

        UtilEtc.responseJsonValue(response, userid);
    }
	
    /**
     * 사용자 삭제.
     */
    @RequestMapping(value = "/adUserDelete")
    public String userDelete(HttpServletRequest request, ModelMap modelMap, UserVO userInfo) {
        
        userSvc.deleteUser(userInfo.getUserno());  //update로 com_user
        
        return common_UserList(modelMap, userInfo.getDeptno());
    }
	
    
    /**
     * 사용자 조회.
     */
    @RequestMapping(value = "/adUserRead")
    public void userRead(HttpServletRequest request, HttpServletResponse response) {
        String userno = request.getParameter("userno");
        
        UserVO userInfo = userSvc.selectUserOne(userno);

        UtilEtc.responseJsonValue(response, userInfo);
    }
	
	
}
