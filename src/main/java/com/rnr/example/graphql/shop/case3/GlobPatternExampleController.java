package com.rnr.example.graphql.shop.case3;

import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GlobPatternExampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobPatternExampleController.class);
    
    /*
        hierarchy{
            level1 {
              level2 {
                description
              }
            }
          }
     */
    @SchemaMapping(typeName = "Query")
    public Object hierarchy(DataFetchingEnvironment dataFetchingEnvironment){
        logSelectionSet(dataFetchingEnvironment, "level1");
        logSelectionSet(dataFetchingEnvironment, "level1/level2");
        logSelectionSet(dataFetchingEnvironment, "level1/*/level4");
        logSelectionSet(dataFetchingEnvironment, "level1/**/level4");
        logSelectionSet(dataFetchingEnvironment, "**/level3");
        return dataFetchingEnvironment.getSelectionSet().getImmediateFields();
    }

    private void logSelectionSet(DataFetchingEnvironment dataFetchingEnvironment, String level) {
        LOGGER.info("{} = {}", level, dataFetchingEnvironment.getSelectionSet().contains(level));
    }

}
