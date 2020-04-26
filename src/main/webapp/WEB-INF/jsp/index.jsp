<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringBoot</title>
    <style>
        table {
            font-family: "Times New Roman", sans-serif;
            width: 100%;
            margin-top: 20px;
            border-style: dashed;
            border-color: black;
        }

        td{
            text-align: center;
            padding: 8px;
            width: 150px;
        }

        th {
            text-align: center;
            text-decoration: underline;
            padding: 8px;
            width: 150px;
        }

        div {
            background-color: lightgrey;
            width: 100%;
            border-style: dashed;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: lightgrey;
        }

        img {
            width: 20%;
        }

        input {
            background-color: skyblue;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: left;
            margin: 5px 5px 10px 5px;
        }

    </style>
</head>
<body>

<div>
<h2>Random Quotes</h2>
</div>

<textarea cols=" 46" rows="7">
    ${quote}</textarea>



<form action="/randomquotes" method="GET">
    <input type="submit" value="Load"/>
</form>

<form action="/" method="GET">
    <input type="submit" value="Refresh"/>
</form>



<table>
    <tr>
        <th>Quote ID</th>
        <th>Quote Message</th>
        <th>Quote Author</th>
        <th>Action</th>
    </tr>
    <c:forEach var = "listitem" items = "${quotesrandom}">
        <tr>
            <td>${listitem.getQuoteId()}</td>
            <td>${listitem.getQuoteMessage()}</td>
            <td>${listitem.getQuoteAuthor()}</td>
            <td>
                <a href="/delete/${listitem.getId()}"><img src="../../img/delete.jpg" alt="delete_image" ></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>