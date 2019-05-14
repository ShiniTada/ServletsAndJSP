package com.epam.couriers.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HeadNameTag extends TagSupport {

    private String role;
    private String login;
    private String settings;
    private String sign_out;
    private final String admin = "admin";
    private final String courier = "courier";
    private final String customer = "customer";

    public void setRole(String role) {
        this.role = role;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public void setSign_out(String sign_out){
        this.sign_out = sign_out;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String s1;
            if(admin.equalsIgnoreCase(role) || courier.equalsIgnoreCase(role) || customer.equalsIgnoreCase(role)) {
                s1 = " <div class=\"w3-right w3-dropdown-hover\">\n" +
                        " \n" +
                        "          <button class=\" w3-button w3-padding-large\">" + login + "</button>\n" +
                        "         <div class=\"w3-dropdown-content w3-bar-block w3-card-4\">\n" +
                        "             <a href=\"#\" class=\"w3-bar-item w3-button w3-text-teal \">" + settings + "</a>\n" +
                        "             <form action=\"Controller\" method=\"post\">\n" +
                        "                  <input type=\"hidden\" name=\"command\" value=\"sign-out\"/>\n" +
                        "                 <button class=\"w3-button w3-right w3-bar-item   w3-text-teal w3-white w3-hover-light-grey w3-padding-large\" >" + sign_out + "</button>\n" +
                        "              </form>\n" +
                        "         </div>\n" +
                        "     </div>\n";
            } else {
                s1 = " ";
            }
            pageContext.getOut().write(s1);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
            return SKIP_BODY;
    }

    @Override
    public int doEndTag()  {
        return EVAL_PAGE;
    }
}
