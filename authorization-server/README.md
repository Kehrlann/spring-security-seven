# Authorization Server


## Get token


```shell
echo "📥 Token response"
http :9000/oauth2/token grant_type=client_credentials scope=client.create --auth default-client:default-secret -f

export DEMO_TOKEN=$(http :9000/oauth2/token grant_type=client_credentials scope=client.create --auth default-client:default-secret -f | jq -r ".access_token")

echo "🍪 Token contents"
echo $DEMO_TOKEN | step crypto jwt inspect --insecure | jq
```

## Register client

Only once!!!

```shell
http :9000/oauth2/register "Authorization: Bearer $DEMO_TOKEN" -v grant_types:='["client_credentials"]'
```