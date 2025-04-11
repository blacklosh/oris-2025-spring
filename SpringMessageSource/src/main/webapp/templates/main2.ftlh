<html>

    <head>
        <title>
            <@spring.message "TITLE"/>
        </title>
    </head>

    <body>

        <h1>
            <@spring.message "TITLE"/>
        </h1>

        <h2>
            <@spring.message "DESC"/>
        </h2>

        <h3>
            <@spring.messageArgs "GREETING", [username]/>
        </h3>

        <a href="?lang=en">EN</a>
        <br/>
        <a href="?lang=ru">RU</a>

    </body>

</html>