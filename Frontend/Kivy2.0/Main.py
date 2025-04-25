import sys
import threading
import webbrowser
from contextlib import nullcontext
from kivy.uix.boxlayout import BoxLayout
from LogedIn import LogedIn
from importlib.metadata import pass_none
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



        # Add the screens to the manager
        #screen_manager.add_widget(Start(name="start"))
        #screen_manager.add_widget(Register(name="register"))
        #screen_manager.add_widget(LogedIn(name="login"))

        #screen_manager.add_widget(Raum())
        #screen_manager.add_widget(Workscreen())

        return screen_manager

    def loginUser(self):
        #url bestandteile
        base_url = "http://localhost:8080/realms/stundenplanApp/protocol/openid-connect/auth"
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
        base_url = "http://localhost:8080/realms/stundenplanApp/protocol/openid-connect/userinfo"
        Headers = {"Content":"application/x-www-form-urlencoded", "Authorization":"Bearer "+self._accessToken}
        response = requests.get(base_url, headers=Headers)
        self._userinfo = response.json()
        print(self._userinfo)
        '''
            # root layout
            root = FloatLayout()

            # background
            with root.canvas.before:
                Color(0.2, 0.6, 0.2, 0.5)
                self.rect = Rectangle(size=root.size, pos=root.pos)

            root.bind(size=self.update_rect, pos=self.update_rect)

            # label
            labelUser = MDLabel(
                text="Username: ",
                text_color=(0,0,0.5),
                pos_hint={"x": 0.05, "center_y": 0.9}
            )
            # add to layout
            root.add_widget(labelUser)

            labelPw = MDLabel(
                text="Password: ",
                text_color=(0,0,0.5),
                pos_hint={"x": 0.05, "center_y": 0.8}
            )
            root.add_widget(labelPw)

            # Button
            self.loginBt = MDRaisedButton(
                text="Safe",
                size_hint_x=None,
                width="100dp",
                pos_hint={"x": 0.05, "center_y": 0.7},
                on_release=self.clickLogin,
                md_bg_color=[0.2, 0.6, 0.4]
            )
            root.add_widget(self.loginBt)

            self.registBt = MDRaisedButton(
                text="Registered",
                size_hint_x=None,
                width="100dp",
                pos_hint={"x": 0.2, "center_y": 0.7},
                on_release=self.clickRegister,
                md_bg_color=[0.2, 0.6, 0.4]
            )
            root.add_widget(self.registBt)

            #textfield
            self.userInput = MDTextField(
                mode="fill",
                hint_text="username",
                size_hint_x=None, #to controll parameter such as width
                width="240dp",
                pos_hint={"x": 0.2, "center_y": 0.9},
                multiline = False # no multiple lines
            )
            root.add_widget(self.userInput)

            self.pwInput = MDTextField(
                mode="fill",
                hint_text="password",
                password = True,
                size_hint_x=None,
                width="240dp",
                pos_hint={"x": 0.2, "center_y": 0.8},
                multiline = False,  # Textfield response to ENTER key
            )
            self.pwInput.bind(on_text_validate=self.clickLogin)
            root.add_widget(self.pwInput)

            Window.bind(on_key_down = self.key_pressed)

            self.output = MDLabel(
                text="",
                size_hint_x=None, width="400dp",
                pos_hint={"x": 0.05, "center_y": 0.6}
            )
            root.add_widget(self.output)

            return root

    def clickLogin(self, instance):
        #button click
        username = self.userInput.text
        password = self.pwInput.text
        if username == "" or password == "":
            self.output.text = "Login Failed"
            self.userInput.text = "" #reset
            self.pwInput.text = ""
        else:
            self.root.clear_widgets()
            print(username)
            print(password)
            self.view2Login(username)

    def clickLogout(self, instance):
        self.root.clear_widgets()
        exit()

    def clickRegister(self, instance):
        self.root.clear_widgets()
        self.viewRegistration()

    def update_rect(self, instance, value):
        # background size to the whole root size
        self.rect.size = instance.size
        self.rect.pos = instance.pos

    def view2Login(self, instance):

        self.labelLogin = MDLabel(
            text="Welcome " + instance + "! \nYou logged in successfully!" ,
            text_color=(0,0,0.5),
            pos_hint={"x": 0.05, "center_y": 0.9}
        )
        self.root.add_widget(self.labelLogin)

        imageLogIn = Image(
            source="froggy.png",
            pos_hint={"x": 0.75, "center_y": 0.9},
            size_hint=(0.2, 0.2)
        )
        self.root.add_widget(imageLogIn)

        endBt = MDRaisedButton(
            text="Logout",
            size_hint_x=None,
            width="140dp",
            pos_hint={"x": 0.8, "center_y": 0.8},
            on_release=self.clickLogout,
            md_bg_color = [0.2, 0.8, 0.4]
        )
        self.root.add_widget(endBt)

    def viewRegistration(self):
        #self.root.clear_widgets()

        self.labelIntro = MDLabel(
            text="Please register below!",
            text_color=(0,0,0.5),
            pos_hint={"x": 0.05, "center_y": 0.95}
        )
        self.root.add_widget(self.labelIntro)

        self.labelUser = MDLabel(
            text="Username: ",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.85}
        )
        self.root.add_widget(self.labelUser)

        self.labelPw = MDLabel(
            text="Password: ",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.75}
        )
        self.root.add_widget(self.labelPw)

        self.labelPw2nd = MDLabel(
            text="Password again: ",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.65}
        )
        self.root.add_widget(self.labelPw2nd)

        self.userInput1 = MDTextField(
            mode="fill",
            hint_text="username",
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.25, "center_y": 0.85},
            multiline=False
        )
        self.root.add_widget(self.userInput1)

        self.pwInput1 = MDTextField(
            mode="fill",
            hint_text="password",
            password=True,
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.25, "center_y": 0.75},
            multiline=False,  # Textfield response to ENTER key
        )
        self.root.add_widget(self.pwInput1)

        self.pwInput2nd = MDTextField(
            mode="fill",
            hint_text="password again",
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.25, "center_y": 0.65},
            multiline=False
        )
        self.pwInput2nd.bind(on_text_validate=self.clickLogin)
        self.root.add_widget(self.pwInput2nd)

    def key_pressed(self, window, key, scancode, codepoint, modifier):
        # key events
        if key == 9:  # Tab
            self.next_field()
            return True
        elif key == 13:  # Enter
            Window.unbind(on_key_down=self.key_pressed)
            self.clickLogin

    def next_field(self):
        #jumps to next text field
        if self.userInput.focus:  # if first is in focus set 2nd in focus
            self.pwInput.focus = True
'''

#run
if __name__ == "__main__":
    MainApp().run()