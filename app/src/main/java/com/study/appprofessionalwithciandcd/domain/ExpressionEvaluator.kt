package com.study.appprofessionalwithciandcd.domain

class ExpressionEvaluator(
    private val expression : List<ExpressionPart>
) {
    fun evaluate(): Double {
        return 0.0
    }

    private fun evalFactor(expression: List<ExpressionPart>): ExpressionResult{
        return when(val part = expression.firstOrNull()){
            ExpressionPart.OperationSymbol(Operation.ADD) ->{
                evalFactor(expression.drop(1))
            }
            ExpressionPart.OperationSymbol(Operation.SUBTRACT) ->{
                evalFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression,-value)
                }
            }
            ExpressionPart.Parentheses(ParenthesesType.Opening) -> {
                evalExpression(expression.drop(1)).run{
                    ExpressionResult(remainingExpression.drop(1),value)
                }
            }
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}