package com.pablitobello.cvBankElasticsearch.resources;

import com.pablitobello.cvBankElasticsearch.model.CV;
import com.pablitobello.cvBankElasticsearch.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CvResource {

    @Autowired
    private CvService cvService;

    @PostMapping("/cv")
    public ResponseEntity postCV(@RequestBody CV cv) {
        cv.setTags(Arrays.stream(cv.getTags()).map(s -> s.toLowerCase()).toArray(String[]::new));
        cvService.save(cv);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cv")
    public ResponseEntity<List<CV>> getCvByTag(@RequestParam String tag)  {
        List<CV> res = cvService.findByTag(tag.toLowerCase());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/cvs")
    public ResponseEntity<Iterable<CV>> getAll()  {
        Iterable<CV> res = cvService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
