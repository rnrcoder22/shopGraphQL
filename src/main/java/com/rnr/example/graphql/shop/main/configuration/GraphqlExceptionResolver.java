package com.rnr.example.graphql.shop.main.configuration;

import com.rnr.example.graphql.shop.main.exceptions.InvalidInputException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

//TODO use DataFetcherExceptionResolver instead
@Component
public class GraphqlExceptionResolver extends DataFetcherExceptionResolverAdapter {
    
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof DataIntegrityViolationException) {
            return buildInternalError(ex, env);
        } else if (ex instanceof InvalidInputException) {
            return buildBadRequest(ex, env);
        } else if(ex instanceof DataAccessException){
            return buildInternalError(ex, env);
        } else {
            return buildInternalError(ex, env);
        }
    }

    private static GraphQLError buildBadRequest(Throwable ex, DataFetchingEnvironment env) {
        return buildErrorWithType(ex, env, ErrorType.BAD_REQUEST);
    }

    private static GraphQLError buildInternalError(Throwable ex, DataFetchingEnvironment env) {
        return buildErrorWithType(ex, env, ErrorType.INTERNAL_ERROR);
    }
    
    private static GraphQLError buildErrorWithType(Throwable ex, DataFetchingEnvironment env, ErrorType errorType){
        String extensionFieldsInfo = env.getSelectionSet().getFields().stream()
                .map(f -> String.format("%s-%s-%s", f.getName(), f.getType(), f.getLevel()))
                .collect(Collectors.joining());
        return GraphqlErrorBuilder.newError()
          .errorType(errorType)
          .message(ex.getMessage())
          .path(env.getExecutionStepInfo().getPath())
          .location(env.getField().getSourceLocation())
          .extensions(Map.of(
            "fields", extensionFieldsInfo,
                "timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
          )
          .build();
    }
}
