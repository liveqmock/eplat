/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package test.test.tree;

import java.util.ArrayList;
import java.util.List;

class GrassGenerator {

    private final int numBlades;

    public GrassGenerator(int numBlades) {
        this.numBlades = numBlades;
    }

    public List<Blade> generateGrass() {
        List<Blade> grass = new ArrayList<Blade>(numBlades);
        for (int i = 0; i < numBlades; i++) {
            final Blade blade = new Blade();
            grass.add(blade);
        }
        return grass;
    }
}
