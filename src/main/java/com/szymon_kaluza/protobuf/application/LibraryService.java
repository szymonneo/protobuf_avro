package com.szymon_kaluza.protobuf.application;

import com.google.protobuf.InvalidProtocolBufferException;
import com.szymon_kaluza.protobuf.proto.model.Library;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    public byte[] serialize(Library library) {
        return library.toByteArray();
    }

    public Library deserialize(byte[] serializedLibrary) throws InvalidProtocolBufferException {
        return Library.parseFrom(serializedLibrary);
    }
}
