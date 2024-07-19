import requests
import json
import os
from dotenv import load_dotenv

fileName = "sign_data.csv"
loginUrl = "http://localhost:8080/login"
postUrl = "http://localhost:8080/api/traffic-sign-observations"


class AuthDto:
    def __init__(self, userName, password):
        self.userName = userName
        self.password = password


class TrafficSignObservationDto:
    def __init__(self, id, latitude, longitude, heading, type, value):
        self.id = id
        self.latitude = latitude
        self.longitude = longitude
        self.heading = heading
        self.type = type
        self.value = value


if __name__ == '__main__':
    load_dotenv()

    user = os.getenv("DEV_USER")
    password = os.getenv("DEV_PASS")

    authDto = AuthDto(user, password)

    authJson = json.dumps(authDto.__dict__)

    headers = {'Content-Type': 'application/json'}

    response = requests.post(loginUrl, json=authJson, headers=headers)

    token = response.text

    with open(fileName) as f:
        fileLines = f.readlines()

    for fileLine in fileLines:
        split = fileLine.split(",")
        observationDto = TrafficSignObservationDto(0, split[0], split[1], split[2], split[3])
        if len(split) > 4:
            observationDto.value = split[4]
        observationJson = json.dumps(observationDto.__dict__)
        requests.post(postUrl, json=observationJson, headers={"Authorization": token})
