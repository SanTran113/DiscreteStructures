class AddExpression
   extends BinaryExpression
{

   public AddExpression(final Expression lft, final Expression rht)
   {
      super (lft, rht, "+");
   }

//   @Override
//   public double evaluate(final Bindings bindings)
//   {
//      return _applyOperator(lft.evaluate(bindings), rht.evaluate(bindings));
//   }

   @Override
   protected double _applyOperator(double left, double right) {
      return right + left;
   }

}
