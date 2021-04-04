<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>New ad was added template</title>
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
            .main-image {
                margin-bottom: 40px;
            }
            .more-images {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }
            .small-image {
                width: 90px;
                height: 100px;
            }
            .btn {
                display: inline-block;
                color: #fff;
                background-color: #2eaae1;
                text-decoration: none;
                padding: 10px 20px;
                border-radius: 5px;
                margin-bottom: 15px;
                margin-right: 15px;
                font-size: 14px;
                font-weight: 500;
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
            <div class="image-wrapper">
                <div class="main-image">
                    <img
                        class="big-image"
                        src="${mainImage}"
                        alt=""
                    />
                </div>
                <div class="more-images">
                    <#list images as image>
                    <img
                        class="small-image"
                        src="${image}"
                        alt=""
                    />
                    </#list>
                </div>
            </div>
            <p class="description">
                ${description}
            </p>

            <a class="btn" href="${objectLink}">View Details</a>
            <a class="btn" href="mailto:${mailTo}?subject=RE: ${title}`"
                >Contact seller</a
            >

            <p class="unsubscribe">
                You received this message because you are subscribed to the
                Google Groups "Baraholka" group. To unsubscribe from this group
                and stop receiving emails from it, you can turn off it in
                <a class="profile-link" href="${profileLink}">Your profile</a> settings
            </p>
        </div>
        ​ ​
    </body>
</html>
