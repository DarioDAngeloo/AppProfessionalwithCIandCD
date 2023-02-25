package com.study.appprofessionalwithciandcd.domain

class ExpressionEvaluator(
    private val expression : List<ExpressionPart>
) {
    fun evaluate(): Double {
        return evalExpression(expression).value
    }

    private fun evalExpression(expression: List<ExpressionPart>) :ExpressionResult{
        val result = evalTerm(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while (true){
            when(remaining.firstOrNull()){
                ExpressionPart.OperationSymbol(Operation.ADD)->{
                    val term = evalTerm(remaining.drop(1))
                    sum += term.value
                    remaining = term.remainingExpression
                }
                ExpressionPart.OperationSymbol(Operation.SUBTRACT)->{
                    val term = evalTerm(remaining.drop(1))
                    sum -= term.value
                    remaining = term.remainingExpression
                }
                else -> return ExpressionResult(remaining,sum)
            }
        }
    }


    private fun evalTerm(expression: List<ExpressionPart>) :ExpressionResult{
        val result = evalFactor(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while (true){
            when(remaining.firstOrNull()){
                ExpressionPart.OperationSymbol(Operation.MULTIPLY)->{
                    val factor = evalFactor(remaining.drop(1))
                    sum *= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.OperationSymbol(Operation.DIVIDE)->{
                    val factor = evalFactor(remaining.drop(1))
                    sum /= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.OperationSymbol(Operation.PERCENT)->{
                    val factor = evalFactor(remaining.drop(1))
                    sum *= (factor.value / 100.0)
                    remaining = factor.remainingExpression
                }
                else -> return ExpressionResult(remaining,sum)
            }
        }
    }
    //

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
            ExpressionPart.OperationSymbol(Operation.PERCENT)-> evalTerm(expression.drop(1))
            is ExpressionPart.Number -> ExpressionResult(
                remainingExpression = expression.drop(1),
                value = part.number
            )
            else -> throw java.lang.IllegalArgumentException("me quiiero irrr de acaa")
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}