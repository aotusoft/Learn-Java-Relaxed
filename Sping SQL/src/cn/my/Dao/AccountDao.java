package cn.my.Dao;

public interface AccountDao {
    //付款
    public void out(String outUser,int money);
    //收款
    public void in(String inUser,int money);
}
