package com.example.demo;

import org.graphqlize.java.GraphQLResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

class GraphQLRequest {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    private Map<String, Object> variables;
}

@RestController
public class GraphQLController {
    private final GraphQLResolver graphQLResolver;

    public GraphQLController(GraphQLResolver graphQLResolver) {
        this.graphQLResolver = graphQLResolver;
    }

    @PostMapping("/graphql")
    public ResponseEntity handle(@RequestBody GraphQLRequest graphQLRequest) {
        String result =
                graphQLResolver.resolve(
                        graphQLRequest.getQuery(),
                        graphQLRequest.getVariables());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(result);
    }
}