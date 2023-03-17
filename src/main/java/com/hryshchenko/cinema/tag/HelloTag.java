package com.hryshchenko.cinema.tag;

import com.hryshchenko.cinema.controller.commands.CommandUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class HelloTag extends TagSupport {
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        String hello;

        HttpSession session = pageContext.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", CommandUtils.getLocale(session));

        try {
            JspWriter out = pageContext.getOut();
            if (role != null && !role.isEmpty()){
                hello = bundle.getString("hello") + " " + role;
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
