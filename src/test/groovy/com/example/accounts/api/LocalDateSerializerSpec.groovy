package com.example.accounts.api

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import spock.lang.Specification

import java.time.LocalDate

import static java.time.format.DateTimeFormatter.ofPattern

class LocalDateSerializerSpec extends Specification {

    def serializer = new LocalDateSerializer()

    def 'should serialize LocalDate'() {
        given:
        def date = LocalDate.now()

        and:
        def gen = Mock(JsonGenerator)
        def sp = Mock(SerializerProvider)

        when:
        serializer.serialize(date, gen, sp)

        then:
        interaction {
            gen.writeString(_ as String)
            date.format(ofPattern("dd/MM/yyyy"))
        }

    }
}
