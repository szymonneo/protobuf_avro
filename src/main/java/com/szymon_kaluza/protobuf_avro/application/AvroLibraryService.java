package com.szymon_kaluza.protobuf_avro.application;

import com.szymon_kaluza.protobuf_avro.avro.model.Library;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AvroLibraryService {

    public byte[] serialize(Library library) {
        SpecificDatumWriter<Library> writer = new SpecificDatumWriter<>(Library.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder encoder;
        try {
            encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(library, encoder);
            encoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            System.out.println("Serialization error");
        }
        return data;
    }

    public Library deserialize(byte[] serializedLibrary) {
        SpecificDatumReader<Library> reader = new SpecificDatumReader<>(Library.class);
        Decoder decoder;
        try {
            decoder = DecoderFactory.get().binaryDecoder(serializedLibrary, null);
            return reader.read(null, decoder);
        } catch (IOException e) {
            System.out.println("Serialization error");
        }
        return null;
    }

    public void printSerializedSize(Library library) {
        System.out.printf("Serialized library with book count: %s has size: %s%n", library.getBooks().size(), serialize(library).length);
    }
}
