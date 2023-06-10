package com.szymon_kaluza.protobuf_avro.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaBuilder {
    public Schema createSchema() {
        Schema author = SchemaBuilder.record("Author").namespace("org.example.petavro.domain").fields()
                .optionalString("name")
                .optionalString("surname")
                .optionalString("nationality")
                .endRecord();

        Schema book = SchemaBuilder.record("Book").namespace("org.example.petavro.domain").fields()
                .optionalString("title")
                .name("author").type(author).noDefault()
                .optionalLong("pages")
                .optionalBoolean("available")
                .endRecord();

        return SchemaBuilder.record("Library").namespace("org.example.petavro.domain").fields()
                .optionalString("address")
                .name("books").type().array().items().type(book).noDefault()
                .endRecord();
    }
}