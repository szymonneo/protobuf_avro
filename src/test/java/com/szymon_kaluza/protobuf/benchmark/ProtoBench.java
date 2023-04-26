package com.szymon_kaluza.protobuf.benchmark;

import com.google.protobuf.InvalidProtocolBufferException;
import com.szymon_kaluza.protobuf.application.LibraryService;
import com.szymon_kaluza.protobuf.proto.model.Library;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;

import static com.szymon_kaluza.protobuf.benchmark.BenchmarkDataFactory.*;

@State(org.openjdk.jmh.annotations.Scope.Benchmark)
public class ProtoBench {

    private final LibraryService libraryService = new LibraryService();

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeSmall(SmallLibrary input) {
        libraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeBig(BigLibrary input) {
        libraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeAndDeserializeSmall(SmallLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = libraryService.serialize(input.library);
        blackhole.consume(libraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeAndDeserializeBig(BigLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = libraryService.serialize(input.library);
        blackhole.consume(libraryService.deserialize(serialized));
    }

    @State(Scope.Benchmark)
    public static class SmallLibrary {
        public Library library;

        @Setup(Level.Invocation)
        public void setUp() {
            library = getLibrary(List.of(getBook()));
        }
    }

    @State(Scope.Benchmark)
    public static class BigLibrary {
        public Library library;

        @Setup(Level.Invocation)
        public void setUp() {
            library = getLibrary(getManyBooks(1000));
        }
    }
}
