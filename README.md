# Домашнее задание 8.6: Spring Cloud Config

Репозиторий содержит три части:

- `config-repo/` — хранилище конфигурации (обычная папка внутри этого же git-репозитория,
  Config Server обращается к ней через `git.uri` + `search-paths`, как к git-репозиторию).
  Файл `config-client.yml` соответствует `spring.application.name=config-client`
  и содержит параметр `app.message`.
- `config-server/` — Spring Boot приложение со стартером `spring-cloud-config-server`,
  раздаёт конфигурацию из `config-repo/` по HTTP.
- `config-client/` — Spring Boot приложение со стартерами `spring-cloud-starter-config`
  и `spring-boot-starter-web`, забирает конфигурацию с сервера конфигурации и
  отдаёт параметр `app.message` через свой контроллер.

## Запуск

Собрать и запустить оба приложения (в двух терминалах, каждое из своей папки):

```bash
cd config-server
mvn spring-boot:run
```

```bash
cd config-client
mvn spring-boot:run
```

Порядок запуска важен: сначала `config-server` (порт 8888), затем `config-client`
(порт 8081) — клиент подключается к серверу при старте.

## Проверка

1. Убедиться, что сервер конфигурации отдаёт конфигурацию клиента напрямую:

   ```
   GET http://localhost:8888/config-client/default
   ```

   В ответе будет JSON с `propertySources`, где встретится `app.message`
   со значением из `config-repo/config-client.yml`.

2. Убедиться, что клиент получил конфигурацию с сервера:

   ```
   GET http://localhost:8081/config/message
   ```

   Должен вернуться текст `Привет из Spring Cloud Config Server!`.

## Публикация на GitHub

Все три части (`config-repo`, `config-server`, `config-client`) находятся в одном
git-репозитории — это и есть репозиторий для загрузки на GitHub. Отдельный git-репозиторий
для конфигурации при желании можно вынести самостоятельно (создать под него отдельный
репозиторий на GitHub, запушить туда содержимое `config-repo/`, а в
`config-server/src/main/resources/application.yml` заменить `uri`/`search-paths` на
прямую ссылку на этот репозиторий), но для сдачи достаточно и текущей монорепо-схемы,
где `search-paths: config-repo` указывает на подпапку с конфигурацией внутри общего репозитория.
