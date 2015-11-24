package com.robert.Organizer.solrTests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;


public interface BookRepository extends SolrCrudRepository<Book, String> {
    List<Book> findByName(String name);

    //boost annotation boosts the books whos names match the search name parameter
    Page<Book> findByNameOrDescription(@Boost(2) String name, String description, Pageable pageable);

    List<Book> findByCategories(Category category);

}
