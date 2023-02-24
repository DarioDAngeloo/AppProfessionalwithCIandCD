package com.study.appprofessionalwithciandcd.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionParserTest {
    private lateinit var parser : ExpressionParser

    @Test
    fun `Simple expression is correctly parsed`(){

        //GIVEN
        parser = ExpressionParser("2+2x2")

        //DO SOMETHING WITH THAT'S GIVEN
        val actual = parser.parse()

        //ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(2.0),
            ExpressionPart.OperationSymbol(Operation.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.OperationSymbol(Operation.MULTIPLY),
            ExpressionPart.Number(2.0),

            )

        assertThat(expected).isEqualTo(actual)

    }


    @Test
    fun `Expression with parentheses is correctly parsed`(){
        parser = ExpressionParser("20+(1+2)")
        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(20.0),
            ExpressionPart.OperationSymbol(Operation.ADD),
            ExpressionPart.Parentheses(type = ParenthesesType.Opening),
            ExpressionPart.Number(1.0),
            ExpressionPart.OperationSymbol(Operation.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Parentheses(type = ParenthesesType.Closing),
            )
        assertThat(expected).isEqualTo(actual)
    }

}