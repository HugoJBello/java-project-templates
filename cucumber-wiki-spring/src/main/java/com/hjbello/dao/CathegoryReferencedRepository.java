package com.hjbello.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CathegoryReferencedRepository extends CrudRepository<CathegoryReferenced, Long> {
	
	public CathegoryReferenced save(CathegoryReferenced cathegoryReferenced);
		
	public void delete(CathegoryReferenced cathegoryReferenced);
	
	@Transactional
	public void removeByEntryNameReferenced(String entryName);

	public ArrayList<CathegoryReferenced> findByCathegoryName(String cathegoryName);
	
	
	public ArrayList<CathegoryReferenced> findByentryNameReferenced(String entryName);

	
//	@Query(value = "SELECT t FROM page_entries t where t.title LIKE '%' || :text||'%'")
//	public List<PageEntry> findCustom (@Param("text") String text);
}
