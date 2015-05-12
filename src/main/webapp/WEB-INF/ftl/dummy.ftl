<!DOCTYPE html>
<html>
<body>
<table border="1">
    <thead>
    <tr>
        <th colspan="3"><h3>Tweets:</h3></th>
    </tr>
    </thead>

    <#foreach tweet in tweets>
        <tr>
            <td>${tweet.campaignName}</td>
            <td>${tweet.rank}</td>
            <td>${tweet.tweet.text}"</td>
        </tr>
    </#foreach>
</table>
</body>
</html>