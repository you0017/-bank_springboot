package com.yc.controller;


import com.yc.bean.Accounts;
import com.yc.bean.JsonModel;
import com.yc.mapper.AccountMapper;
import com.yc.service.BankBiz;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Tag(name = "控制器：测试Swagger3", description = "描述：测试Swagger3")
@RestController
@RequestMapping("/account.action")
//@CrossOrigin(origins = "*")
public class BankController {

    @Autowired
    private BankBiz bankBiz;
    @Autowired
    private AccountMapper accountMapper;

    @Operation(summary = "测试Swagger3注解方法Get")
    @Parameters({@Parameter(name = "id",description = "编码"),
            @Parameter(name = "headerValue",description = "header传送内容")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "400", description = "请求参数没填好"),
            @ApiResponse(responseCode = "401", description = "没有权限"),
            @ApiResponse(responseCode = "403", description = "禁止访问"),
            @ApiResponse(responseCode = "404", description = "请求路径没有或页面跳转路径不对")
    })
    @GetMapping("/getEmail")
    public JsonModel email (@RequestParam(value = "accountId",defaultValue = "0") Integer accountId){
        if (accountId == 0){
            JsonModel jm = new JsonModel();
            jm.setCode(0);
            jm.setObj("accountId不能为空");
            return jm;
        }

        Accounts account = bankBiz.email(accountId);
        if (account == null){
            JsonModel jm = new JsonModel();
            jm.setCode(0);
            jm.setObj("accountId不存在");
            return jm;
        }
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
