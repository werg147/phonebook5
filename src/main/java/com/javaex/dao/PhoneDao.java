package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트 가져오기
	public List<PersonVo> getPersonList(){
		System.out.println("dao: getPersonList()");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList2");
		//System.out.println(personList.toString());
		return personList;
	}
	
	//전화번호 저장
	public void personInsert(PersonVo personVo) {
		System.out.println(personVo);
		
		int count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(count);
	}
	
	//전화번호 삭제
	public void personDelete(int personId) {
		System.out.println("dao delete(): " + personId);
		
		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count);
	}
	
	
	//1명 데이터 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("dao getPerson: " + personId);
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", personId);
		System.out.println(personVo.toString());
		
		return personVo;
	}
	
	//1명 데이터 가져오기2
	public Map<String, Object> getPerson2(int personId) {
		System.out.println("dao getPerson2: " + personId);
			
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectOne2", personId);
		System.out.println(personMap.toString());	
		
		String name = (String)personMap.get("name");
		System.out.println(name);
		
		
		
		return personMap;
	}
	
	//전화번호 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("dao update: " + personVo);
		
		//int count = sqlSession.update("phonebook.update", personVo);
		//return count;
		
		return sqlSession.update("phonebook.update", personVo);
	}
	
	//수정2
	public int personUpdate2(int personId, String name, String hp, String company) {
		System.out.println("dao update2: " + personId + "," + name + "," + hp + "," + company);
		
		//한번만 쓰고 말 것 같아서 vo 대신 -> map
		//PersonVo personVo = new PersonVo(personId, name, hp, company);
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("personId", personId);
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap.toString());
		
		return sqlSession.update("phonebook.update2", personMap);
		
	}

	
}
