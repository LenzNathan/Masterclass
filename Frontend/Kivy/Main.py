import sys
import threading
import webbrowser
from contextlib import nullcontext
from kivy.uix.boxlayout import BoxLayout
from LogedIn import LogedIn
#from importlib.metadata import pass_none
from kivy.modules.recorder import on_recorder_key
from kivy.uix.screenmanager import ScreenManager, Screen
from kivy.core.window import Window
from kivymd.app import MDApp
from kivymd.uix.label import MDLabel
from kivymd.uix.button import MDRaisedButton
from kivy.lang import Builder
from kivy.uix.floatlayout import FloatLayout
from kivymd.uix.textfield import MDTextField
from kivy.graphics import *
from kivy.uix.image import Image
from pygments.styles.dracula import background

##layout is 1 x 1 (0,0) is buttom left
##rights belong to ASUNAxoxo
##--------------------------
##KivyMD project
from Start  import Start
from Register import Register
from LogedIn import LogedIn
from Raum import Raum
from Workscreen import Workscreen
import webbrowser
import requests

class MainApp(MDApp):
    _accessToken = ""
    _screen_manager = ScreenManager()
    _userinfo = ""
    def build(self):
        screen_manager = self._screen_manager

        #checken ob ein Argument beim scriptaufruf mitgegeben wurde
        if((len(sys.argv) > 1)and(type(sys.argv[1]) is str)):
            self._accessToken = sys.argv[1]
        #wenn kein access token übergeben wurde beim scriptaufruf, dann wird der user über keycloak eingeloggt
        if self._accessToken == "":
            self.loginUser()

        self.getUserInfo()
        self._screen_manager.add_widget(Workscreen(self._userinfo))

        return screen_manager

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
        self._userinfo = response.json()
        print(self._userinfo)

#run
if __name__ == "__main__":
    MainApp().run()