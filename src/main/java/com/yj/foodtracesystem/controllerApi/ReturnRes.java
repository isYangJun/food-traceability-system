package com.yj.foodtracesystem.controllerApi;

import com.yj.foodtracesystem.exception.BaseException;
import com.yj.foodtracesystem.model.TempModel.ProductPara;
import com.yj.foodtracesystem.unuserful.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.Future;

@RestController   //recontroller=controller+responebody
@RequestMapping("/test")
public class ReturnRes {
    private static final Log log = LogFactory.getLog(ReturnRes.class);
    private static final Logger logger = LoggerFactory.getLogger(ReturnRes.class);
    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/person")
    @ResponseBody
    public JsonResult returnPerson(){
        Person p=new Person();
        p.setName("p1");
        p.setSex("female");
        p.setPass("123456");
        p.setBirthday(new Date());
        return JsonResult.ok(p);
    }

    @GetMapping("/pe")
    @ResponseBody
    public Result<Person> returnP()throws Exception{
        Result<Person>personResult=new Result<Person>();

        Person p=new Person();
        p.setName("p1");
        p.setSex("female");
        p.setPass("123456");
        p.setBirthday(new Date());
        personResult.setCode(200);
        personResult.setMsg("success");
        personResult.setData(p);
        if(true)
            throw new Exception("密码不能为null");
        return personResult;
    }

    @GetMapping("/pes")
    @ResponseBody
    public Result<Person> returnPes()throws Exception{
        Result<Person>personResult=new Result<Person>();

        Person p=new Person();
        p.setName("p1");
        p.setSex("female");
        p.setPass("123456");
        p.setBirthday(new Date());
        personResult.setCode(200);
        personResult.setMsg("success");
        personResult.setData(p);
        if(true)
            throw new BaseException(ResultEnum.UNKNOW_ERROR);
        return ResultUtil.success(p);
    }

    /**
     * @Author:yangjun
     * @Description:異步任務
     * @Date: Created in 7:37 2018/7/31
     */
    @GetMapping("/asyTask")
    public String asyTask()throws Exception{
        long start=System.currentTimeMillis();
        Future<Boolean> a=asyncTask.doTask11();
        Future<Boolean> b=asyncTask.doTask22();
        Future<Boolean> c=asyncTask.doTask33();
        while(!a.isDone()||!b.isDone()||!c.isDone()){
            if(a.isDone()&&b.isDone()&&c.isDone())
                break;
        }
        long end=System.currentTimeMillis();
        System.out.println("toltal time: " +(end-start));
        logger.debug(String.valueOf(end-start));
        return "done";
    }
 /*   @Autowired
    TokenService tokenService;*/

    @GetMapping
    public String test() {
        logger.debug("now {}debug", "starting server");
        logger.info("now {}info", "starting server");
        logger.warn("now {}warn", "starting server");
        logger.error("now {}error", "starting server");

        log.debug("now {}hahadebug");
        log.info("now {}hahainfo");
        log.warn("now {}hahawarn");
        log.error("now {}hahaerror");

        logger.debug("dd");
        if (log.isTraceEnabled()) {
            log.trace("log test");
        }
        return "hello world, I yangJun";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable int id) {
        if (id == 1) {
            if (log.isInfoEnabled()) {
                log.info("id=" + String.valueOf(id));
            }

            return "hello";
        }
        if (id == 2) {
            if (log.isDebugEnabled()) {
                log.debug("id=" + String.valueOf(id));
            }
            return "world";
        } else {
            if (log.isErrorEnabled()) {
                log.fatal("输入的值错误");
            }
            throw new NullPointerException();
        }
    }

    @PostMapping
    public String addPro(@Valid @RequestBody ProductPara productPara) {
        if (log.isInfoEnabled()) {
            log.info("add info  test");
        }
        productPara.proName = "yang";
        productPara.proNum = "123";
        productPara.age = 1;
        return productPara.toString();
    }

    @PutMapping("/{id}")
    public String upPro(@PathVariable int id, @Valid @RequestBody ProductPara productPara, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (log.isErrorEnabled()) {
                log.error("参数信息不对");
            }
            return "参数信息不对";
        }
        if (log.isInfoEnabled()) {
            log.info("update info test");
        }
        if (id == 1) {
            productPara.proName = "yang";
            productPara.proNum = "123";
        }
        if (id == 2) {
            productPara.proName = "jun";
            productPara.proNum = "456";
        }
        return productPara.toString();
    }

    @PatchMapping("/{id}")
    public String upPartPro(@PathVariable int id, @RequestBody ProductPara productPara) {
        if (log.isInfoEnabled()) {
            log.info("update info test");
        }
        if (id == 1) {
            productPara.proName = "yang";
            productPara.proNum = "123";
        }
        if (id == 2) {
            productPara.proName = "jun";
            productPara.proNum = "456";
        }
        return productPara.toString();
    }


    @DeleteMapping("/{id}")
    public String delPro(@PathVariable int id, @RequestBody ProductPara productPara) {
        if (log.isInfoEnabled()) {
            log.info("update info test");
        }
        if (id == 1) {
            productPara.proName = "yg";
            productPara.proNum = "12e3";
        }
        if (id == 2) {
            productPara.proName = "jeun";
            productPara.proNum = "45e6";
        }
        return productPara.toString();
    }

   /* @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody ProductPara productPara){
        String userName=productPara.proName;
        String pass=productPara.proNum;
        HashMap<String,Object>result=new HashMap<>();
        String token =tokenService.login(userName,pass);
        if(token==null){
            result.put("message","invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

        }else {
            result.put("token",token);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }*/


}
