package Main;


import dagger.Component;

@Component
interface CommandRouterFactory {
    static Main.CommandRouterFactory create() {
    }

    CommandRouter router();

}
