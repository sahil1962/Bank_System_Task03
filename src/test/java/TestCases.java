import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class TestCases extends TestCase {
    @Test
    public void testAdmin_CreateAccount_Test_1() throws Exception {
        Admin A = new Admin();
        assertEquals(true, A.Admin_CreateAccount("99943666", "99927222", "Ahmed",
                "Khan", 20000, "10/1/2021", "saving", "6154"));
    }
//    @Test(expected=Exception.class)
//    public void testAdmin_CreateAccount_Test_3() throws Exception {//exception test case
//        Admin A = new Admin();
//        A.Admin_CreateAccount("32434564", "635622789", "Ahmed",
//                "Ali", 23000, "20/21/2021", "checking", "6654");
//    }

    @Test
    public void testAdmin_CheckZakat_Test_1() throws Exception {
        Filehandling FH = new Filehandling();
        Admin A = new Admin();
        assertEquals(true, A.Admin_CheckZakat("24575687"));
    }

    @Test
    public void testAdmin_CheckZakat_Test_2() throws Exception {
        Filehandling FH = new Filehandling();
        Admin A = new Admin();
        assertEquals(false, A.Admin_CheckZakat("6723452"));
    }

    @Test
    public void testAdmin_PrintStatement_Test_1() throws Exception {
        Admin A = new Admin();
        assertEquals(true, A.Admin_PrintStatement("65443388"));

    }

    @Test
    public void testAdmin_PrintStatement_Test_2() throws Exception {
        Admin A = new Admin();
        assertEquals(false, A.Admin_PrintStatement("6723472"));

    }

    @Test
    public void testAdmin_PrintDeduction_Test_1() throws Exception {
        Admin A = new Admin();
        assertEquals(true, A.Admin_PrintDeduction("24575687"));
    }

    @Test
    public void testAdmin_PrintDeduction_Test_2() throws Exception {
        Admin A = new Admin();
        assertEquals(false, A.Admin_PrintDeduction("6723572"));
    }

    @Test
    public void testAdmin_PrintDeduction_Test_3() throws Exception {
        Admin A = new Admin();
        assertEquals(true, A.Admin_PrintDeduction("24575687"));
    }

    @Test
    public void testAdmin_CalculateInterest_Test_1() throws Exception {
//        Admin A = new Admin();
//        assertEquals(true, A.Admin_CalculateInterest("67234572"));
    }

    @Test
    public void testAdmin_CalculateInterest_Test_2() throws Exception {
        Admin A = new Admin();
        assertEquals(false, A.Admin_CalculateInterest("6724572"));
    }

//    @Test
//    public void testAdmin_DeleteAccount_Test_1() throws Exception {
//        Admin A = new Admin();
//        assertEquals(true, A.Admin_DeleteAccount("71124543", "63452789", "6654"));
//    }
//
//    @Test
//    public void testAdmin_DeleteAccount_Test_2() throws Exception {
//        Admin A = new Admin();
//        assertEquals(false, A.Admin_DeleteAccount("6723572", "56433789", "6754"));
//    }
//
//    @Test
//    public void testAdmin_DeleteAccount_Test_3() throws Exception {
//        Admin A = new Admin();
//        assertEquals(false, A.Admin_DeleteAccount("67234572", "5643389", "6754"));
//    }
//
//    @Test
//    public void testAdmin_DeleteAccount_Test_4() throws Exception {
//        Admin A = new Admin();
//        assertEquals(false, A.Admin_DeleteAccount("67234572", "56433789", "6654"));
//    }

    @Test
    public void testUser_DepositeAmount_Test_1() throws Exception {
        User U = new User();
        assertEquals(true, U.User_DepositeAmount("45379087", 50000));
    }

    @Test
    public void testUser_DepositeAmount_Test_2() throws Exception {
        User U = new User();
        assertEquals(false, U.User_DepositeAmount("4537907", 25000));
    }

    @Test
    public void testUser_WithdrawAmount_Test_1() throws Exception {
        User U = new User();
        assertEquals(true, U.User_WithdrawAmount("45379087", "3456", 2000));
    }

    @Test
    public void testUser_WithdrawAmount_Test_2() {
//        User U = new User();
//        assertEquals(false, U.User_WithdrawAmount("45379087", "3456", 205500));
    }

    @Test
    public void testUser_WithdrawAmount_Test_3() throws Exception {
        User U = new User();
        assertEquals(false, U.User_WithdrawAmount("45379087", "4456", 2000));
    }

    @Test
    public void testUser_WithdrawAmount_Test_4() throws Exception {
        User U = new User();
        assertEquals(false, U.User_WithdrawAmount("12454436", "3456", 2000));
    }

    @Test
    public void testUser_WithdrawAmount_Test_5() throws Exception {
        User U = new User();
        assertEquals(false, U.User_WithdrawAmount("12454431", "3456", 2000));
    }

    @Test
    public void testUser_CheckAmount_Test_1() throws Exception {
        User U = new User();
        assertEquals(true, U.User_CheckAmount("45379087"));
    }

    @Test
    public void testUser_CheckAmount_Test_2() throws Exception {
        User U = new User();
        assertEquals(false, U.User_CheckAmount("4537987"));
    }

    @Test
    public void testUser_TransferAmount_Test_1() throws Exception {
        User U = new User();
        assertEquals(true, U.User_TransferAmount("45379087", "13467907", "3456", 5000));
    }

    @Test
    public void testUser_TransferAmount_Test_2() throws Exception {
        User U = new User();
        assertEquals(false, U.User_TransferAmount("4539087", "13467907", "3456", 5000));
    }

    @Test
    public void testUser_TransferAmount_Test_3() throws Exception {
        User U = new User();
        assertEquals(false, U.User_TransferAmount("45379087", "1346907", "3456", 5000));
    }

    @Test
    public void testUser_TransferAmount_Test_4() throws Exception {
        User U = new User();
        assertEquals(false, U.User_TransferAmount("45379087", "13467907", "7456", 5000));
    }

}

