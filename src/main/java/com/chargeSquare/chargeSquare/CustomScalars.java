package com.chargeSquare.chargeSquare;

import graphql.schema.GraphQLScalarType;
import graphql.schema.Coercing;
import org.jetbrains.annotations.NotNull;

public class CustomScalars {
    public static final GraphQLScalarType DateTime = GraphQLScalarType.newScalar()
            .name("DateTime")
            .description("Custom DateTime scalar")
            .coercing(new Coercing<>() {
                @Override
                public Object serialize(@NotNull Object dataFetcherResult) {
                    return dataFetcherResult.toString(); // Serialization logic
                }

                @NotNull
                @Override
                public Object parseValue(@NotNull Object input) {
                    return input; // Parsing logic
                }

                @NotNull
                @Override
                public Object parseLiteral(@NotNull Object input) {
                    return input; // Literal parsing logic
                }
            })
            .build();
}
