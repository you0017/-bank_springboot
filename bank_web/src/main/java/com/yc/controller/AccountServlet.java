package com.yc.controller;


import com.yc.bean.Accounts;
import com.yc.bean.JsonModel;
import com.yc.mapper.AccountMapper;
import com.yc.service.BankBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account.action")
public class AccountServlet {

    @Autowired
    private BankBiz bankBiz;
    @Autowired
    private AccountMapper accountMapper;
    @GetMapping("/getEmail")
    public JsonModel email (@RequestParam(value = "accountId",defaultValue = "0") Integer accountId){
        if (accountId == 0){
            JsonModel jm = new JsonModel();
            jm.setCode(0);
            jm.setObj("accountId不能为空");
            return jm;
        }

        Accounts account = bankBiz.email(accountId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(account);
        return jm;
    }

    @PostMapping("/deposit")
    protected JsonModel deposit(Integer accountId, Double balance) {
        Accounts a = bankBiz.deposit(accountId, balance);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        return jm;
    }

    @PostMapping("/withdraw")
    public JsonModel withdraw(Integer accountId, Double balance) {
        Accounts a = bankBiz.withdraw(accountId, balance);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        return jm;
    }


    @PostMapping("/openAccount")
    public JsonModel openAccount(@RequestBody Accounts account) {
        Accounts a = bankBiz.openAccount( account);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        return jm;
    }

    @GetMapping("/query")
    public JsonModel query(@RequestParam(value = "accountId",defaultValue = "0") Integer accountId) {

        if (accountId == 0){
            JsonModel jm = new JsonModel();
            jm.setCode(0);
            jm.setObj("accountId不能为空");
            return jm;
        }

        Accounts a = bankBiz.findAccount(accountId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        return jm;
    }

    @PostMapping("/transfer")
    public JsonModel transfer(Integer accountId, Double balance, Integer toAccountId){

        Accounts a = bankBiz.transfer( accountId,balance,toAccountId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        return jm;
    }
}
