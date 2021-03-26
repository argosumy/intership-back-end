<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Comment added template</title>
        ​
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        ​
        <link
            href="http://fonts.googleapis.com/css?family=Roboto"
            rel="stylesheet"
            type="text/css"
        />
        <style>
            body {
                font-family: "Roboto", sans-serif;
                font-size: 24px;
            }
            img {
                display: block;
                max-width: 100%;
                height: auto;
                border-style: none;
            }
            .container {
                max-width: 600px;
                margin: 0 auto;
            }
            .unsubscribe {
                font-size: 10px;
                color: gray;
            }
            .profile-link {
                color: #10293f;
                font-size: 12px;
            }
        </style>
    </head>
    <body style="margin: 0; padding: 0">
        <div class="container">
            <p class="description">
                User <#if writer??>${writer}</#if>
                post new comment <#if objectLink??> in <a href="${objectLink}">Your ad</a></#if>
            </p>

            <p class="subscribe">
                You received this message because you are subscribed to the
                Google Groups "Baraholka" group. To unsubscribe from this group
                and stop receiving emails from it, you can turn off it in
                <a class="profile-link" href="${profileLink}">Your profile</a> settings
            </p>
        </div>
        ​ ​
    </body>
</html>
