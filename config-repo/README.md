# config-repo

Репозиторий с конфигурацией для Spring Cloud Config Server.

Файл `config-client.yml` соответствует `spring.application.name=config-client`
приложения-клиента и содержит параметр `app.message`, который клиент
получает с сервера конфигурации и отдаёт через свой контроллер.
