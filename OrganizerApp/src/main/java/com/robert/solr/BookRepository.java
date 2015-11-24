package com.robert.solr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created by RobertXi on 11/22/15.
 */
public interface BookRepository extends SolrCrudRepository<Book, String> {
    List<Book> findByName(String name);

    //boost annotation boosts the books whos names match the search name parameter
    Page<Book> findByNameOrDescription(@Boost(2) String name, String description, Pageable pageable);

    List<Book> findByCategories(Category category);

}
