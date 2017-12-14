package com.shenpu.config.server.config;

import java.util.LinkedList;
import java.util.List;
import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Serializable;
import scala.Tuple2;
import zipkin.Codec;
import zipkin.DependencyLink;
import zipkin.Span;
import zipkin.internal.DependencyLinker;
import zipkin.internal.GroupByTraceId;
import zipkin.internal.Nullable;

final class TraceIdAndJsonToDependencyLinks implements Serializable,
    Function<Iterable<Tuple2<String, String>>, Iterable<DependencyLink>> {
  private static final long serialVersionUID = 0L;
  private static final Logger log = LoggerFactory.getLogger(TraceIdAndJsonToDependencyLinks.class);

  @Nullable final Runnable logInitializer;

  TraceIdAndJsonToDependencyLinks(Runnable logInitializer) {
    this.logInitializer = logInitializer;
  }

  @Override public Iterable<DependencyLink> call(Iterable<Tuple2<String, String>> traceIdJson) {
    if (logInitializer != null) logInitializer.run();
    List<Span> sameTraceId = new LinkedList<>();
    for (Tuple2<String, String> row : traceIdJson) {
      try {
        sameTraceId.add(Codec.JSON.readSpan(row._2.getBytes()));
      } catch (RuntimeException e) {
        log.warn("Unable to decode span from traces where trace_id=" + row._1, e);
      }
      sameTraceId.add(Codec.JSON.readSpan(row._2.getBytes()));
    }
    DependencyLinker linker = new DependencyLinker();
    for (List<Span> trace : GroupByTraceId.apply(sameTraceId, true, true)) {
      linker.putTrace(trace);
    }
    return linker.link();
  }
}
