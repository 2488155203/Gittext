package pojo;

import java.util.Set;

public class Userinfo {
    private String account;
    private String password;
    private Set<Goods> goodsSet;

    public Set<Goods> getGoodsSet() { return goodsSet; }

    public void setGoodsSet(Set<Goods> goodsSet) { this.goodsSet = goodsSet; }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
