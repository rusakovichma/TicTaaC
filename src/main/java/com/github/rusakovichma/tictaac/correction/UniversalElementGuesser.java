/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;

import java.util.ArrayList;
import java.util.Collection;

public class UniversalElementGuesser implements Guesser<Element> {

    private Collection<Guesser<Element>> guessers = new ArrayList<>();

    private void init() {
        guessers.add(new WebserverGuesser());
        guessers.add(new DatabaseGuesser());
        guessers.add(new ProxyServerGuesser());
        guessers.add(new InteractorGuesser());
        guessers.add(new ExternalServiceGuesser());
        guessers.add(new InternalServiceGuesser());
        guessers.add(new ProcessGuesser());
    }

    public UniversalElementGuesser() {
        init();
    }

    @Override
    public boolean tryToCorrect(Element element) {
        for (Guesser<Element> guesser : guessers) {
            if (guesser.tryToCorrect(element)) {
                return true;
            }
        }
        return false;
    }
}
