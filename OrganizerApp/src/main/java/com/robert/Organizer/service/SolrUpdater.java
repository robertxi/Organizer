package com.robert.Organizer.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


public class SolrUpdater {

    GeneralRepositoryTests SolrSearch = ApplicationContextHolder.getContext().getBean(GeneralRepositoryTests.class);
    @Scheduled (fixedRate=100000)
    public void updateSolr(){
        SolrSearch.updateSolrRepository();
    }

}
