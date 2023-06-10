package com.szymon_kaluza.protobuf_avro.benchmark;

import com.google.protobuf.InvalidProtocolBufferException;
import com.szymon_kaluza.protobuf_avro.application.ProtoLibraryService;
import com.szymon_kaluza.protobuf_avro.proto.model.Library;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;

import static com.szymon_kaluza.protobuf_avro.benchmark.ProtoBenchmarkDataFactory.*;

@State(org.openjdk.jmh.annotations.Scope.Benchmark)
public class ProtoBench {

    private final ProtoLibraryService protoLibraryService = new ProtoLibraryService();

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeSmallThroughput(SmallLibrary input) {
        protoLibraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeBigThroughput(BigLibrary input) {
        protoLibraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeAndDeserializeSmallThroughput(SmallLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = protoLibraryService.serialize(input.library);
        blackhole.consume(protoLibraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.Throughput)
    public void serializeAndDeserializeBigThroughput(BigLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = protoLibraryService.serialize(input.library);
        blackhole.consume(protoLibraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.AverageTime)
    public void serializeSmallAverageTime(SmallLibrary input) {
        protoLibraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.AverageTime)
    public void serializeBigAverageTime(BigLibrary input) {
        protoLibraryService.serialize(input.library);
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.AverageTime)
    public void serializeAndDeserializeSmallAverageTime(SmallLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = protoLibraryService.serialize(input.library);
        blackhole.consume(protoLibraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 3, time = 3)
    @Fork(3)
    @Measurement(iterations = 100, time = 3)
    @BenchmarkMode(Mode.AverageTime)
    public void serializeAndDeserializeBigAverageTime(BigLibrary input, Blackhole blackhole) throws InvalidProtocolBufferException {
        byte[] serialized = protoLibraryService.serialize(input.library);
        blackhole.consume(protoLibraryService.deserialize(serialized));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Fork(0)
    @Measurement(iterations = 1)
    @BenchmarkMode(Mode.SingleShotTime)
    public void sizeOfSerializedData(Parameters parameters) {
        protoLibraryService.printSerializedSize(getFixedLibrary(getManyFixedBooks(parameters.librarySize)));
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
