package com.pablitobello.cvBankElasticsearch.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "cv")
@ToString
public class CV {

    @Id
    private String id;

    @Field(type = Keyword)
    private String name;

    @Field(type = Keyword)
    private String base64;

    @Field(type = Keyword)
    private String[] tags;

    public CV(){
    }

    public CV(String id, String name, String base64, String[] tags) {
        this.id = id;
        this.name = name;
        this.base64 = base64;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public String getBase64() { return base64; }

    public String[] getTags() {
        return tags;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setBase64(String base64) { this.base64 = base64; }

    public void setTags(String [] tags) { this.tags = tags; }

}
