package q006.value;

import java.math.BigDecimal;
import java.util.Stack;

public class WaruValue implements IValue {
    
    @Override
    public void execute(Stack<BigDecimal> values) {
        // スタックから2つの値を抜き出し、割り算した結果をスタックに積む
        BigDecimal right = values.pop();
        BigDecimal left = values.pop();
        values.push(right.divide(left));
    }  
}