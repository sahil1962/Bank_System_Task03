
import java.io.File;
import java.lang.reflect.Type;
import java.util.Scanner;

public class Bank_Accounts extends Filehandling implements Saving {
    private int Balance;
    private String Fname, Lname, Date, Type = "", CNIC, AccountNo, Pin;


    void setFname(String s) {
        Fname = s;
    }

    String getFname() {
        return Fname;
    }

    void setLname(String s) {

        Lname = s;
    }

    String getLname() {
        return Lname;
    }

    void setCNIC(String s) {

        CNIC = s;
    }

    String getCNIC() {

        return CNIC;
    }

    void setAccountNo(String s) {

        AccountNo = s;
    }

    String getAccountNo() {
        return AccountNo;
    }

    void setDate(String s) {

        Date = s;
    }

    String getDate() {
        return Date;
    }

    void setPin(String s) {
        Pin = s;
    }

    String getPin() {
        return Pin;
    }

    void setType(String s) {
        Type = s;
    }

    String getType() {
        return Type;
    }

    public boolean CreateAccount(String AccountNo, String CNIC, String Fname,
                                 String Lname, int Balance, String Date, String Type, String Pin) throws Exception {
        if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == false) {
//sending CNIC data to CNIC.txt
            Filehandling FileCreate = new Filehandling();
            FileCreate.CreateFile("CNIC.txt");
            String CNICstring = String.valueOf(CNIC);
            FileCreate.WriteToFile("CNIC.txt", CNICstring + "\n");

//sending Account number data to AcNo.txt
            Filehandling FileCreate2 = new Filehandling();
            FileCreate2.CreateFile("AcNo.txt");
            FileCreate2.WriteToFile("AcNo.txt", AccountNo + "\n");

//sending Account holder Balance data to Balance.txt
            Filehandling FileCreate3 = new Filehandling();
            FileCreate3.CreateFile("Balance.txt");
            String BalanceString = String.valueOf(Balance);
            FileCreate3.WriteToFile("Balance.txt", BalanceString + "\n");

//sending Account holder name data to AcNo.txt
            Filehandling FileCreate4 = new Filehandling();
            FileCreate4.CreateFile("Username.txt");
            FileCreate4.WriteToFile("Username.txt", Fname + " " + Lname + "\n");

//sending Account holder Type data to Type.txt
            Filehandling FileCreate6 = new Filehandling();
            FileCreate6.CreateFile("Type.txt");
            FileCreate6.WriteToFile("Type.txt", Type + "\n");

//sending Account holder Balance data to Balance.txt
            Filehandling FileCreate5 = new Filehandling();
            FileCreate5.CreateFile("Pin.txt");
            FileCreate3.WriteToFile("Pin.txt", Pin + "\n");
            return true;

        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == true) {
            throw new Exception("Same Account number already exists....");
        }
        return false;
    }

    public boolean makeDeposit(String AccountNo, int Amount) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AccountNo);
            String oldBalance = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
            int old = Integer.parseInt(oldBalance);
            Balance = Amount + old;
            String BalanceString = String.valueOf(Balance);
            ReadAndWriteSepcificLine("Balance.txt", lineNumber, oldBalance, BalanceString);
            return true;
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }

    public boolean makeWithdrawal(String AccountNo, String Pin, int WithdrawAmount) throws Exception {
        int lineNumber;
        lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AccountNo);
        if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == true) {
            if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == true) {
                String oldBalance = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
                System.out.println("Balance: "+ oldBalance);
                int old = Integer.parseInt(oldBalance);
                if (old > 20000) {
                    if (old > WithdrawAmount) {
                        Balance = old - WithdrawAmount;
                        String BalanceString = String.valueOf(Balance);
                        ReadAndWriteSepcificLine("Balance.txt", lineNumber, oldBalance, BalanceString);
                        return true;
                    } else {
                        System.out.println("\n\n****Withdraw Amount is excessive, not available in your account****");
                        return false;
                    }
                } else if (old < 20000) {
                    System.out.println("Your Account balance is less than 20,000.\n");
                    return false;
                }
            } else if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == false) {
                System.out.println("Incorrect Pin.\n");
                return false;
            }
        } else {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }

    public boolean checkBalance(String AccountNo) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AccountNo);
            System.out.println("The Balance of account number is : " + ReturnStringOfMatchingCount("Balance.txt", lineNumber));
            return true;
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AccountNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }

    public boolean printStatement(String AcNo) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AcNo);
            System.out.println(
                    "********Your Account Statement********\n" +
                            "Account number: " + ReturnStringOfMatchingCount("AcNo.txt", lineNumber) + "\n" +
                            "CNIC: " + ReturnStringOfMatchingCount("CNIC.txt", lineNumber) + "\n" +
                            "Name: " + ReturnStringOfMatchingCount("Username.txt", lineNumber) + "\n" +
                            "Account Type: " + ReturnStringOfMatchingCount("Type.txt", lineNumber) + "\n" +
                            "Balance: " + ReturnStringOfMatchingCount("Balance.txt", lineNumber)
            );
            return true;
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }
    public boolean transferAmount(String FromAcNo, String ToAcNo, String Pin, int TransferAmount) throws Exception {
        int lineNumber, TolineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", FromAcNo) == true) {
            if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == true) {
                if (ReturnBoleanOfMatchingString("AcNo.txt", ToAcNo) == true) {
                    lineNumber = ReturnCountOfMatchingLine("AcNo.txt", FromAcNo);
                    TolineNumber = ReturnCountOfMatchingLine("AcNo.txt", ToAcNo);
                    String oldBalance = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
                    String ToOldBalance = ReturnStringOfMatchingCount("Balance.txt", TolineNumber);
                    int old = Integer.parseInt(oldBalance);
                    int ToOld = Integer.parseInt(ToOldBalance);
                    if (old > TransferAmount) {
                        ToOld = TransferAmount + ToOld;
                        old = old - TransferAmount;
                        String BalanceString = String.valueOf(old);
                        String ToBalanceString = String.valueOf(ToOld);
                        ReadAndWriteSepcificLine("Balance.txt", lineNumber, oldBalance, BalanceString);
                        ReadAndWriteSepcificLine("Balance.txt", TolineNumber, ToOldBalance, ToBalanceString);
                        return true;
                    } else if (old < TransferAmount) {
                        System.out.println("\n\n****Withdraw Amount is excessive, not available in your account****");
                        return false;
                    }
                } else if (ReturnBoleanOfMatchingString("AcNo.txt", ToAcNo) == false) {
                    System.out.println("\n\n****Account Number is wrong in which you want to transfer money****");
                    return false;
                }
            } else if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == false) {
                System.out.println("\n\n****Wrong Pin****");
                return false;
            }
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", FromAcNo) == false) {
            System.out.println("Your Account number is wrong\n");
            return false;
        }
        return false;
    }

    public boolean calculateZakat(String AcNo) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AcNo);
            String AmountInAccount = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
            double old = Integer.parseInt(AmountInAccount);
            old = old * 0.025;
            System.out.println("The Balance of account number is : " + AmountInAccount + " so, the Zakat will be: " + old);
            return true;
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;

    }

    public boolean displayAllDeductions(String AcNo) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AcNo);
            String AmountInAccount = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
            String AccountType = ReturnStringOfMatchingCount("Type.txt", lineNumber);
            if (ReturnStringOfMatchingCount("Type.txt", lineNumber).equals("saving")) {
                double old = Integer.parseInt(AmountInAccount);
                old = old * 0.025;
                System.out.println("The Balance of account number is : " + AmountInAccount + ".\n" +
                        "and your account type is " + AccountType + ".\n" +
                        "So, the total tax(only Zakat charger) will be: " + old);
                return true;
            } else if (ReturnStringOfMatchingCount("Type.txt", lineNumber).equals("checking")) {
                double balance = Integer.parseInt(AmountInAccount);
                double zakat = 0, otherTax = 0, totalTax = 0;
                zakat = balance * 0.025;
                otherTax = balance * 0.05;
                totalTax = zakat + otherTax;
                System.out.println("The Balance of account number is : " + AmountInAccount + ".\n" +
                        "and your account type is " + AccountType + ".\n" +
                        "So, the total tax(bank charger{5%} + Zakat) will be: " + otherTax + " + " + zakat + " = " + totalTax);
                return true;
            }
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }

    public boolean Interset(String AcNo) throws Exception {
        int lineNumber;
        if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == true) {
            lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AcNo);
            String AmountInAccount = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
            String AccountType = ReturnStringOfMatchingCount("Type.txt", lineNumber);
            if (AccountType.equals("saving")) {
                double Interest = Integer.parseInt(AmountInAccount);
                Interest = Interest * 0.10;
                System.out.println("The Balance of account number is : " + AmountInAccount + ".\n" +
                        "and your account type is " + AccountType + ".\n" +
                        "So, the interest(10%) will be: " + Interest);
            } else if (ReturnStringOfMatchingCount("Type.txt", lineNumber).equals("checking")) {
                System.out.println("****The account is checking, so no interest on your account balance****");
            }
            return true;
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }

    public boolean DeleteAccount(String AcNo, String CNIC, String Pin) throws Exception {
        int lineNumber;
        lineNumber = ReturnCountOfMatchingLine("AcNo.txt", AcNo);
        String AccountNumber = ReturnStringOfMatchingCount("AcNo.txt", lineNumber);
        String Balance = ReturnStringOfMatchingCount("Balance.txt", lineNumber);
        String CNICfromFile = ReturnStringOfMatchingCount("CNIC.txt", lineNumber);
        String PinFromFile = ReturnStringOfMatchingCount("Pin.txt", lineNumber);
        String AccountType = ReturnStringOfMatchingCount("Type.txt", lineNumber);
        String Username = ReturnStringOfMatchingCount("Username.txt", lineNumber);
        if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == true) {
            if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == true) {
                if (ReturnBoleanOfMatchingString("CNIC.txt", CNIC) == true) {
                    ReadAndWriteSepcificLine("AcNo.txt", lineNumber, AccountNumber, "Deleted Account");
                    ReadAndWriteSepcificLine("Balance.txt", lineNumber, Balance, "Deleted Account");
                    ReadAndWriteSepcificLine("CNIC.txt", lineNumber, CNICfromFile, "Deleted Account");
                    ReadAndWriteSepcificLine("Pin.txt", lineNumber, PinFromFile, "Deleted Account");
                    ReadAndWriteSepcificLine("Type.txt", lineNumber, AccountType, "Deleted Account");
                    ReadAndWriteSepcificLine("Username.txt", lineNumber, Username, "Deleted Account");
                    return true;
                } else {
                    System.out.println("\n\n****CNIC not matched, no matching account****");
                    return false;
                }
            } else if (ReturnBoleanOfMatchingString("Pin.txt", Pin) == false) {
                System.out.println("*******Wrong Pin*******\n");
                return false;
            }
        } else if (ReturnBoleanOfMatchingString("AcNo.txt", AcNo) == false) {
            System.out.println("Wrong Account number\n");
            return false;
        }
        return false;
    }


}



