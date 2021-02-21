package de.conrad.speedcamcontrol.dataprovider.converter;

@FunctionalInterface
public interface Mapper<S, T> {

    T convert(S source);
}
