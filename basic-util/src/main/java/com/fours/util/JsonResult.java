package com.fours.util;

public class JsonResult {
    private boolean success=true;
    private String msg="你成功了，真棒";

    public static JsonResult getJsonResult(){
        return new JsonResult();
    }

    public static JsonResult error(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMsg(msg);
        return jsonResult;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
