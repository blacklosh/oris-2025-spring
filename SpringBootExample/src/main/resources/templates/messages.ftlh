<html>

    <body>

        <h1>Messages:</h1>

        <#list messages as message>

            ${message.id} : ${message.text}

            <br/>

        </#list>

    </body>

</html>