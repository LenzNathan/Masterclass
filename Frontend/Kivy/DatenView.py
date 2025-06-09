from kivy.core.window import Window
from kivy.uix.anchorlayout import AnchorLayout
from kivy.uix.floatlayout import FloatLayout
from kivy.uix.popup import Popup
from kivy.uix.scrollview import ScrollView
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.button import Button
from kivy.uix.screenmanager import Screen
from kivy.uix.label import Label

class DataDetailView(Screen):
    def __init__(self, data_name, **kwargs):
        super().__init__(**kwargs)
        self.name = f"detail_{data_name.lower()}"
        layout = BoxLayout(orientation='vertical', padding=10, spacing=10)
        layout.add_widget(Label(text=f"Datensätze verwalten: [b]{data_name}[/b]", markup=True, font_size=24))
        back_btn = Button(text="Zurück", size_hint=(None, None), size=(150, 50))
        back_btn.bind(on_release=self.go_back)
        layout.add_widget(back_btn)
        self.add_widget(layout)

    def go_back(self, *args):
        self.manager.current = "daten"

class DatenView(Screen):
    def __init__(self, app, **kwargs):
        super().__init__(**kwargs)
        self.app = app
        self.name = "daten"

        self.root_layout = FloatLayout()  # für überlagernde Widgets

        scroll = ScrollView(size_hint=(1, 1))
        self.main_layout = BoxLayout(
            orientation='vertical',
            padding=(20, 20),
            spacing=20,
            size_hint_y=None,
            size_hint_x=1
        )
        self.main_layout.bind(minimum_height=self.main_layout.setter('height'))
        scroll.add_widget(self.main_layout)

        self.root_layout.add_widget(scroll)
        self.add_widget(self.root_layout)

        self.active_action_widget = None

        categories = {
            "Allgemein": ["Building", "Abteilung", "Room", "Subject", "Tag"],
            "Zeit": ["LessonEnd", "Time", "LessonBegin"],
            "Personen": ["Teacher", "Student"],
            "Schule": ["Schulklasse", "Group", "Jahrgang", "Lesson"]
        }

        for category, items in categories.items():
            label = Label(
                text=f"[b]{category}[/b]",
                markup=True,
                font_size=20,
                size_hint_y=None,
                height=40,
                halign='left',
                valign='middle',
                text_size=(Window.width - 40, None)
            )
            self.main_layout.add_widget(label)

            container = BoxLayout(
                orientation='horizontal',
                size_hint_y=None,
                height=70,
                spacing=10
            )

            for item in items:
                btn = Button(
                    text=item,
                    size_hint=(None, None),
                    size=(160, 50)
                )
                btn.bind(on_release=lambda btn, name=item, cont=container: self.box_clicked(btn, name, cont))
                container.add_widget(btn)

            self.main_layout.add_widget(container)

    def box_clicked(self, button, item_name, container):
        if self.active_action_widget:
            self.root_layout.remove_widget(self.active_action_widget)
            self.active_action_widget = None

        if getattr(button, 'actions_open', False):
            button.actions_open = False
            return

        btn_pos = button.to_window(button.x, button.y)

        actions = [
            ("Alle bekommen", lambda x: self.open_action_view("get_all", item_name)),
            ("Alle löschen", lambda x: self.open_action_view("delete_all", item_name)),
            ("Ein Element erstellen", lambda x: self.open_action_view("create", item_name)),
            ("Ein Element bekommen", lambda x: self.open_action_view("get_one", item_name)),
            ("Ein Element löschen", lambda x: self.open_action_view("delete_one", item_name)),
            ("Ein Element updaten", lambda x: self.open_action_view("update", item_name)),
        ]

        action_layout = BoxLayout(
            orientation='vertical',
            spacing=5,
            size_hint=(None, None),
            size=(200, len(actions) * 40 + 10),
            pos=(btn_pos[0], btn_pos[1] - len(actions) * 40 - 10)
        )
        for label, callback in actions:
            action_btn = Button(
                text=label,
                size_hint=(1, None),
                height=35,
                font_size=12
            )
            action_btn.bind(on_release=callback)
            action_layout.add_widget(action_btn)

        self.root_layout.add_widget(action_layout)
        self.active_action_widget = action_layout
        button.actions_open = True

    def perform_action(self, action, entity):
        print(f"Aktion: {action} für {entity}")

    def open_action_view(self, action_type, item_name):
        from kivy.uix.popup import Popup
        from kivy.network.urlrequest import UrlRequest
        import json

        # Bestehendes Aktionsmenü schließen
        if self.active_action_widget:
            self.root_layout.remove_widget(self.active_action_widget)
            self.active_action_widget = None

        if action_type == "get_all" and item_name == "Building":
            def on_success(req, result):
                content = BoxLayout(orientation="vertical", spacing=10, padding=10)
                from kivy.uix.label import Label
                from kivy.uix.scrollview import ScrollView

                scroll = ScrollView()
                inner = BoxLayout(orientation="vertical", size_hint_y=None)
                inner.bind(minimum_height=inner.setter("height"))

                for b in result:
                    lbl = Label(
                        text=f"[b]{b['kuerzel']}[/b] - {b['name']} (ID: {b['id']})",
                        markup=True,
                        size_hint_y=None,
                        height=40
                    )
                    inner.add_widget(lbl)

                scroll.add_widget(inner)
                content.add_widget(scroll)

                popup = Popup(
                    title="Buildings",
                    content=content,
                    size_hint=(0.9, 0.9)
                )
                popup.open()

            def on_error(req, result):
                self.show_error_popup(f"Fehler beim Laden der Gebäude: {result}")

            def on_failure(req, error):
                self.show_error_popup(f"Verbindungsfehler: {error}")

            UrlRequest(
                url="http://localhost:8080/buildings",
                on_success=on_success,
                on_error=on_error,
                on_failure=on_failure
            )
        elif action_type == "create" and item_name == "Building":
            self.show_create_building_form()
        elif action_type == "delete_all" and item_name == "Building":
            self.confirm_delete_all_buildings()
        elif action_type == "get_one" and item_name == "Building":
            self.open_get_single_building_view()
        elif action_type == "update" and item_name == "Building":
            self.open_building_update_view()
        elif action_type == "delete_one" and item_name =="Building":
            self.open_delete_building_view()
        else:
            # Platzhalter für alle anderen Aktionen
            popup = Popup(
                title=f"{action_type} – {item_name}",
                content=Label(text=f"View für '{action_type}' von '{item_name}' kommt noch."),
                size_hint=(0.7, 0.4)
            )
            popup.open()

    def show_error_popup(self, message):
        from kivy.uix.popup import Popup
        from kivy.uix.label import Label

        popup = Popup(
            title="Fehler",
            content=Label(text=message),
            size_hint=(0.7, 0.4)
        )
        popup.open()

    def show_info_popup(self, title, message, on_ok_callback=None):
        from kivy.uix.popup import Popup
        from kivy.uix.label import Label
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.button import Button

        layout = BoxLayout(orientation="vertical", padding=10, spacing=10)
        layout.add_widget(Label(text=message))
        close_btn = Button(text="OK", size_hint=(1, None), height=40)
        layout.add_widget(close_btn)

        popup = Popup(title=title, content=layout, size_hint=(0.6, 0.4))

        def on_ok(instance):
            popup.dismiss()
            if on_ok_callback:
                on_ok_callback(instance)

        close_btn.bind(on_release=on_ok)
        popup.open()

    def show_create_building_form(self):
            from kivy.uix.popup import Popup
            from kivy.uix.boxlayout import BoxLayout
            from kivy.uix.label import Label
            from kivy.uix.textinput import TextInput
            from kivy.uix.button import Button
            from kivy.network.urlrequest import UrlRequest
            import json

            def submit_building(name, kuerzel, popup):
                def on_success(req, result):
                    popup.dismiss()

                    def on_ok(_):
                        # Öffne wieder das Eingabeformular
                        self.show_create_building_form()

                    self.show_info_popup("Erfolg", "Gebäude erfolgreich erstellt!", on_ok)

                def on_error(req, result):
                    self.show_error_popup("Fehler", f"Fehler beim Erstellen: {result}")

                def on_failure(req, error):
                    self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

                data = json.dumps({
                    "name": name,
                    "kuerzel": kuerzel
                })

                headers = {'Content-Type': 'application/json'}
                UrlRequest(
                    url="http://localhost:8080/buildings",  # URL ggf. anpassen
                    req_body=data,
                    req_headers=headers,
                    method='POST',
                    on_success=on_success,
                    on_error=on_error,
                    on_failure=on_failure
                )

            layout = BoxLayout(orientation="vertical", spacing=10, padding=10)

            name_input = TextInput(hint_text="Name", multiline=False)
            kuerzel_input = TextInput(hint_text="Kürzel", multiline=False)

            submit_btn = Button(text="Erstellen", size_hint=(1, None), height=40)
            layout.add_widget(Label(text="Neues Gebäude erstellen"))
            layout.add_widget(name_input)
            layout.add_widget(kuerzel_input)
            layout.add_widget(submit_btn)

            popup = self.create_custom_popup("Gebäude erstellen", layout, size_hint=(0.9, 0.9))
            submit_btn.bind(on_release=lambda x: submit_building(name_input.text, kuerzel_input.text, popup))
            popup.open()

    def confirm_delete_all_buildings(self):
        from kivy.uix.popup import Popup
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.label import Label
        from kivy.uix.button import Button
        from kivy.network.urlrequest import UrlRequest

        layout = BoxLayout(orientation="vertical", spacing=10, padding=10)
        label = Label(text="Willst du wirklich **alle** Gebäude löschen?", font_size=16)
        btn_yes = Button(text="Ja, löschen", size_hint=(1, None), height=40)
        btn_no = Button(text="Abbrechen", size_hint=(1, None), height=40)

        layout.add_widget(label)
        layout.add_widget(btn_yes)
        layout.add_widget(btn_no)
        popup = self.create_custom_popup(title="Bestätigung", content=layout, size_hint=(0.9, 0.9))

        def on_success(req, result):
            popup.dismiss()
            self.show_info_popup("Erfolg", "Alle Gebäude wurden gelöscht.")

        def on_error(req, result):
            popup.dismiss()
            self.show_error_popup(f"Fehler beim Löschen: {result}")

        def on_failure(req, error):
            popup.dismiss()
            self.show_error_popup(f"Verbindungsfehler: {error}")

        def delete_all(instance):
            headers = {'Content-Type': 'application/json'}
            UrlRequest(
                url="http://localhost:8080/buildings/all",
                req_headers=headers,
                method='DELETE',
                on_success=on_success,
                on_error=on_error,
                on_failure=on_failure
            )

        btn_yes.bind(on_release=delete_all)
        btn_no.bind(on_release=lambda x: popup.dismiss())
        popup.open()

    def open_building_update_view(self):
        from kivy.uix.popup import Popup
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.label import Label
        from kivy.uix.button import Button
        from kivy.uix.textinput import TextInput
        from kivy.network.urlrequest import UrlRequest
        import json

        def on_buildings_success(req, result):
            if not result:
                self.show_info_popup("Keine Daten", "Keine Datensätze vorhanden!")
                return

            selection_layout = BoxLayout(orientation="vertical", spacing=10, padding=10)

            for building in result:
                btn = Button(
                    text=f"{building['kuerzel']} - {building['name']}",
                    size_hint=(1, None),
                    height=40
                )

                def create_update_popup(building_data):
                    def open_popup(instance):
                        popup_layout = BoxLayout(orientation="vertical", spacing=10, padding=10)

                        id_label = Label(text=f"ID: {building_data['id']}", size_hint=(1, None), height=30)
                        name_input = TextInput(text=building_data['name'], hint_text="Name", multiline=False)
                        kuerzel_input = TextInput(text=building_data['kuerzel'], hint_text="Kürzel", multiline=False)

                        save_btn = Button(text="Speichern", size_hint=(1, None), height=40)
                        cancel_btn = Button(text="Abbrechen", size_hint=(1, None), height=40)

                        popup_layout.add_widget(id_label)
                        popup_layout.add_widget(name_input)
                        popup_layout.add_widget(kuerzel_input)
                        popup_layout.add_widget(save_btn)
                        popup_layout.add_widget(cancel_btn)
                        popup = self.create_custom_popup("Gebäude bearbeiten", popup_layout, size_hint=(0.9, 0.9))

                        def send_update(instance):
                            body = {
                                "name": name_input.text.strip(),
                                "kuerzel": kuerzel_input.text.strip()
                            }

                            url = f"http://localhost:8080/buildings/{building_data['id']}"  # anpassen
                            headers = {'Content-Type': 'application/json'}

                            def on_success(req, result):
                                popup.dismiss()
                                self.show_info_popup("Erfolg", "Gebäude erfolgreich aktualisiert.")

                            def on_error(req, result):
                                popup.dismiss()
                                self.show_error_popup("Fehler", f"Fehler beim Update: {result}")

                            def on_failure(req, error):
                                popup.dismiss()
                                self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

                            UrlRequest(
                                url=url,
                                method='PUT',
                                req_headers=headers,
                                req_body=json.dumps(body),
                                on_success=on_success,
                                on_error=on_error,
                                on_failure=on_failure
                            )

                        save_btn.bind(on_release=send_update)
                        cancel_btn.bind(on_release=lambda x: popup.dismiss())
                        popup.open()

                    return open_popup

                btn.bind(on_release=create_update_popup(building))
                selection_layout.add_widget(btn)

            popup = self.create_custom_popup("Gebäude auswählen zum Bearbeiten", selection_layout, size_hint=(0.9, 0.9))
            popup.open()

        def on_buildings_error(req, result):
            self.show_error_popup("Fehler", f"Fehler beim Laden: {result}")

        def on_buildings_failure(req, error):
            self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

        UrlRequest(
            url="http://localhost:8080/buildings",
            method="GET",
            on_success=on_buildings_success,
            on_error=on_buildings_error,
            on_failure=on_buildings_failure
        )

    def open_get_single_building_view(self):
        from kivy.uix.popup import Popup
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.label import Label
        from kivy.uix.button import Button
        from kivy.network.urlrequest import UrlRequest
        import json

        def on_buildings_loaded(req, result):
            if not result:
                self.show_info_popup("Keine Daten", "Keine Datensätze vorhanden!")
                return

            selection_layout = BoxLayout(orientation="vertical", spacing=10, padding=10)

            for building in result:
                btn = Button(
                    text=f"{building['kuerzel']} - {building['name']}",
                    size_hint=(1, None),
                    height=40
                )

                def create_detail_fetcher(building_id):
                    def on_click(instance):
                        def on_detail_success(req, detail):
                            detail_layout = BoxLayout(orientation="vertical", spacing=10, padding=10)
                            detail_layout.add_widget(Label(text=f"ID: {detail['id']}"))
                            detail_layout.add_widget(Label(text=f"Name: {detail['name']}"))
                            detail_layout.add_widget(Label(text=f"Kürzel: {detail['kuerzel']}"))

                            close_btn = Button(text="OK", size_hint=(1, None), height=40)
                            detail_layout.add_widget(close_btn)
                            popup = self.create_custom_popup("Gebäude-Details", detail_layout, size_hint=(0.9, 0.9))
                            close_btn.bind(on_release=popup.dismiss)
                            popup.open()

                        def on_error(req, result):
                            self.show_error_popup("Fehler", f"Fehler beim Laden: {result}")

                        def on_failure(req, error):
                            self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

                        UrlRequest(
                            url=f"http://localhost:8080/buildings/{building_id}",  # URL anpassen
                            method="GET",
                            on_success=on_detail_success,
                            on_error=on_error,
                            on_failure=on_failure
                        )

                    return on_click

                btn.bind(on_release=create_detail_fetcher(building["id"]))
                selection_layout.add_widget(btn)
            popup = self.create_custom_popup("Gebäude auswählen", selection_layout, size_hint=(0.9, 0.9))
            popup.open()

        def on_error(req, result):
            self.show_error_popup("Fehler", f"Fehler beim Laden der Gebäudeliste: {result}")

        def on_failure(req, error):
            self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

        UrlRequest(
            url="http://localhost:8080/buildings",  # URL anpassen
            method="GET",
            on_success=on_buildings_loaded,
            on_error=on_error,
            on_failure=on_failure
        )

    def open_delete_building_view(self):
        import json
        from kivy.uix.popup import Popup
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.button import Button
        from kivy.uix.scrollview import ScrollView
        from kivy.uix.label import Label
        from kivy.network.urlrequest import UrlRequest

        def on_success(req, result):
            buildings = result
            if not buildings:
                self.show_info_popup("Keine Einträge", "Keine Datensätze vorhanden!")
                return

            layout = BoxLayout(orientation="vertical", spacing=5, padding=10)
            scroll = ScrollView()
            inner = BoxLayout(orientation='vertical', size_hint_y=None)
            inner.bind(minimum_height=inner.setter('height'))

            for building in buildings:
                text = f"{building['name']} ({building['kuerzel']})"
                btn = Button(text=text, size_hint_y=None, height=40)
                btn.bind(on_release=lambda btn, b=building: confirm_delete_popup(b))
                inner.add_widget(btn)

            scroll.add_widget(inner)
            layout.add_widget(Label(text="Wähle ein Gebäude zum Löschen:", size_hint_y=None, height=30))
            layout.add_widget(scroll)

            popup = self.create_custom_popup("Gebäude löschen", layout, size_hint=(0.9, 0.9))
            popup.open()

            self.delete_building_popup = popup  # speichern, um nachher zu schließen

        def confirm_delete_popup(building):
            def on_ok(instance):
                delete_building(building['id'])

            self.show_info_popup(
                "Löschen bestätigen",
                f"Möchtest du das Gebäude '{building['name']}' wirklich löschen?",
                on_ok_callback=on_ok
            )

        def delete_building(building_id):
            def on_success_delete(req, result):
                self.show_info_popup("Erfolg", "Gebäude erfolgreich gelöscht!",
                                     lambda _: self.open_delete_building_view())

            def on_error(req, result):
                self.show_error_popup("Fehler", f"Fehler beim Löschen: {result}")

            def on_failure(req, error):
                self.show_error_popup("Verbindung", f"Verbindungsfehler: {error}")

            if hasattr(self, 'delete_building_popup'):
                self.delete_building_popup.dismiss()

            UrlRequest(
                url=f"http://localhost:8080/buildings/{building_id}",
                method='DELETE',
                on_success=on_success_delete,
                on_error=on_error,
                on_failure=on_failure
            )

        # GET /buildings aufrufen
        UrlRequest(
            url="http://localhost:8080/buildings",
            method='GET',
            on_success=on_success,
            on_error=lambda *a: self.show_error_popup("Fehler", "Fehler beim Abrufen der Gebäude."),
            on_failure=lambda *a: self.show_error_popup("Verbindung", "Verbindung fehlgeschlagen.")
        )
    def show_popup_with_close(title, inner_content):
        popup = Popup(title='',
                      size_hint=(0.8, 0.8),
                      auto_dismiss=False)

        # Hauptlayout: enthält Schließen-Button und restlichen Inhalt
        layout = BoxLayout(orientation='vertical')

        # Obere Leiste mit X-Button
        top_bar = AnchorLayout(anchor_x='right', anchor_y='top', size_hint_y=None, height=40)
        close_btn = Button(text='X', size_hint=(None, None), size=(40, 40),
                           background_normal='', background_color=(1, 0.3, 0.3, 1))
        close_btn.bind(on_release=lambda *args: popup.dismiss())
        top_bar.add_widget(close_btn)

        # Layout zusammensetzen
        layout.add_widget(top_bar)
        layout.add_widget(inner_content)

        popup.content = layout
        popup.open()

    def create_custom_popup(self, title, content, size_hint=(0.8, 0.6)):
        from kivy.uix.popup import Popup
        from kivy.uix.boxlayout import BoxLayout
        from kivy.uix.button import Button
        from kivy.uix.label import Label

        wrapper = BoxLayout(orientation='vertical')
        header = BoxLayout(size_hint_y=None, height=40)

        title_label = Label(text=title, halign="left", valign="middle")
        title_label.bind(size=title_label.setter("text_size"))

        close_button = Button(text='X', size_hint=(None, 1), width=40)
        popup = Popup(content=wrapper, size_hint=size_hint, auto_dismiss=False)

        close_button.bind(on_release=popup.dismiss)

        header.add_widget(title_label)
        header.add_widget(close_button)

        wrapper.add_widget(header)
        wrapper.add_widget(content)

        return popup

