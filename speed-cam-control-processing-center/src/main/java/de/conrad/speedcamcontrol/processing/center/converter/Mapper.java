package de.conrad.speedcamcontrol.processing.center.converter;

@FunctionalInterface
public interface Mapper<S, T> {

    T convert(S source);
}
