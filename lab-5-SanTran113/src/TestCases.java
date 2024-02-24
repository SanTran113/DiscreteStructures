import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCases
{
   public static final double DELTA = 0.00001;
   private Bindings bindings = new VariableBindings();


   @Test
   public void test01_AddExpressionEvaluate()
   {
      bindings.addBinding("x", 2.5);
      bindings.addBinding("y", 10);
      IdentifierExpression x = new IdentifierExpression("x");
      IdentifierExpression y = new IdentifierExpression("y");
      AddExpression add = new AddExpression(x, y);

      assertEquals(12.5, add.evaluate(bindings), DELTA);
   }

   @Test
   public void test02_AddExpressionToString()
   {
      bindings.addBinding("x", 2.5);
      bindings.addBinding("y", 10);
      IdentifierExpression x = new IdentifierExpression("x");
      IdentifierExpression y = new IdentifierExpression("y");
      AddExpression add = new AddExpression(x, y);

      assertEquals("(x + y)", add.toString());
   }
}