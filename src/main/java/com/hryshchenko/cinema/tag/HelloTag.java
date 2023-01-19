package com.hryshchenko.cinema.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport {
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        String hello;
        try {
            JspWriter out = pageContext.getOut();
            if (role != null && !role.isEmpty()){
                hello = "Welcome, " + role;
                out.write(hello);
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
