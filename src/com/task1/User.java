package com.task1;

import java.util.*;

class Cart{
    double priceD=0;
    ArrayList goodsInCart = new ArrayList();
    Scanner addScan = new Scanner(System.in);
    public double getPriceD(){
        return this.priceD;
    }
    public void setPriceD(double price){
        this.priceD = price;
    }
    public void listCart(){
        if(goodsInCart.isEmpty()){
            System.out.println("Your Cart is empty!");
        }
        else {
            int i=0;
            // 遍历值集合
            for (Object item : goodsInCart){
                String s = (String) item;
                System.out.println(i++ + " Index of Goods: " + s);
            }
            System.out.println("Total Price:"+getPriceD());
        }
    }
    public void add(int GID,int number){
        goodsInCart.add("goodsID="+GID + "&amount=" + number);
    }
    public String pop(int index){
        String popGoods = goodsInCart.get(index).toString();
        goodsInCart.remove(index);
        return popGoods;
    }
    public void clear(){
        goodsInCart.clear();
        priceD = 0;
    }

}

public interface User {
    Http http = new Http();
    void userLogin();
}

class Customer implements User{
    Scanner userScan = new Scanner(System.in);
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

    public void reqJOSN(String str){
        System.out.print(http.request(str).replaceAll("[\\[,\\]]","\n"));
    }

    String account,password;
    Cart cart = new Cart();

    Customer(String acc,String pass){
        this.account=acc;
        this.password=pass;
    }

    protected void listGoods(){
        String listGoodsReq = "http://happybirthdaytolinan.site/phpN/goods.php";
        reqJOSN(listGoodsReq);
    }

    protected void addGoodsToCart(){
        int goodsID,amount;
        String priceReq = "http://happybirthdaytolinan.site/phpN/price.php?";
        try{
            System.out.print("Please Choose Goods'ID to add to cart:");
            goodsID = userScan.nextInt();
            System.out.print("Please Enter Goods'amount:");
            amount = userScan.nextInt();
            String priceStr = http.request(priceReq + "goodsID=" + goodsID + "&amount=" + amount);
            System.out.println("Price:"+priceStr);
            double priceDou = Double.parseDouble(priceStr);
            cart.setPriceD(priceDou+cart.getPriceD());
            cart.add(goodsID,amount);
        }catch (Exception exceptionAddGoods){
            System.out.println(exceptionAddGoods.getMessage());
        }
    }

    protected void check(){
        String goodsSoldReq = "http://happybirthdaytolinan.site/phpN/goodsSold.php?";
        while (!cart.goodsInCart.isEmpty())
        {
            goodsSoldReq = goodsSoldReq + cart.goodsInCart.get(0) + "&UA=" + account;
            System.out.println(http.request(goodsSoldReq));
            cart.goodsInCart.remove(0);
        }
        cart.priceD = 0;
    }

    protected void removeSelectGoods(){
        if(cart.goodsInCart.isEmpty()){
            System.out.println("Hey!Please Add Some Goods Into Your Cart First!");
        }else {
            String priceReq = "http://happybirthdaytolinan.site/phpN/price.php?";
            System.out.print("Please choose the index of goods:");
            try {
                int indexOfGoods = userScan.nextInt();
                double priceDou = Double.parseDouble(http.request(priceReq + cart.pop(indexOfGoods)));
                cart.setPriceD(cart.getPriceD() - priceDou);
            }catch (Exception userRemoveGoods){
                System.out.println(userRemoveGoods.getMessage());
            }
        }


    }

    protected void getMoney(){
        System.out.print("you have:");
        String moneyReq = "http://happybirthdaytolinan.site/phpN/money.php?account=" + account;
        System.out.println(http.request(moneyReq));
        System.out.println("PLAESE Contact Alipay：13506871553 For Charge");
    }

    protected void listBought()
    {
        String userGoodsReq = "http://happybirthdaytolinan.site/phpN/userGoods.php?account=" + account;
        reqJOSN(userGoodsReq);
    }

    @Override
    public void userLogin() {
        while (true)
        {
            int option = 1;
            System.out.println("Welcome:"+getAccount());
            System.out.print("Press  1:ListGoods  2:AddGoodsToCart  3:ListCart  4:CheckALLGoods  5:ClearCart  \r\n\t  6:RemoveSelectGoods  7:ListMoney&Charge  8:ListBought  else:back\nPlease Enter:");
            try {
                option = userScan.nextInt();
                switch (option)
                {
                    case 1:listGoods(); break;
                    case 2:addGoodsToCart(); break;
                    case 3:cart.listCart(); break;
                    case 4:check(); break;
                    case 5:cart.clear(); break;
                    case 6:removeSelectGoods(); break;
                    case 7:getMoney(); break;
                    case 8:listBought(); break;
                    default:
                        return;
                }
            }catch (Exception exceptionCustomer){
                System.out.println(exceptionCustomer.getMessage());
                return;
            }
        }
    }
}

class VIPCustomer extends Customer{
    VIPCustomer(String acc,String pass){
        super(acc,pass);
    }
    Scanner userScan = new Scanner(System.in);
    private int VIPRate=1;
    public int getVIPRate(){
        return this.VIPRate;
    }
    public void setVIPRate(int rate){
        this.VIPRate = rate;
    }

    @Override
    public void userLogin() {
        while (true)
        {
            int option = 1;
            System.out.println("Welcome: VIP "+getAccount());
            System.out.print("Press  1:ListGoods  2:AddGoodsToCart  3:ListCart  4:CheckALLGoods  5:ClearCart  \r\n\t  6:RemoveSelectGoods  7:ListMoney&Charge  8:ListBought  else:back\nPlease Enter:");
            try {
                option = userScan.nextInt();
                switch (option)
                {
                    case 1:listGoods(); break;
                    case 2:addGoodsToCart(); break;
                    case 3:cart.listCart(); break;
                    case 4:check(); break;
                    case 5:cart.clear(); break;
                    case 6:removeSelectGoods(); break;
                    case 7:getMoney(); break;
                    case 8:listBought(); break;
                    default:
                        return;
                }
            }catch (Exception exceptionCustomer){
                System.out.println(exceptionCustomer.getMessage());
                return;
            }
        }
    }
}

class Admin implements User{
    String account,password;
    Scanner userScan = new Scanner(System.in);

    Admin(String acc,String pass){
        this.account=acc;
        this.password=pass;
    }

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

    public void reqJOSN(String str){
        System.out.print(http.request(str).replaceAll("[\\[,\\]]","\n"));
    }

    private void addGoods(){
        String name;
        int amount;
        double price;

        try{
            System.out.print("Please Enter Goods Name:");
            name = userScan.next();
            System.out.print("Please Add Goods' amount:");
            amount = userScan.nextInt();
            System.out.print("Please Add Goods' price:");
            price = userScan.nextDouble();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/goodsAdd.php?name=" + name + "&amount=" + amount + "&price=" + price));
        }catch (Exception adminAddGoods){
            System.out.println(adminAddGoods.getMessage());
        }
    }

    private void deleteGoods(){
        int id;
        try {
            System.out.print("Please Enter Goods ID:");
            id = userScan.nextInt();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/goodsDelete.php?goodsID=" + id));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    protected void listGoods(){
        reqJOSN("http://happybirthdaytolinan.site/phpN/goods.php");
    }

    protected void ListAllSoldGoods(){
        reqJOSN("http://happybirthdaytolinan.site/phpN/userGoodsAll.php");
    }

    private void deleteSoldGoodsByID(){
        int id;
        try {
            System.out.print("Please Enter GoodsSold ID:");
            id = userScan.nextInt();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/soldGoodsDeleteByID.php?goodsSoldID=" + id));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    private void deleteSoldGoodsByName(){
        String name;
        try {
            System.out.print("Please Enter GoodsSold Name:");
            name = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/soldGoodsDeleteByName.php?goodsName=" + name));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    private void deleteSoldGoodsByAccount(){
        String account;
        try {
            System.out.print("Please Enter GoodsSold Account:");
            account = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/soldGoodsDeleteByAccount.php?goodsAccount=" + account));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    protected void listUser(){
        reqJOSN("http://happybirthdaytolinan.site/phpN/allUser.php");
    }

    protected void setVip(){
        String account;
        try {
            System.out.print("Please Enter account:");
            account = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/setVIP.php?account=" + account));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    public void userLogin()
    {
        while (true)
        {
            int option = 1;
            System.out.println("Welcome: Admin "+getAccount());
            System.out.print("Press  1:AddGoods  2:DeleteGoods  3:ListALLGoods  4:ListAllSoldGoods  5:DeleteSoldGoodsByID  \n  6:DeleteSoldGoodsByName  7:deleteSoldGoodsByAccount  8:ListUser  9:SetVip  else:back  \nPlease Enter:");
            try {
                option = userScan.nextInt();
                switch (option)
                {
                    case 1:addGoods(); break;
                    case 2:deleteGoods(); break;
                    case 3:listGoods(); break;
                    case 4:ListAllSoldGoods(); break;
                    case 5:deleteSoldGoodsByID(); break;
                    case 6:deleteSoldGoodsByName(); break;
                    case 7:deleteSoldGoodsByAccount(); break;
                    case 8:listUser(); break;
                    case 9:setVip(); break;
                    default:
                        return;
                }
            }catch (Exception exceptionCustomer){
                System.out.println(exceptionCustomer.getMessage());
                return;
            }
        }
    }
}

class UltimateAdmin extends Admin{
    UltimateAdmin(String acc,String pass){
        super(acc,pass);
    }

    Scanner userScan = new Scanner(System.in);

    private void chargeMoney(){
        String account;
        int amount;
        try {
            System.out.print("Please Enter Charge Account:");
            account = userScan.next();
            System.out.print("Please Enter Charge amount:");
            amount = userScan.nextInt();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/charge.php?account=" + account + "&chargeAmount=" + amount));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    private void setUser(){
        String account;
        try {
            System.out.print("Please Enter account:");
            account = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/setUser.php?account=" + account));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    private void setAdmin(){
        String account;
        try {
            System.out.print("Please Enter account:");
            account = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/setAdmin.php?account=" + account));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    private void listAccount(){
        reqJOSN("http://happybirthdaytolinan.site/phpN/allAccount.php");
    }

    private void deleteAccount(){
        String account;
        try {
            System.out.print("Please Enter deleteAccount:");
            account = userScan.next();
            System.out.println(http.request("http://happybirthdaytolinan.site/phpN/accountDelete.php?account=" + account));
        }catch (Exception adminDelGoodsEx){
            System.out.println(adminDelGoodsEx.getMessage());
        }
    }

    public void userLogin()
    {
        while (true)
        {
            int option = 1;
            System.out.println("Welcome: Ultimate Admin "+getAccount());
            System.out.print("Press  1:ChargeMoney  2:SetUser  3:SetVip  4:SetAdmin  \n  5:ListAccount  6:DeleteAccount  else:back  \nPlease Enter:");
            try {
                option = userScan.nextInt();
                switch (option)
                {
                    case 1:chargeMoney(); break;
                    case 2:setUser(); break;
                    case 3:setVip(); break;
                    case 4:setAdmin(); break;
                    case 5:listAccount(); break;
                    case 6:deleteAccount(); break;
                    default:
                        return;
                }
            }catch (Exception exceptionCustomer){
                System.out.println(exceptionCustomer.getMessage());
                return;
            }
        }
    }
}