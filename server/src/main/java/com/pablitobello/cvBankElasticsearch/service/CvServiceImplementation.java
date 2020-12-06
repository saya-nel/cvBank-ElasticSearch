package com.pablitobello.cvBankElasticsearch.service;

import com.pablitobello.cvBankElasticsearch.model.CV;
import com.pablitobello.cvBankElasticsearch.repository.CvRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CvServiceImplementation implements CvService {

    @Autowired
    private CvRepository cvRepository;

    @Override
    public CV save(CV cv) {
        return cvRepository.save(cv);
    }

    @Override
    public List<CV> findByTag(String tag) {
        Page<CV> cvByTag = cvRepository.findByTagUsingDeclaredQuery(tag,PageRequest.of(0,10));
        return cvByTag.get().collect(Collectors.toList());
    }

    @Override
    public Iterable<CV> findAll() {
        return cvRepository.findAll();
    }
}
