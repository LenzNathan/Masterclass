from kivy.uix.boxlayout import BoxLayout
from kivy.uix.gridlayout import GridLayout
from kivy.uix.label import Label
from kivymd.app import MDApp
from kivymd.uix.button import MDRaisedButton
import webbrowser

# Jede View ist ein Kivy-Widget!
class HomeView(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.add_widget(Label(text="🏠 Willkommen auf der Startseite"))

class StundenplanView(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.add_widget(Label(text="📅 Stundenplan-Verwaltung"))

class DatenView(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.add_widget(Label(text="📁 Datenverwaltung"))

class MainApp(MDApp):
    def build(self):
        # Hauptlayout
        self.layout = BoxLayout(orientation='vertical')

        # Mainbar mit Buttons
        mainbar = GridLayout(cols=5, size_hint_y=None, height="50dp")

        mainbar.add_widget(Label())  # Platzhalter

        mainbar.add_widget(MDRaisedButton(
            text="Stundenplan verwalten",
            on_release=lambda x: self.switch_partial_view(StundenplanView())
        ))

        mainbar.add_widget(MDRaisedButton(
            text="Daten verwalten",
            on_release=lambda x: self.switch_partial_view(DatenView())
        ))

        mainbar.add_widget(MDRaisedButton(
            text="Konten verwalten",
            on_release=lambda x: print("Noch nicht implementiert.")
        ))

        mainbar.add_widget(MDRaisedButton(
            text="eigenen Account verwalten",
            on_release=lambda x: webbrowser.open("http://localhost:8180/realms/stundenplanApp/account")
        ))

        # Content-Container für Partial Views
        self.partial_container = BoxLayout()
        self.switch_partial_view(HomeView())  # Startansicht setzen

        # Aufbau
        self.layout.add_widget(mainbar)
        self.layout.add_widget(self.partial_container)

        return self.layout

    def switch_partial_view(self, new_view):
        # Alte Ansicht entfernen, neue einsetzen
        self.partial_container.clear_widgets()
        self.partial_container.add_widget(new_view)

MainApp().run()
