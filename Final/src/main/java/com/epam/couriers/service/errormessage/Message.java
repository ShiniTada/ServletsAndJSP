package com.epam.couriers.service.errormessage;

public class Message {
   private  static Message messageError= new Message();
    private String message;

    public static Message getInstanse(){
        return messageError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
