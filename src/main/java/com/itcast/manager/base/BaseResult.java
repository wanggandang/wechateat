package com.itcast.manager.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.itcast.manager.utils.DateUtil;
import com.itcast.manager.utils.ReflectUtil;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;


/**
 * Created by lucifer.chan on 2017/5/3.
 * 消息的包装类
 */
public class BaseResult {
    public final static String SUCCESS_MSG = "ok";
    public final static String FAILED_MSG = "failed";
    public final static String SUCCESS_CODE = "0";

    private Map<String, Object> all = new HashMap<>();

    private Map<String, Object> ret = new HashMap<>();

    protected String errcode;
    protected String errmsg;

    public BaseResult(){
        this.errcode = SUCCESS_CODE;
        this.errmsg = SUCCESS_MSG;
    }

    public BaseResult(String errcode){
        this.errcode = errcode;
        this.errmsg = SUCCESS_CODE.equals(errcode) ? SUCCESS_MSG : FAILED_MSG;
    }

    public BaseResult(Integer errcode){
        this(errcode+"");
    }

    public BaseResult(Object errcode, String errmsg){
        this.errcode = null == errcode ? "0" : errcode.toString();
        this.errmsg = errmsg;
    }


    public BaseResult put(String key, Object value){
        ret.put(key, value);
        return this;
    }

    public BaseResult add(Map<String, Object> map){
        map.forEach(this::put);
        return this;
    }

    @SuppressWarnings("unchecked")
    public BaseResult addPojo(Object pojo){
        return addPojo(pojo, null);
    }

    @SuppressWarnings("unchecked")
    public BaseResult addPojo(Object pojo, String dateFormat){
        if(null == dateFormat) dateFormat = "yyyy-MM-dd";
        return add(pojo2Map(pojo, dateFormat));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> pojo2Map(Object pojo, String dateFormat){
        if(pojo instanceof Map)
            return (Map<String, Object>) pojo;

        List<Field> fields = ReflectUtil.getAllFieldsWithoutIgnored(pojo);
        Map<String, Object> map = new HashMap<>();
        for(Field field : fields){
            if(!field.isAnnotationPresent(JsonIgnore.class)){
                Object value = ReflectUtil.getValueByGetter(field, pojo);
                String fieldName = field.getName();
                if(null == value){
                    map.put(fieldName, "");
                }else if(value instanceof Date && StringUtils.hasLength(dateFormat))
                    map.put(fieldName, DateUtil.getDateString((Date) value, dateFormat));
                else if(value instanceof Iterable){
                    List list = new ArrayList();
                    ((Iterable) value).forEach(obj->list.add(pojo2Map(obj, dateFormat)));
                    map.put(fieldName, list);
                }else{
                    map.put(fieldName, value);
                }
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public BaseResult addList(Iterable it, String dateFormat){
        return addList("list", it, dateFormat);
    }

    @SuppressWarnings("unchecked")
    public BaseResult addList(Iterable it){
        return addList(it, null);
    }

    @SuppressWarnings("unchecked")
    public BaseResult addList(String key, Iterable it){
        return addList(key, it, null);
    }

    @SuppressWarnings("unchecked")
    public BaseResult addList(String key, Iterable it, String dateFormat){
        List list = new ArrayList();
        it.forEach(obj->{
            if(obj instanceof Number || obj instanceof String)
                list.add(obj);
            else if(obj instanceof Date && StringUtils.hasLength(dateFormat))
                list.add(DateUtil.getDateString((Date) obj, dateFormat));
            else
                list.add(pojo2Map(obj, dateFormat));
        });
        ret.put(key, list);
        return this;
    }

    public String toString(){
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject(){
        all.put("errcode", getErrcode());
        all.put("errmsg", errmsg);
        all.put("success", isSuccess());
        all.put("ret", ret);
        return JSONObject.fromObject(all);
    }

    public Object getErrcode(){
        try{
            return Integer.parseInt(errcode);
        }catch(NumberFormatException e){
            return errcode;
        }
    }

    public BaseResult setErrcode(String errcode){
        this.errcode = errcode;
        return this;
    }


    public String getErrmsg(){
        return errmsg;
    }


    public BaseResult setErrmsg(String errmsg){
        this.errmsg = errmsg;
        return this;
    }

    public boolean isSuccess(){
        return SUCCESS_CODE.equals(errcode);
    }

    @JsonInclude(Include.NON_EMPTY)
    public JSONObject getRet(){
        return JSONObject.fromObject(ret);
    }

    public BaseResult setRet(JSONObject ret){
        this.ret = ret;
        return this;
    }

//    public static void main(String[] ags){
//        Dto dto = new Dto();
//        dto.setConsName("dedededwedwedwedwed");
//        dto.setDxje("aaaa");
//        dto.setDate(null);
//        BaseResult br = new BaseResult();
//        br.addPojo(dto);
//        System.out.println(br);
//    }
}
