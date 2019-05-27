package com.epam.couriers.tag;

import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {

    /**
     * The name of the command to correctly form the request
     */
    private String command;

    /**
     * Total count of entities.
     */
    private int total;

    /**
     * Limit to show entities on page.
     */
    private int limit;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        if (limit >= total) {
            return SKIP_BODY;
        }
        int pageParam = 0;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Paginator paginator = new Paginator(limit, total, command);

        if (request.getParameter(GeneralConstant.PAGE_NUMBER) != null) {
            pageParam = Integer.parseInt(request.getParameter(GeneralConstant.PAGE_NUMBER));
        } else {
            pageParam = (Integer) request.getSession().getAttribute(GeneralConstant.PAGE_NUMBER);
        }

        try {
            if (pageParam != 0) {
                paginator.setPageNumber(pageParam);
            }

            JspWriter out = pageContext.getOut();
            out.write(paginator.generate());

        } catch (IOException e) {
            throw new JspException("Write output error", e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
