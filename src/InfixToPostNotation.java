import java.util.Stack;

class InfixToPostNotation

{



    public static void main(String args[])

    {



        InfixToPostfixConversion convert=new InfixToPostfixConversion();

        String str="A+B+C*D/E";

        String postfix=convert.infixToPostfix(str);

        System.out.println("Postfix expression is :"+convert.infixToPostfix(str));

        double result=convert.postfixEvaluation(postfix);

        System.out.println("Postfix expression is :"+result);



    }



}

class InfixToPostfixConversion

{

    public String infixToPostfix(String infix)

    {

        String postfix="";

        Stack<Character> stack=new Stack<>();

        stack.push('(');

        infix=infix.concat(String.valueOf(')'));

        for(int i=0; i<infix.length(); i++)

        {

            char character=infix.charAt(i);



            if(Character.isAlphabetic(character)||Character.isDigit(character))

            {



                postfix=postfix.concat(String.valueOf(character));



            }

            else if(character=='(')

            {

                stack.push(character);



            }

            else if(isOperator(character))

            {

                while(!stack.empty())

                {



                    if(precedence(stack.peek())>=precedence(character))

                    {

                        postfix=postfix.concat(String.valueOf(stack.pop()));

                    }

                    else

                    {

                        stack.push(character);

                        break;



                    }



                }





            }

            else if(character==')')

            {



                while(!stack.empty())

                {

                    if(stack.peek()!='(')

                    {

                        postfix=postfix.concat(String.valueOf(stack.pop()));



                    }

                    else

                    {

                        stack.pop();

                        break;



                    }





                }



            }





        }

        return postfix;



    }



    private boolean isOperator(char operator)

    {

        boolean response=false;

        switch(operator)

        {

            case '^':

            case '*':

            case '/':

            case '+':

            case '-':

                response=true;





        }

        return response;



    }

    public int precedence(char operator)

    {

        int response=0;

        switch(operator)

        {

            case '^':

                response=3;

                break;

            case '/':

            case '*':

                response=2;

                break;

            case '+':

            case '-':

                response=1;

        }



        return response;





    }

// It will be ask in end term practical exams...

    public double postfixEvaluation(String postfix)

    {

        double response=0;

        Stack<Double> stack=new Stack<>();

        //step 1

        postfix=postfix.concat(String.valueOf(')'));

        //step2

        for (int i = 0; i < postfix.length(); i++) {

            char character=postfix.charAt(i);

            if(Character.isDigit(character))

            {

                stack.push(Double.valueOf(Character.toString(character)));

            }

            else if(isOperator(character))

            {

                double second=stack.pop();

                double first=stack.pop();

                stack.push(evaluate(first,second,character));

                //stack.push((evaluate(stack.pop(),stack.pop(),character)));



            }

        }

        response=stack.peek();

        return response;



    }

    private double evaluate(double first, double second, char operator)

    {

        System.out.println(first);

        System.out.println(second);

        double response=0;

        switch(operator)

        {

            case '^':

                response=Math.pow(first,second);

                break;



            case '/':

                response=(double)first/second;

                break;

            case '*':

                response=first*second;

                break;

            case '+':

                response=first+second;

                break;

            case '-':



                response=first-second;



        }

        return response;





    }



}