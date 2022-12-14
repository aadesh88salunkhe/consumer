# Getting Started

Start consumer service after producer service to push data into db

### Curl to execute get user tweet rest api by labels

curl --location --request GET 'http://localhost:8088/tweets?labels=bollywood,information,tourism' \
--header 'username: TestUser1' \
--header 'password: abcd'

ResponseJSon :

{
"userId": 1,
"labels": [
{
"label": "bollywood",
"tweets": [
{
"hashtag": "fashion",
"tweets": [
"fashion1 Test tweet",
"fashion1 Test tweet"
]
},
{
"hashtag": "photography",
"tweets": [
"photography1 Test tweet",
"photography2 Test tweet",
"photography3 Test tweet",
"photography4 Test tweet",
"photography5 Test tweet",
"photography6 Test tweet",
"photography6 Test tweet"
]
},
{
"hashtag": "love",
"tweets": [
"love1 Test tweet",
"love2 Test tweet",
"love3 Test tweet",
"love4 Test tweet"
]
},
{
"hashtag": "movie",
"tweets": [
"movie Test tweet"
]
},
{
"hashtag": "model",
"tweets": [
"model1 Test tweet",
"model2 Test tweet"
]
},
{
"hashtag": "marvel",
"tweets": [
"marvel Test tweet"
]
}
]
},
{
"label": "information",
"tweets": [
{
"hashtag": "model",
"tweets": [
"model1 Test tweet",
"model2 Test tweet"
]
}
]
},
{
"label": "tourism",
"tweets": [
{
"hashtag": "tourism",
"tweets": [
"tourism  Test tweet",
"tourism1 Test tweet",
"tourism2 Test tweet",
"tourism3 Test tweet"
]
},
{
"hashtag": "travel",
"tweets": [
"travel1 Test tweet",
"travel2 Test tweet",
"travel1 Test tweet"
]
},
{
"hashtag": "tourist",
"tweets": [
"tourist5 Test tweet",
"tourist6 Test tweet",
"tourist7 Test tweet",
"tourist9 Test tweet"
]
},
{
"hashtag": "photography",
"tweets": [
"photography1 Test tweet",
"photography2 Test tweet",
"photography3 Test tweet",
"photography4 Test tweet",
"photography5 Test tweet",
"photography6 Test tweet",
"photography6 Test tweet"
]
}
]
}
]
}