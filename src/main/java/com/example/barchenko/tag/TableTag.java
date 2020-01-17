package com.example.barchenko.tag;

import com.example.barchenko.dao.impl.JdbcRoomsDao;
import com.example.barchenko.dao.impl.JdbcStudentsDao;
import com.example.barchenko.entity.Room;
import com.example.barchenko.entity.Student;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class TableTag extends TagSupport {

    private String empty;

    private List<Student> students;
    private List<Room> rooms;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int doStartTag() throws JspException {

        students = new JdbcStudentsDao().findAllStudents();
        rooms = new JdbcRoomsDao().findAllRooms();
        pageContext.getRequest().getServerName();

        JspWriter out = pageContext.getOut();

        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"" + empty + "\">");
        sb.append("<tr><td><h3>");
        sb.append("First Name");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Last Name");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Age");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Gender");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Group");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Room");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Actions");
        sb.append("</h3></td></tr>");
        for (Student student : students) {
            sb.append("<tr><td>");
            sb.append(student.getFname());
            sb.append("</td><td>");
            sb.append(student.getLname());
            sb.append("</td><td>");
            sb.append(student.getAge());
            sb.append("</td><td>");
            sb.append(student.getGender());
            sb.append("</td><td>");
            sb.append(student.getGroup());
            sb.append("</td><td>");
            sb.append(student.getRoom().getNumber());
            sb.append("</td><td>");
            sb.append("<a href=\""
                    + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath() + "/update?id="
                    + student.getId() + "\" >Edit</a>");
            sb.append("  <a href=\""
                    + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath() + "/delete?id="
                    + student.getId()
                    + "\" onclick=\"return confirm('Are you sure?');"
                    + "\">Delete</a>");
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        try {
            out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }

}

