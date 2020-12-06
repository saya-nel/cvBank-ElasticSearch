package com.pablitobello.cvBankElasticsearch.repository;

import com.pablitobello.cvBankElasticsearch.model.CV;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CvRepository extends ElasticsearchRepository<CV, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"tags\": \"?0\"}}]}}")
    Page<CV> findByTagUsingDeclaredQuery(String tag, Pageable pageable);

}
