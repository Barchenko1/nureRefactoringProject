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

public class TableRooms extends TagSupport {

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
        sb.append("Number");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Capacity");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Flour");
        sb.append("</h3></td>");
        sb.append("<td><h3>");
        sb.append("Actions");
        sb.append("</h3></td></tr>");
        for (Room room : rooms) {
            sb.append("<tr><td>");
            sb.append(room.getNumber());
            sb.append("</td><td>");
            sb.append(room.getCapacity());
            sb.append("</td><td>");
            sb.append(room.getFlour());
            sb.append("</td><td>");
            sb.append("<a href=\""
                    + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath() + "/updateRoom?id="
                    + room.getId() + "\" >Edit</a>");
            sb.append("  <a href=\""
                    + ((HttpServletRequest) pageContext
                    .getRequest()).getContextPath() + "/deleteRoom?id="
                    + room.getId()
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

