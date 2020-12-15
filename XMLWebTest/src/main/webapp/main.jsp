<%@ page import="com.developersweb.entities.Developers" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #developers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #developers td, #developers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #developers tr:nth-child(even){background-color: #f2f2f2;}

        #developers tr:hover {background-color: #ddd;}

        #developers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #52B792;
            color: white;
        }
        #unique
        {
            background-color: #52B792;
            color: white;
        }
    </style>
    <title>Developers list</title>
    <table border=1 width=100% cellpadding=5 id="developers">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Position</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%
                List <Developers> devList = (List<Developers>) request.getAttribute("developers");
                int count = devList.size();
                int ageCount = 0;
                for (int i = 0; i<count; i++)
                {
                    out.print("<tr><td>"+devList.get(i).getName()+"</td>"+
                            "<td>"+devList.get(i).getSurname()+"</td>" +
                            "<td>"+devList.get(i).getAge()+"</td>"+
                            "<td>"+devList.get(i).getPosition()+"</td></tr>");
                    ageCount+=Integer.parseInt(devList.get(i).getAge());

                }
                out.print("<tr><td id='unique'>Statistics</td></tr><tr><td> Amount of employees</td><td>"+ count+ "</td></tr>");
                out.print("<tr><td> Average employees age</td><td>"+ ageCount/count + "</td></tr>");
            %>
        </tr>
        </tbody>
    </table>
</head>
<body>

</body>
</html>
