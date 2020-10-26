package hu.admin.organ;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptSvc {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<?> selectDepartment() {
		return sqlSession.selectList("selectDepartment");
	}
	
	public DepartmentVO selectDepartmentOne(String param) {   //adDepartmentRead
		return sqlSession.selectOne("selectDepartmentOne", param);
	}
	
	
	/**
     * 부서저장.     
     */
    public void insertDepartment(DepartmentVO param) {
        if ("".equals(param.getParentno())) {
            param.setParentno(null); 
        }
            
        if (param.getDeptno() == null || "".equals(param.getDeptno())) {
             sqlSession.insert("insertDepartment", param);
        } else {
             sqlSession.insert("updateDepartment", param);
        }
    }
    
    /**
     * 부서 삭제
     */
    public void deleteDepartment(String param) {
        sqlSession.delete("deleteDepartment", param);
    }
    
    
}
