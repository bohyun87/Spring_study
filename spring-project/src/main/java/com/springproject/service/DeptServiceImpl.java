package com.springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.dao.DeptDao;
import com.springproject.dto.Dept;

//1. service 의 역할을 하는 클래스, 2. 자동을 bean 이 되어 스프링 컨테이너에 등록된다.
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired  //스프링컨테이너가 의종성을 만들어준다.
	DeptDao deptDao;
	
    
	/*
    @Autowired 를 안했을 경우 의존성 주입 방법

    1. 생성자 이용하기
    
	DeptDao deptDao;
	
	public DeptServiceImpl(DeptDao deptDao){
		this.deptDao = deptDAo
	}
		
	2.의존선 만드는 법 두번째
	
	DeptDao deptDao;	
	
	public void setDeptDao(DeptDao deptDao){
		this.deptDao = deptDAo
	}	
	*/
	
    
	
	@Override
	public List<Dept> selectList() {
		return deptDao.selectList();
	}

}
