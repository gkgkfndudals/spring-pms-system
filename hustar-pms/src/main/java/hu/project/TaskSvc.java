package hu.project;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

@Service
public class TaskSvc {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private DataSourceTransactionManager txManager;
	
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectSvc.class);
	
	/**
	 * Task 관련 함수
	 */
	public List<?> selectTaskList(String param) {
		return sqlSession.selectList("selectTaskList", param);
	}
}