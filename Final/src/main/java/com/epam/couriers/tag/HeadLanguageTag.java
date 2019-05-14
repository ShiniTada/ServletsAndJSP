package com.epam.couriers.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HeadLanguageTag  extends TagSupport {

    private String language;
    private final String russian = "Русский";
    private final String english= "English";

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String s1 = "<div class=\"w3-right w3-dropdown-hover\">\n" +
                    "              <button class=\" w3-button w3-padding-large\">" + language + "</button>\n" +
                    "              <div class=\"w3-dropdown-content w3-bar-block w3-card-4\">\n" +
                    "                  <form action=\"Controller\" method=\"get\">\n" +
                    "                      <input type=\"hidden\" name=\"command\" value=\"set-locale\"/>\n" +
                    "                      <button class=\"w3-bar-item w3-text-teal\" value=\"en\" name=\"locale\" id=\"locale_en\">"+ english +"</button>\n" +
                    "                  </form>\n" +
                    "                  <form action=\"Controller\" method=\"get\">\n" +
                    "                      <input type=\"hidden\" name=\"command\" value=\"set-locale\"/>\n" +
                    "                      <button class=\"w3-bar-item w3-text-teal\" value=\"ru\" name=\"locale\" id=\"locale_ru\">"+ russian +"</button>\n" +
                    "                  </form>\n" +
                    "              </div>\n" +
                    "              </form>\n" +
                    "          </div>\n";

            pageContext.getOut().write(s1);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
