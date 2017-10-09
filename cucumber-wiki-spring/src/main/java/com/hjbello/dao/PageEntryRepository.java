package com.hjbello.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface PageEntryRepository extends CrudRepository<PageEntry, Long> {
	
	public PageEntry save(PageEntry pageEntry);
		
	public PageEntry findByEntryName(String entryName);
	
	public PageImpl<PageEntry> findAll(Pageable pageable);
	
//	@Query(value = "SELECT t FROM page_entries t where t.title LIKE '%' || :text||'%'")
//	public List<PageEntry> findCustom (@Param("text") String text);
}
