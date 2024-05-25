package com.rnr.example.graphql.shop.main.configuration;

import com.rnr.example.graphql.shop.main.exceptions.NonParsableScalarType;
import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.sql.Date;
import java.time.LocalDate;

@Configuration
public class ShopConfiguration {
    
    @Bean 
    public RuntimeWiringConfigurer runtimeWiringForDateConfigurer() { 
       return wiringBuilder -> wiringBuilder.scalar(scalarDate()); 
    }
    
    @Bean 
    public RuntimeWiringConfigurer runtimeWiringForLongConfigurer() { 
       return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLLong);  
    }

    /**
     * Registering this scalar to use float values greater than or equals to zero.
     * It will be applied for salary's stuff.
     * @return RuntimeWiringConfigurer
     */
    @Bean
    public RuntimeWiringConfigurer extendedScalarNonNegativeFloat() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.NonNegativeFloat);
    }

    /**
     * Provides a custom scalar to support java.sql.Date types in GraphQL.
     */
    
    private GraphQLScalarType scalarDate(){
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("java.sql.Date")
                .coercing(new Coercing<Date, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Date) {
                            return dataFetcherResult.toString();
                        } else {
                            throw getNonParsableDataException(dataFetcherResult, Date.class);
                        }
                    }
                    @Override
                    public Date parseValue(Object input) throws CoercingParseValueException {
                         if (input instanceof String) {
                            return Date.valueOf(LocalDate.parse(((String)input)));
                        } else {
                             throw getNonParsableDataException(input, Date.class);
                         }
                    }
                    
                    @Override
                    public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue) {
                            return Date.valueOf(LocalDate.parse(((StringValue)input).getValue()));
                        } else {
                            throw getNonParsableDataException(input, Date.class);
                        }
                    }
                }).build();
    }

    private static NonParsableScalarType getNonParsableDataException(Object data, Class expectedClass) {
        return new NonParsableScalarType(String.format(
            "Unable to serialize %s as %s to String. Wrong Type. Expected %s, Got %s", 
            data, expectedClass.getName(), expectedClass.getName(), data.getClass()));
    }
}
