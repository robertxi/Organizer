package com.robert.Organizer.solrTests;

import com.robert.Organizer.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;


public interface TaskRepository extends SolrCrudRepository<Task, String>{
    List<Task> findByTitle(String title);
    Page<Task> findByTitleOrDescription(@Boost(2) String title, String description, Pageable pageable);
}
