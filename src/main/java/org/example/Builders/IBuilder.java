package org.example.Builders;

import org.example.Exceptions.UserException;

public interface IBuilder<T> {
    T build() throws UserException;
}