package com.github.rusakovichma.tictaac.correction;

public interface Corrector<T> {

    public boolean tryToCorrect(T t);

}
