package com.epam.ld.module2.testing

import spock.lang.Specification

class MathTest extends Specification {
    void setupSpec() {

    }

    def Add() {
        setup:
        def obj = new Math();
        expect:
        obj.add(1, 2) == 3
    }

    def Multiply() {
        setup:
        def obj = new Math()
        when:
        def c = obj.multiply(3, 4)
        then:
        c == 12
    }
}
