# EasyLearning
EasyLearning – это веб-приложение, которое предоставляет возможность студентам заучивать иностранные слова удобным способом: с помощью карточек, объединенных в модули.
Во время изучения модуля пользователю последовательно показываются карточки с одной стороны, он проговаривает перевод, после чего переворачивает карточку. В зависимости
от правильности ответа нажимается соответствующая кнопка. После просмотра всех карточек можно повторить те слова, в которых была допущена ошибка. Пользователь может 
самостоятельно создавать новые модули, а также редактировать существующие, что делает приложение довольно гибким и удобным. Благодаря расширенному функционалу пользователь 
может делиться модулями с другими зарегистрированными пользователями.

# Архитектура приложения поуровневая

В нашем приложении есть пять уровней:
1. Уровень представления (Presentation layer)
2. Уровенль для обработки front - end запросов (Controller)
3. Уровень обслуживания клиента (Service layer)
4. Уровень для связи других уровней (Beans)
5. Уровень хранения данных и доступа к ним (Data Access layer)

# Стек технологий

Для разработки проекта использовались следующие технологии:
Java, HTML, CSS, JavaScript, python, postrgreSQL, apache tomcat

Также использовались некоторые сторонние библиотеки:

TestNG - для тестирования

log4j - для логирования

# Диаграммы

Usecase - диаграмма проекта:
![](/docs/img/usecase.png)

Диаграммы зависимостей классов выглядят следующим образом:
![](/docs/img/actions_diagram.png)

![](/docs/img/beans_diagram.png)

![](/docs/img/dao_beans_diagram.png)

![](/docs/img/dao_diagram.png)

![](/docs/img/dao_impl_diagram.png)

![](/docs/img/exceptions_diagram.png)

![](/docs/img/service_diagram.png)

![](/docs/img/service_impl_diagram.png)

![](/docs/img/validators_diagram.png)

![](/docs/img/controller_diagram.png)


ER - диаграмма проекта:
![](/docs/img/er_diagram.jpg)

# Пользовательские сценарии

Ниже приведены 3 пользовательских сценария в виде диаграммы последовательности

1. Добавить карочку
![](/docs/img/add_card_scenario.png)

2. Зарегистрироваться
![](/docs/img/registration_scenario.png)

3. Поделиться модулем
![](/docs/img/share_module_scenario.png)

# Развертывание приложения

Как развертывать приложение:
1. Склонируйте проект и откройте его в своей IDE
2. Установите на свой компьютер posgreSQL:
    
3. 

    a. Если вы используете Ubuntu ссылка на гайд: https://www.digitalocean.com/community/tutorials/how-to-install-postgresql-on-ubuntu-20-04-quickstart-ru
    
    b. Если же вы на Windows: https://winitpro.ru/index.php/2019/10/25/ustanovka-nastrojka-postgresql-v-windows/
4. Запустите скрипты нашего проекта, которые лежат в папке sql
5. Установите apache tomcat
    
    a. Для Windows users: https://metanit.com/java/javaee/2.1.php

    b. Для Ubuntu users: https://www.digitalocean.com/community/tutorials/install-tomcat-9-ubuntu-1804-ru

6. Запустите проект




