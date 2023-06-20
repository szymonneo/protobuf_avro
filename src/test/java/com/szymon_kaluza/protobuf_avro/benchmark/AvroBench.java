package com.szymon_kaluza.protobuf_avro.benchmark;

import com.szymon_kaluza.protobuf_avro.application.AvroLibraryService;
import com.szymon_kaluza.protobuf_avro.avro.model.Library;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;

import static com.szymon_kaluza.protobuf_avro.benchmark.AvroBenchmarkDataFactory.*;

@State(Scope.Benchmark)
public class AvroBench {

    private final AvroLibraryService libraryService = new AvroLibraryService();

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
    public void serializeAndDeserializeSmall(SmallLibrary input, Blackhole blackhole) {
        byte[] serialized = libraryService.serialize(input.library);
        blackhole.consume(libraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeAndDeserializeBig(BigLibrary input, Blackhole blackhole) {
        byte[] serialized = libraryService.serialize(input.library);
        blackhole.consume(libraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Fork(0)
    @Measurement(iterations = 1)
    @BenchmarkMode(Mode.SingleShotTime)
    public void sizeOfSerializedData(Parameters parameters) {
        libraryService.printSerializedSize(getFixedLibrary(getManyFixedBooks(parameters.librarySize)));
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

    @State(Scope.Benchmark)
    public static class Parameters {

        @Param({"1", "10", "1000", "1000000"})
        public int librarySize;
    }
}
