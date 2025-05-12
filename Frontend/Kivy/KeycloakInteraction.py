import sys
import webbrowser
import requests


class KeycloakUserInteraction():
    _accessToken = ""
    _userinfo = ""

    def __init__(self):
        # checken ob ein Argument beim scriptaufruf mitgegeben wurde
        if ((len(sys.argv) > 1) and (type(sys.argv[1]) is str)):
            self._accessToken = sys.argv[1]
        # wenn kein access token übergeben wurde beim scriptaufruf, dann wird der user über keycloak eingeloggt
        if self._accessToken == "":
            self.loginUser()

    def loginUser(self):
        #url bestandteile
        base_url = "http://localhost:8180/realms/stundenplanApp/protocol/openid-connect/auth"
        response_type = "code"
        client_id = "kivyClient"
        redirect_uri = "http://localhost:5001/authenticationKey"
        scope = "openid"
        #url zusammensetzen
        auth_url = f"{base_url}?response_type={response_type}&client_id={client_id}&redirect_uri={redirect_uri}&scope={scope}"
        #im browser öffnen
        webbrowser.open(auth_url)
        #python script wird geschlossen (es wird ja nicht mehr gebraucht)
        quit()

    def getUserInfo(self):
        #url bestandteile
        base_url = "http://localhost:8180/realms/stundenplanApp/protocol/openid-connect/userinfo"
        Headers = {"Content":"application/x-www-form-urlencoded", "Authorization":"Bearer "+self._accessToken}
        response = requests.get(base_url, headers=Headers)
        return response.json()