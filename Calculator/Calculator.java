package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.Math;


class NumberButtonListener implements ActionListener {
    //base class for digit buttons
    protected JTextField textField;
    protected JButton button;

    public NumberButtonListener(JTextField textField, JButton button) {
        this.textField = textField;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText(textField.getText() + button.getText());
    }
}

class OneBtnListener extends NumberButtonListener {
    public OneBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}
class TwoBtnListener extends NumberButtonListener {
    public TwoBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}

class ThreeBtnListener extends NumberButtonListener {
    public ThreeBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}

class FourBtnListener extends NumberButtonListener {
    public FourBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}
class FiveBtnListener extends NumberButtonListener {
    public FiveBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}

class SixBtnListener extends NumberButtonListener {
    public SixBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}
class SevenBtnListener extends NumberButtonListener {
    public SevenBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}

class EightBtnListener extends NumberButtonListener {
    public EightBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}

class NineBtnListener extends NumberButtonListener {
    public NineBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}
class ZeroBtnListener extends NumberButtonListener {
    public ZeroBtnListener(JTextField textField, JButton button) {
        super(textField, button);
    }

}


public class Calculator {

    private JPanel Calculator;
    private JTextField textField;
    private JButton zeroBtn;
    private JButton oneBtn;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton addBtn;
    private JButton mAddBtn;
    private JButton minusBtn;
    private JButton mulBtn;
    private JButton divideBtn;
    private JButton clearBtn;
    private JButton squareBtn;
    private JButton mClearBtn;
    private JButton signBtn;
    private JButton logBtn;
    private JButton mRecallBtn;
    private JButton mSubBtn;
    private JButton powerBtn;
    private JButton percentageBtn;
    private JButton digitBtn;
    private JButton sqrBtn;
    private JButton equalBtn;
    double a,b,result;
    double memory = 0;
    String op;


    public Calculator() {
        //constructor

        // Set default value to 0
        textField.setText("0");



        //Create Listeners for all number buttons

        OneBtnListener oneBtnListener = new OneBtnListener(textField, oneBtn);
        TwoBtnListener twoBtnListener = new TwoBtnListener(textField, twoBtn);
        ThreeBtnListener threeBtnListener = new ThreeBtnListener(textField,threeBtn);
        FourBtnListener fourBtnListener = new FourBtnListener(textField,fourBtn);
        FiveBtnListener fiveBtnListener = new FiveBtnListener(textField,fiveBtn);
        SixBtnListener  sixBtnListener = new SixBtnListener(textField,sixBtn);
        SevenBtnListener sevenBtnListener = new SevenBtnListener(textField,sevenBtn);
        EightBtnListener eightBtnListener = new EightBtnListener(textField,eightBtn);
        NineBtnListener nineBtnListener = new NineBtnListener(textField,nineBtn);
        ZeroBtnListener zeroBtnListener = new ZeroBtnListener(textField,zeroBtn);

        // Attach buttons to their listeners

        oneBtn.addActionListener(oneBtnListener);
        twoBtn.addActionListener(twoBtnListener);
        threeBtn.addActionListener(threeBtnListener);
        fourBtn.addActionListener(fourBtnListener);
        fiveBtn.addActionListener(fiveBtnListener);
        sixBtn.addActionListener(sixBtnListener);
        sevenBtn.addActionListener(sevenBtnListener);
        eightBtn.addActionListener(eightBtnListener);
        nineBtn.addActionListener(nineBtnListener);
        zeroBtn.addActionListener(zeroBtnListener);

        //Define actions performed by operation buttons when clicked

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("0");
            }

        });

        signBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().contains("."))
                {
                    double pm = Double.parseDouble(textField.getText());
                    pm = pm * -1;
                    textField.setText(String.valueOf(pm));
                }
                else
                {
                    long PM = Long.parseLong(textField.getText());
                    PM = PM * -1;
                    textField.setText(String.valueOf(PM));
                }
            }
        });
        digitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().contains("."))
                {
                    textField.setText(textField.getText() + digitBtn.getText());
                }
            }
        });
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                op = "+";
                textField.setText("");
            }
        });
        minusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                op = "-";
                textField.setText("");
            }
        });
        divideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                op = "/";
                textField.setText("");
            }
        });
        mulBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                op = "*";
                textField.setText("");
            }
        });
        equalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                b = Double.parseDouble(textField.getText());

                if (op == "+")
                {
                    result = a + b;
                    textField.setText(String.valueOf(result));
                }
                else if(op == "-")
                {
                    result = a - b;
                    textField.setText(String.valueOf(result));
                }
                else if (op == "*")
                {
                    result = a * b;
                    textField.setText(String.valueOf(result));
                }
                else if (op == "/")
                {
                    if (b != 0) {

                        result = a / b;
                        textField.setText(String.valueOf(result));
                    } else {
                        result = 0;
                        textField.setText("Error: dividing by zero");
                        return;
                    }
                }
                else if (op == "^")
                {

                    result = Math.pow(a,b);
                    textField.setText(String.valueOf(result));

                }

                if (result == Math.floor(result)) {
                    textField.setText(String.valueOf((int) result)); // Display as an integer
                } else {
                    textField.setText(String.valueOf(result)); // Display as a double
                }


            }
        });
        squareBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                result = a * a;

                if (result == Math.floor(result)) {
                    textField.setText(String.valueOf((int) result)); // Display as an integer
                } else {
                    textField.setText(String.valueOf(result)); // Display as a double
                }

            }
        });
        powerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                op = "^";
                textField.setText("");
            }
        });
        logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                result = Math.log(a);
                if (result == Math.floor(result)) {
                    textField.setText(String.valueOf((int) result)); // Display as an integer
                } else {
                    textField.setText(String.valueOf(result)); // Display as a double
                }
            }
        });
        sqrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());

                if (a >= 0) {
                    result = Math.sqrt(a);
                    if (result == Math.floor(result)) {
                        textField.setText(String.valueOf((int) result)); // Display as an integer
                    } else {
                        textField.setText(String.valueOf(result)); // Display as a double
                    }
                } else {
                    textField.setText("Error: Cannot square root a negative number");
                }
            }
        });
        percentageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                result = a/100;
                textField.setText(String.valueOf(result));
            }
        });
        mClearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = 0;

            }
        });
        mAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                memory+=a;
            }
        });
        mSubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textField.getText());
                memory-=a;
            }
        });
        mRecallBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = memory;
                op = "MR";
                if (result == Math.floor(result)) {
                    textField.setText(String.valueOf((int) result)); // Display as an integer
                } else {
                    textField.setText(String.valueOf(result)); // Display as a double
                }

            }
        });

        // Add a focus listener to clear the default 0 when the user starts inputting a number
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("0")) {
                    textField.setText("");
                }
            }

        });
        // Add a focus listener to clear the 0 that the All clear button leaves before you start inputting a number
        clearBtn.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("0")){
                    textField.setText("");
                }
            }
        });
    }



    public static void main(String[] args) {
        //create the form and pack it inside the frame

        JFrame frame = new JFrame("Calculator");
        Calculator calculator = new Calculator();
        frame.setContentPane(calculator.Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        calculator.test("1");
        calculator.test("+");
        calculator.test("2");
        calculator.test("=");
        calculator.test("AC");
        calculator.test("3");
        calculator.test("-");
        calculator.test("4");
        calculator.test("=");
        calculator.test("AC");
        calculator.test("1");
        calculator.test("/");
        calculator.test("0");
        calculator.test("=");


    }
    public void test( String button) {
        switch (button) {
            case "0":
                zeroBtn.doClick();
                break;
            case "1":
                oneBtn.doClick();
                break;
            case "2":
                twoBtn.doClick();
                break;
            case "3":
                threeBtn.doClick();
                break;
            case "4":
                fourBtn.doClick();
                break;
            case "5":
                fiveBtn.doClick();
                break;
            case "6":
                sixBtn.doClick();
                break;
            case "7":
                sevenBtn.doClick();
                break;
            case "8":
                eightBtn.doClick();
                break;
            case "9":
                nineBtn.doClick();
                break;
            case "%":
                percentageBtn.doClick();
                break;
            case "-/+":
                signBtn.doClick();
                break;
            case "AC":
                clearBtn.doClick();
                break;
            case "*2":
                squareBtn.doClick();
                break;
            case "sqr":
                sqrBtn.doClick();
                break;
            case "log":
                logBtn.doClick();
                break;
            case ".":
                digitBtn.doClick();
                break;
            case "+":
                addBtn.doClick();
                break;
            case "-":
                minusBtn.doClick();
                break;
            case "*":
                mulBtn.doClick();
                break;
            case "/":
                divideBtn.doClick();
                break;
            case "**":
                powerBtn.doClick();
                break;
            case "M+":
                mAddBtn.doClick();
                break;
            case "M-":
                mSubBtn.doClick();
                break;
            case "MR":
                mRecallBtn.doClick();
                break;
            case "MC":
               // mClearBtn.doClick();
                break;
            case "=":
                equalBtn.doClick();
                break;
            case "txt":
                System.out.println("The result is: " +
                        textField.getText());
                break;
        }
    }
}
