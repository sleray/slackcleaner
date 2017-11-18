# slackcleaner

Welcome to slack cleaner batch !

If your have a channel full of messages in slack that you want to clear, slack free plan doesn't offer a handy solution.

With their api, we can loop on all the messages to remove them.

Unfortunately, we only can call api once each second to avoid a http rate limit exception. So run the batch, get a coffee and log in slack to see messages disappearing slowly :)

## Prerequisites
there is two mandatory arguments for the main method of the launcher : **token** and **channelId**

# Get your OAuth token
go in your slack menu > manage Apps > build 
Create a slack-app then click on permissions, select following scopes :

- channels:history
- chat:write:user

Save changes then click on Install App to workspace and authorise your own app

Copy / paste your OAuth Access Token. Keep it safe, do not write it on a post-it :)

it should look like this :

```
xoxp-123456789123-123456789123-123456789123-3f2b2d5d123f5d1234b84bf1234efa70
```

# Get your channel id
logged on your slack account, 
browse https://api.slack.com/methods/channels.list/test
click on test method, look at the Json returned and copy the channel's id you want to clean
it should look like this :

```
C3BX5K12B
```

## Contributions
As i'm quite new to open source sharing, please feel free to comment code, do pull requests or just ask for new features

