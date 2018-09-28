# LunatechTweets
This project intends to provide a bridge between tweets concerning Lunatech and Lunatech employees with access to Slack.
A list of hashtags has to be configured. The tweets that contain the configured hashtags will be forward to a Slack channel (also configurable).
The tweets marked as re-tweets will not be taken into consideration to avoid spam.

## Usage
Please configure the following keys:

```bash
export TWITTER_CONSUMER_TOKEN_KEY='my-consumer-key'
export TWITTER_CONSUMER_TOKEN_SECRET='my-consumer-secret'
export TWITTER_ACCESS_TOKEN_KEY='my-access-key'
export TWITTER_ACCESS_TOKEN_SECRET='my-access-secret'

export SLACK_WEBHOOK_URL="https://hooks.slack.com/services/organization/channel"
export SLACK_TRACKED_HASHTAGS1="LunatechTest"
export SLACK_TRACKED_HASHTAGS2="LunatechTest"
export SLACK_TRACKED_HASHTAGS3="LunatechTest"

export HOST="0.0.0.0"
export PORT="8080"

```



