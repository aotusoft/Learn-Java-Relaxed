package cn.my.Dao;

public class ASImp implements AccountService {
    private AccountDao aD;

    public void setaD(AccountDao aD) {
        this.aD = aD;
    }

    @Override
    public void transfer(String outUser, String inUser, int money) {
        try {

        } catch (ArithmeticException e) {
            System.err.println(e);
        }

        this.aD.out(outUser, money);
//        int z = 1 / 0;
        this.aD.in(inUser, money);
    }
}
