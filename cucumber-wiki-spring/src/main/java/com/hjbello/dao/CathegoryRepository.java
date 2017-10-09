package com.hjbello.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface CathegoryRepository extends CrudRepository<Cathegory, Long> {
	
	public Cathegory save(Cathegory pageEntry);
	
	public Cathegory findByCathegoryName(String cathegoryName);
	
//	@Query(value = "SELECT t FROM page_entries t where t.title LIKE '%' || :text||'%'")
//	public List<PageEntry> findCustom (@Param("text") String text);
}
