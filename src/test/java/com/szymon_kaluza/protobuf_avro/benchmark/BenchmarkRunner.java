package com.szymon_kaluza.protobuf_avro.benchmark;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

public class BenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtoBench.class.getSimpleName())
                .include(AvroBench.class.getSimpleName())
                .verbosity(VerboseMode.EXTRA)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
