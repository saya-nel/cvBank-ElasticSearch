package com.pablitobello.cvBankElasticsearch.service;

import com.pablitobello.cvBankElasticsearch.model.CV;

import java.util.List;

public interface CvService {

    CV save(CV cv);

    List<CV> findByTag(String tag);

    Iterable<CV> findAll();
}
