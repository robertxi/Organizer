package com.robert.Organizer.model;

import com.robert.Organizer.model.TaskEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface TaskEntryRepository extends SolrCrudRepository<TaskEntry, String> {
    Page<TaskEntry> findByTitleOrDescription(@Boost(2)String title, String description, Pageable pageable);

}
