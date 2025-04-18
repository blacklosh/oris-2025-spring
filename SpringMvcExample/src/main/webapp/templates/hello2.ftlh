<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>Hello world - 2!</h1>

    <table>
        <tr>
            <td>ELEMENT</td>
        </tr>
        <#list objects as object>
            <tr>
                <td>
                    ${object}
                </td>
            </tr>
        </#list>
    </table>
<hr>

<form action="/hello" method="post">
    <input name="data"/>
    <input type="submit"/>
</form>

</body>
</html>