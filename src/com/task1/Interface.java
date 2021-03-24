package com.task1;

public class Interface {
    Http httpInterface = new Http();
    public void welcome(){
        String noticeReq = "http://happybirthdaytolinan.site/phpN/notice.php?id=1";
        System.out.println(httpInterface.request(noticeReq));
    }
    public void login(String acc,String pass){
        if (acc.length() < 6 || pass.length() < 6)
        {
            System.out.println("login FAILED, the length of account or password must more than 6!" );
        }
        else {
            String loginReq = "http://happybirthdaytolinan.site/phpN/login.php?name=";
            loginReq = loginReq + acc + "&pass=" + pass;
            String requestData = httpInterface.request(loginReq);
            System.out.println(requestData);
            if(requestData.contains("Ultimate_Admin")){
                UltimateAdmin ultimateAdmin = new UltimateAdmin(acc,pass);
                ultimateAdmin.userLogin();
            }else if(requestData.contains("Admin")){
                Admin admin = new Admin(acc,pass);
                admin.userLogin();
            }else if(requestData.contains("VIP")){
                VIPCustomer vipCustomer = new VIPCustomer(acc,pass);
                vipCustomer.userLogin();
            }else if(requestData.contains("welcome!")){
                Customer customer = new Customer(acc,pass);
                customer.userLogin();
            }
        }
    }
    public void register(String acc,String pass){
        if (acc.length() < 6 || pass.length() < 6)
        {
            System.out.println("register FAILED, the length of account or password must more than 6!");
        }
        else {
            String regReq = "http://happybirthdaytolinan.site/phpN/reg.php?name=";
            regReq = regReq + acc + "&pass=" + pass;
            System.out.println(httpInterface.request(regReq));
        }
    }
}
