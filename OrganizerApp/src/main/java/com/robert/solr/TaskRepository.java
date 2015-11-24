package com.robert.solr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created by RobertXi on 11/23/15.
 */
public interface TaskRepository extends SolrCrudRepository<Task, String>{
    List<Task> findByTitle(String title);
    Page<Task> findByTitleOrDescription(@Boost(2) String title, String description, Pageable pageable);
}
