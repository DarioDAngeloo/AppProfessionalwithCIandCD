package com.study.appprofessionalwithciandcd.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ExpressionWriterTest {

    private lateinit var writer: ExpressionWriter

    @Before
    fun setup(){
        writer = ExpressionWriter()
    }

    @Test
    fun `Initial parentheses parsed`() {
        writer.processAction(action = CalculatorAction.Parentheses)
        writer.processAction(action = CalculatorAction.Number(5))
        writer.processAction(action = CalculatorAction.OperationSymbol(Operation.ADD))
        writer.processAction(action = CalculatorAction.Number(5))
        writer.processAction(action = CalculatorAction.Parentheses)

        assertThat(writer.expression).isEqualTo("(5+5)")
    }


    @Test
    fun `Closing parentheses at the start not parsed`() {
        writer.processAction(action = CalculatorAction.Parentheses)
        writer.processAction(action = CalculatorAction.Parentheses)

        assertThat(writer.expression).isEqualTo("((")
    }

    @Test
    fun `Parentheses around a number are parsed`() {
        writer.processAction(action = CalculatorAction.Parentheses)
        writer.processAction(action = CalculatorAction.Number(5))
        writer.processAction(action = CalculatorAction.Parentheses)

        assertThat(writer.expression).isEqualTo("(5)")
    }

}