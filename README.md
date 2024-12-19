# Typography Course Work Project

## Описание проекта
Проект представляет собой информационную систему для учета заказов в типографии. Он построен на стеке React (frontend), Java Spring Boot (backend) и PostgreSQL (база данных).

## Инструкции по запуску проекта

### 1. Запуск базы данных
Для начала необходимо запустить базу данных, используя Docker-контейнер, который уже настроен в проекте. В корне проекта расположен файл с конфигурацией Docker Compose, содержащий параметры базы данных.

#### Команды для запуска:
1. Откройте терминал в корневой директории проекта.
2. Выполните следующую команду для запуска контейнера:
   ```bash
   docker-compose up -d
   ```

### 2. Запуск серверной части (backend)
Сервер написан на Java и находится в каталоге `javaServerPart`. Для его запуска потребуется установленная JDK на вашем компьютере.

#### Установка и настройка JDK
Если Java еще не установлена, выполните следующие шаги:
1. Убедитесь, что переменная `JAVA_HOME` установлена правильно. В PowerShell выполните:
   ```powershell
   echo $env:JAVA_HOME
   ```
   Убедитесь, что вывод содержит путь к установленной JDK, например: `C:\Users\hirof\.jdks\corretto-22.0.2`.

2. Если переменная не задана, установите ее командой:
   ```powershell
   $env:JAVA_HOME = "C:\Users\hirof\.jdks\corretto-22.0.2"
   ```

3. Проверьте, добавлен ли путь к `bin` в переменную окружения `PATH`:
   ```powershell
   $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
   echo $env:PATH
   ```
   Убедитесь, что путь `C:\Users\hirof\.jdks\corretto-22.0.2\bin` присутствует в начале переменной.

4. Проверьте версию Java:
   ```powershell
   java -version
   ```

#### Запуск серверной части:
1. Перейдите в каталог `javaServerPart`:
   ```bash
   cd javaServerPart
   ```
2. Соберите и запустите проект командой:
   ```bash
   ./mvnw spring-boot:run
   ```

### 3. Настройка IP-адреса для связи микросервисов
Для связи микросервисов потребуется заменить `localhost` на ваш IP-адрес в нескольких файлах проекта. Чтобы узнать ваш IP, выполните в командной строке:
```bash
ipconfig
```

#### Файлы, требующие изменения:
- В контроллерах (примерно на строках 10-15):
  ```
  ..\Typography_course_work\src\main\java\com\example\Typography_course_work\controller
  ```
- В конфигурационном файле API:
  ```
  ..\Typography_course_work\source-frontend\src\ApiConfig.js
  ```

### 4. Запуск фронтенда (React)
Frontend проекта разработан на React. Для его запуска потребуются следующие технологии:
- Node.js
- npm

#### Установка Node.js через fnm (Fast Node Manager):
1. Установите fnm:
   ```bash
   winget install Schniz.fnm
   ```
2. Настройте окружение fnm:
   ```bash
   fnm env --use-on-cd | Out-String | Invoke-Expression
   ```
3. Установите и активируйте Node.js версии 22:
   ```bash
   fnm use --install-if-missing 22
   ```
4. Проверьте версию Node.js:
   ```bash
   node -v
   ```
   Вывод должен быть `v22.12.0`.
5. Проверьте версию npm:
   ```bash
   npm -v
   ```
   Вывод должен быть `10.9.0`.

#### Запуск фронтенда:
1. Перейдите в директорию фронтенда:
   ```bash
   cd C:\Users\{user}\{your_path}\Typography_course_work\source-frontend
   ```
2. Установите зависимости:
   ```bash
   npm install
   ```
3. Запустите фронтенд:
   ```bash
   npm start
   ```
4. Перейдите в браузере по адресу:
   ```
   http://{ваш IP}:3000
   ```

## Поздравляем!
Теперь ваш проект полностью запущен и готов к использованию!

