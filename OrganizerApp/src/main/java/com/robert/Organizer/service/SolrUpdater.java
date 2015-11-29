package com.robert.Organizer.service;

import org.springframework.scheduling.annotation.Scheduled;


public class SolrUpdater {

    SolrRepositoryService SolrSearch = ApplicationContextHolder.getContext().getBean(SolrRepositoryService.class);
    @Scheduled (fixedRate=100000)
    public void updateSolr(){
        SolrSearch.updateSolrRepository();
    }

}
