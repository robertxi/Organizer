package com.robert.Organizer;

import com.robert.Organizer.service.ApplicationContextHolder;
import com.robert.Organizer.service.GeneralRepositoryTests;
import com.robert.Organizer.service.SolrUpdater;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableSolrRepositories(basePackages = {"com.robert.Organizer"})
@SpringBootApplication
@EnableScheduling
public class Application {

//    @Autowired
//    private static BookRepositoryTests tests;
//
    public static void main(String[] args) {
        //gets context
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        GeneralRepositoryTests tests = ApplicationContextHolder.getContext().getBean(GeneralRepositoryTests.class);

        tests.init();

    }

    //solrServerused to connect to running Solr instance
    @Bean
    public HttpSolrServer solrServer(){
        return new HttpSolrServer("http://localhost:8983/solr");
    }

    //SolrTemplate provides common functionality to workwith Solr
    @Bean
    public SolrTemplate solrTemplate(SolrServer server) throws Exception{
        return new SolrTemplate(server);
    }

    @Bean
    public SolrUpdater solrUpdater(){
        return new SolrUpdater();
    }

}
